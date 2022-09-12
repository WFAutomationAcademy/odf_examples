package com.workfusion.examples.aa_examples_bots.notepad;

import com.workfusion.examples.aa_examples_bots.AbstractTestWithLogger;
import com.workfusion.examples.aa_examples_bots.notepad.task.NotepadAndClipboardTask;
import com.workfusion.odf.test.junit.IacDeveloperJUnitConfig;
import com.workfusion.odf.test.launch.InputData;
import com.workfusion.odf.test.launch.OutputData;
import com.workfusion.odf2.junit.BotTaskFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@IacDeveloperJUnitConfig
public class NotepadAndClipboardTaskTest extends AbstractTestWithLogger {

    private static final String TEXT_TO_TYPE = "Test text";

    @Test
    @DisplayName("Should run bot task")
    void shouldRunRpaBotTask(BotTaskFactory botTaskFactory) {

        OutputData outputData = botTaskFactory.fromClass(NotepadAndClipboardTask.class)
                .withInputData(InputData.of(NotepadAndClipboardTask.INPUT_COLUMN_NAME, TEXT_TO_TYPE))
                .buildAndRun();

        logResults(outputData);
    }
}
