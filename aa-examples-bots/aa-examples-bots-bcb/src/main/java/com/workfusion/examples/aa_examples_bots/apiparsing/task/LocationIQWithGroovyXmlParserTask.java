package com.workfusion.examples.aa_examples_bots.apiparsing.task;

import com.workfusion.examples.aa_examples_bots.apiparsing.common_workflow.GetResponseFromLocationIQ;
import com.workfusion.odf2.compiler.BotTask;
import com.workfusion.odf2.core.cdi.Injector;
import com.workfusion.odf2.core.task.generic.GenericTask;
import com.workfusion.odf2.core.webharvest.TaskInput;
import com.workfusion.odf2.core.webharvest.TaskOutput;
import com.workfusion.odf2.core.webharvest.service.vault.SecretsVaultService;
import groovy.util.Node;
import groovy.util.XmlParser;
import org.xml.sax.SAXException;

import javax.inject.Inject;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@BotTask
public class LocationIQWithGroovyXmlParserTask implements GenericTask {

    private final TaskInput taskInput;
    private final TaskOutput taskOutput;
    private final SecretsVaultService secretsVaultService;

    @Inject
    public LocationIQWithGroovyXmlParserTask(Injector injector) {
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

        //Parse response into Node object using XmlParser
        Node xmlResponse;
        try {
            xmlResponse = new XmlParser().parseText(response);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new RuntimeException("An error occurred during parsing xml", e);
        }

        /*Get list of places as child elements of parsed xmlResponse
         *
         * <searchresults ...>
         *    <place .../>
         *    ...
         *    <place .../>
         * </searchresults>
         * */
        List<?> places = xmlResponse.children();
        //Get first "place" element to return only its data
        Node firstPlaceFromResponse = (Node) places.get(0);

        //Get attribute values from "place" element
        String displayName = (String) firstPlaceFromResponse.attribute("display_name");
        String placeRank = (String) firstPlaceFromResponse.attribute("place_rank");
        String type = (String) firstPlaceFromResponse.attribute("type");

        //Set output columns to be exported from the task
        taskOutput.setColumn("name", displayName);
        taskOutput.setColumn("place_rank", placeRank);
        taskOutput.setColumn("type", type);
    }
}
