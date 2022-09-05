package com.workfusion.examples.aa_examples_bots.google.task;

import com.workfusion.examples.aa_examples_bots.google.automation.entities.FoundItemDto;
import com.workfusion.examples.aa_examples_bots.google.workflow.MakeGoogleSearch;
import com.workfusion.odf2.compiler.BotTask;
import com.workfusion.odf2.core.cdi.Injector;
import com.workfusion.odf2.core.task.AdHocTask;
import com.workfusion.odf2.core.task.TaskInput;
import com.workfusion.odf2.core.task.output.MultipleResults;
import com.workfusion.odf2.core.task.output.SingleResult;
import com.workfusion.odf2.core.task.output.TaskRunnerOutput;
import com.workfusion.odf2.core.webharvest.rpa.RpaDriver;
import com.workfusion.odf2.core.webharvest.rpa.RpaFactory;
import com.workfusion.odf2.core.webharvest.rpa.RpaRunner;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@BotTask(requireRpa = true)
public class GoogleDemoTask implements AdHocTask {

    private final RpaRunner rpaRunner;
    private final Logger logger;

    public static final String INPUT_COLUMN_NAME = "text_to_search";

    @Inject
    public GoogleDemoTask(Injector injector) {
        RpaFactory rpaFactory = injector.instance(RpaFactory.class);
        this.rpaRunner = rpaFactory.builder(RpaDriver.UNIVERSAL).closeOnCompletion(true).build();
        this.logger = injector.instance(Logger.class);
    }

    @Override
    public TaskRunnerOutput run(TaskInput taskInput) {
        //AtomicReference object is used to have ability to get access and set results list from lambda expression
        AtomicReference<List<Map<String, String>>> taskResults = new AtomicReference<>();
        rpaRunner.execute(d -> {
            String textToSearch = taskInput.getRequiredVariable(INPUT_COLUMN_NAME);
            //Get raw results by executing common (for GoogleDemoTask and GoogleDemoRobot) scenario
            List<FoundItemDto> rawResult = MakeGoogleSearch.makeSearch(this.logger, textToSearch);
            //Prepare results to be returned
            taskResults.set(rawResult.stream().map(FoundItemDto::convertToMap).collect(Collectors.toList()));
        });
        MultipleResults results = new MultipleResults();
        taskResults.get().forEach(row -> results.addRow(new SingleResult(row)));
        return results;
    }
}
