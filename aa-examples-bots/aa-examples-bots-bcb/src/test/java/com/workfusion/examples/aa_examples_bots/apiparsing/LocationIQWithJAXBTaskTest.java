package com.workfusion.examples.aa_examples_bots.apiparsing;

import com.workfusion.examples.aa_examples_bots.AbstractTestWithLogger;
import com.workfusion.examples.aa_examples_bots.apiparsing.common_workflow.GetResponseFromLocationIQ;
import com.workfusion.examples.aa_examples_bots.apiparsing.task.LocationIQWithJAXBTask;
import com.workfusion.odf.test.junit.WorkerJUnitConfig;
import com.workfusion.odf.test.launch.BotTaskUnit;
import com.workfusion.odf.test.launch.InputData;
import com.workfusion.odf.test.launch.OutputData;
import com.workfusion.odf2.junit.BotTaskFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@WorkerJUnitConfig
public class LocationIQWithJAXBTaskTest extends AbstractTestWithLogger {

    //Sign up to LocationIQ API service and obtain your apikey - https://us1.locationiq.com
    private static final String API_ACCESS_TOKEN = "PUT YOUR ACCESS TOKEN HERE";

    //Companies to get info about
    private static final String[] COMPANIES = {"microsoft", "apple"};

    @Test
    @DisplayName("Should run bot task and get info about first place for every company")
    void shouldRunBotTask(BotTaskFactory botTaskFactory) {
        BotTaskUnit genericTask = botTaskFactory
                .fromClass(LocationIQWithJAXBTask.class)
                .withSecureEntries(cfg -> cfg.withEntry(GetResponseFromLocationIQ.API_ACCESS_TOKEN_ALIAS,
                        "not_used", API_ACCESS_TOKEN))
                .withInputData(InputData.of(GetResponseFromLocationIQ.INPUT_COLUMN_NAME, COMPANIES));

        OutputData outputData = genericTask.buildAndRun();

        logResults(outputData);

        assertThat(outputData.getRecords()).hasSize(COMPANIES.length);
    }
}
