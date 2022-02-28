package com.workfusion.examples.aa_examples_bots.webapplication;

import com.workfusion.examples.aa_examples_bots.webapplication.task.InvoicePlaneNavigationTask;
import com.workfusion.odf.test.junit.IacDeveloperJUnitConfig;
import com.workfusion.odf.test.launch.InputData;
import com.workfusion.odf.test.launch.LaunchSettingsFactory;
import com.workfusion.odf.test.launch.WorkerAgent;
import com.workfusion.studio.bot.model.LaunchSettings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@IacDeveloperJUnitConfig
public class InvoicePlaneNavigationTaskTest {

    private static final String INVOICE_INPUT =
            "{client:\"Workfusion Inc.\"," +
                    "invoice_date:\"02/01/2022\"," +
                    "invoice_group:\"Default\"," +
                    "payment_method:\"Paypal\"," +
                    "total_discount:\"10\"," +
                    "products:[{item:\"Small Iron Watch\",quantity:\"10\",price:\"963.09\",description:\"The best choice for everyone who wants a watch\"}," +
                    "{item:\"Iron Clock\",quantity:\"7\",price:\"950.05\",description:\"Get it and you won't regret\"}," +
                    "{item:\"Ergonomic Paper Bench\",quantity:\"5\",price:\"938.24\",description:\"More comfortable than anything else\"}]}";

    @Test
    @DisplayName("Should run bot task")
    void shouldRunRpaBotTask(WorkerAgent agent, LaunchSettingsFactory settingsFactory) {


        LaunchSettings settings = settingsFactory.builder()
                .secureEntries(cfg -> cfg.withEntry(InvoicePlaneNavigationTask.INVOICE_PLANE_CREDENTIALS_ALIAS,
                        "wf-robot@mail.com", "BotsRock4ever!"))
                .inputData(InputData.of(InvoicePlaneNavigationTask.INPUT_COLUMN_NAME, INVOICE_INPUT))
                .botTask("configs/main/invoice-plane-navigation.xml")
                .build();

        agent.run(settings);
    }
}
