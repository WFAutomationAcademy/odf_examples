package com.workfusion.examples.aa_examples_bots.notepad;

import com.workfusion.examples.aa_examples_bots.AbstractTestWithLogger;
import com.workfusion.examples.aa_examples_bots.notepad.task.NotepadAndClipboardTask;
import com.workfusion.odf.test.junit.IacDeveloperJUnitConfig;
import com.workfusion.odf.test.launch.BotTaskResult;
import com.workfusion.odf.test.launch.InputData;
import com.workfusion.odf.test.launch.LaunchSettingsFactory;
import com.workfusion.odf.test.launch.WorkerAgent;
import com.workfusion.studio.bot.model.LaunchSettings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@IacDeveloperJUnitConfig
public class NotepadAndClipboardTaskTest extends AbstractTestWithLogger {

    private static final String TEXT_TO_TYPE = "Test text";
    @Test
    @DisplayName("Should run bot task")
    void shouldRunRpaBotTask(WorkerAgent agent, LaunchSettingsFactory settingsFactory) {


        LaunchSettings settings = settingsFactory.builder()
                .botTask("configs/main/notepad-and-clipboard.xml")
                .inputData(InputData.of(NotepadAndClipboardTask.INPUT_COLUMN_NAME, TEXT_TO_TYPE))
                .build();

        BotTaskResult actualResult = agent.run(settings);

        logResults(actualResult.getOutputData());
    }
}
