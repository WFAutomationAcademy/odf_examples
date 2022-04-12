package com.workfusion.examples.aa_examples_bots.scrapwebtable;

import com.workfusion.examples.aa_examples_bots.AbstractTestWithLogger;
import com.workfusion.examples.aa_examples_bots.scrapwebtable.task.ScrapTableWithRpaTask;
import com.workfusion.odf.test.junit.IacDeveloperJUnitConfig;
import com.workfusion.odf.test.launch.OutputData;
import com.workfusion.odf2.junit.BotTaskFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@IacDeveloperJUnitConfig
public class ScrapTableWithRpaTaskTest extends AbstractTestWithLogger {

    @Test
    @DisplayName("Should run bot task")
    void shouldRunRpaBotTask(BotTaskFactory botTaskFactory) {

        OutputData outputData = botTaskFactory.fromClass(ScrapTableWithRpaTask.class).buildAndRun();

        logResults(outputData);
    }
}
