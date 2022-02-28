package com.workfusion.examples.aa_examples_bots.scrapwebtable;

import com.workfusion.examples.aa_examples_bots.AbstractTestWithLogger;
import com.workfusion.examples.aa_examples_bots.scrapwebtable.task.ScrapTableWithoutRpaTask;
import com.workfusion.odf.test.junit.WorkerJUnitConfig;
import com.workfusion.odf.test.launch.BotTaskUnit;
import com.workfusion.odf.test.launch.OutputData;
import com.workfusion.odf2.junit.BotTaskFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@WorkerJUnitConfig
public class ScrapTableWithoutRpaTaskTest extends AbstractTestWithLogger {

    @Test
    @DisplayName("Should run bot task")
    void shouldRunBotTask(BotTaskFactory botTaskFactory) {
        BotTaskUnit genericTask = botTaskFactory
                .fromClass(ScrapTableWithoutRpaTask.class);

        OutputData outputData = genericTask.buildAndRun();

        logResults(outputData);
    }
}
