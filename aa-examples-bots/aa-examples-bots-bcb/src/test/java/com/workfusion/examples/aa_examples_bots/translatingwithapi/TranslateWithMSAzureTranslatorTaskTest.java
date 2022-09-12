package com.workfusion.examples.aa_examples_bots.translatingwithapi;

import com.workfusion.examples.aa_examples_bots.AbstractTestWithLogger;
import com.workfusion.examples.aa_examples_bots.translatingwithapi.task.TranslateWithMSAzureTranslatorTask;
import com.workfusion.odf.test.junit.WorkerJUnitConfig;
import com.workfusion.odf.test.launch.BotTaskUnit;
import com.workfusion.odf.test.launch.InputData;
import com.workfusion.odf.test.launch.OutputData;
import com.workfusion.odf2.junit.BotTaskFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@WorkerJUnitConfig
public class TranslateWithMSAzureTranslatorTaskTest extends AbstractTestWithLogger {

    //Register on https://azure.microsoft.com/en-us/services/cognitive-services/translator/
    //Create your instance of translator service (Can be used free subscription). Obtain your access token and region.
    private static final String API_ACCESS_TOKEN_OF_TRANSLATOR_SERVICE = "PUT YOUR ACCESS TOKEN HERE";
    private static final String REGION_VALUE_OF_TRANSLATOR_SERVICE = "PUT YOUR REGION HERE";

    //Input data
    private static final String TEXT_TO_TRANSLATE = "Ich würde wirklich gerne Ihr Auto ein paar Mal um den Block fahren.";
    private static final String TRANSLATE_TO = "en";

    @Test
    @DisplayName("Should run bot task")
    void shouldRunBotTask(BotTaskFactory botTaskFactory) {

        List<String> inputDataHeaders = new ArrayList<>();
        inputDataHeaders.add(TranslateWithMSAzureTranslatorTask.TEXT_INPUT_COLUMN_NAME);
        inputDataHeaders.add(TranslateWithMSAzureTranslatorTask.TRANSLATE_TO_INPUT_COLUMN_NAME);

        List<String> inputDataRowValues = new ArrayList<>();
        inputDataRowValues.add(TEXT_TO_TRANSLATE);
        inputDataRowValues.add(TRANSLATE_TO);

        BotTaskUnit genericTask = botTaskFactory
                .fromClass(TranslateWithMSAzureTranslatorTask.class)
                .withSecureEntries(cfg -> cfg.withEntry(TranslateWithMSAzureTranslatorTask.AZURE_TRANSLATOR_CREDENTIALS_ALIAS,
                        REGION_VALUE_OF_TRANSLATOR_SERVICE, API_ACCESS_TOKEN_OF_TRANSLATOR_SERVICE))
                .withInputData(InputData.of(inputDataHeaders, inputDataRowValues));

        OutputData outputData = genericTask.buildAndRun();

        logResults(outputData);
    }
}
