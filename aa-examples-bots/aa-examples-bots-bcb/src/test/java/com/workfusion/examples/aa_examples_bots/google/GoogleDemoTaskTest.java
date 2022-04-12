package com.workfusion.examples.aa_examples_bots.google;

import com.workfusion.examples.aa_examples_bots.AbstractTestWithLogger;
import com.workfusion.examples.aa_examples_bots.google.task.GoogleDemoTask;
import com.workfusion.odf.test.junit.IacDeveloperJUnitConfig;
import com.workfusion.odf.test.launch.InputData;
import com.workfusion.odf.test.launch.OutputData;
import com.workfusion.odf2.junit.BotTaskFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@IacDeveloperJUnitConfig
public class GoogleDemoTaskTest extends AbstractTestWithLogger {

    private static final String TEXT_TO_SEARCH = "workfusion";

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDemoTaskTest.class);

    @Test
    @DisplayName("Should run bot task")
    void shouldRunRpaBotTask(BotTaskFactory botTaskFactory) {

        OutputData outputData = botTaskFactory.fromClass(GoogleDemoTask.class)
                .withInputData(InputData.of(GoogleDemoTask.INPUT_COLUMN_NAME, TEXT_TO_SEARCH))
                .buildAndRun();

        logResults(outputData);
    }
}
