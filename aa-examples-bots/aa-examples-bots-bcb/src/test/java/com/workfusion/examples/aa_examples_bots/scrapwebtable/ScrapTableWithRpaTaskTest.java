package com.workfusion.examples.aa_examples_bots.scrapwebtable;

import com.workfusion.examples.aa_examples_bots.AbstractTestWithLogger;
import com.workfusion.odf.test.junit.IacDeveloperJUnitConfig;
import com.workfusion.odf.test.launch.BotTaskResult;
import com.workfusion.odf.test.launch.LaunchSettingsFactory;
import com.workfusion.odf.test.launch.WorkerAgent;
import com.workfusion.studio.bot.model.LaunchSettings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@IacDeveloperJUnitConfig
public class ScrapTableWithRpaTaskTest extends AbstractTestWithLogger {

    @Test
    @DisplayName("Should run bot task")
    void shouldRunRpaBotTask(WorkerAgent agent, LaunchSettingsFactory settingsFactory) {

        LaunchSettings settings = settingsFactory.builder()
                .botTask("configs/main/scrap-table-with-rpa.xml")
                .build();

        BotTaskResult actualResult = agent.run(settings);

        logResults(actualResult.getOutputData());
    }
}
