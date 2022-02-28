package com.workfusion.examples.aa_examples_bots.apiparsing.task;

import com.workfusion.examples.aa_examples_bots.apiparsing.common_workflow.GetResponseFromLocationIQ;
import com.workfusion.examples.aa_examples_bots.apiparsing.entity.Place;
import com.workfusion.examples.aa_examples_bots.apiparsing.entity.SearchResults;
import com.workfusion.odf2.compiler.BotTask;
import com.workfusion.odf2.core.cdi.Injector;
import com.workfusion.odf2.core.task.generic.GenericTask;
import com.workfusion.odf2.core.webharvest.TaskInput;
import com.workfusion.odf2.core.webharvest.TaskOutput;
import com.workfusion.odf2.core.webharvest.service.vault.SecretsVaultService;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@BotTask
public class LocationIQWithJAXBTask implements GenericTask {

    private final TaskInput taskInput;
    private final TaskOutput taskOutput;
    private final SecretsVaultService secretsVaultService;

    @Inject
    public LocationIQWithJAXBTask(Injector injector) {
        this.taskInput = injector.instance(TaskInput.class);
        this.taskOutput = injector.instance(TaskOutput.class);
        this.secretsVaultService = injector.instance(SecretsVaultService.class);
    }

    @Override
    public void run() {

        //Get LocationIQ response
        GetResponseFromLocationIQ getResponseFromLocationIQ = new GetResponseFromLocationIQ(this.taskInput,
                this.secretsVaultService);
        String response = getResponseFromLocationIQ.getLocationIQResponse();

        SearchResults searchResults;
        try {
            //Create JAXBContext instance for SearchResults class
            JAXBContext jc = JAXBContext.newInstance(SearchResults.class);
            //Create unmarshaller
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            //Map response on java object (SearchResults instance)
            searchResults = (SearchResults) unmarshaller.unmarshal(new StringReader(response));
        } catch (JAXBException e) {
            throw new RuntimeException("An error occurred during parsing xml", e);
        }

        //Get first "place" element to return only its data
        Place firstPlaceFromResponse = searchResults.getPlaces().get(0);

        //Set output columns to be exported from the task
        taskOutput.setColumn("name", firstPlaceFromResponse.getDisplayName());
        taskOutput.setColumn("place_rank", firstPlaceFromResponse.getPlaceRank());
        taskOutput.setColumn("type", firstPlaceFromResponse.getType());
    }
}
