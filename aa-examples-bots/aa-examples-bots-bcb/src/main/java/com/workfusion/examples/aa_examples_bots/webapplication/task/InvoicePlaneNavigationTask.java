package com.workfusion.examples.aa_examples_bots.webapplication.task;

import com.google.gson.Gson;
import com.workfusion.bot.service.SecureEntryDTO;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.client.InvoicePlaneClient;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.dto.InvoiceDto;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.DashboardPage;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.LoginPage;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.invoices.CreateInvoicePage;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.invoices.ExtendedInvoiceCreationPage;
import com.workfusion.odf2.compiler.BotTask;
import com.workfusion.odf2.core.cdi.Injector;
import com.workfusion.odf2.core.task.generic.GenericTask;
import com.workfusion.odf2.core.task.rpa.RpaDriver;
import com.workfusion.odf2.core.task.rpa.RpaFactory;
import com.workfusion.odf2.core.task.rpa.RpaRunner;
import com.workfusion.odf2.core.webharvest.TaskInput;
import com.workfusion.odf2.core.webharvest.service.vault.SecretsVaultService;
import org.slf4j.Logger;

import javax.inject.Inject;

@BotTask(requireRpa = true)
public class InvoicePlaneNavigationTask implements GenericTask {

    private final RpaRunner rpaRunner;
    private final Logger logger;
    private final TaskInput taskInput;
    private final SecretsVaultService secretsVault;

    public static final String INVOICE_PLANE_CREDENTIALS_ALIAS = "invoice_plane_credentials";
    public static final String INPUT_COLUMN_NAME = "invoice_dto";

    @Inject
    public InvoicePlaneNavigationTask(Injector injector) {
        RpaFactory rpaFactory = injector.instance(RpaFactory.class);
        this.rpaRunner = rpaFactory.builder(RpaDriver.UNIVERSAL).closeOnCompletion(true).build();
        this.logger = injector.instance(Logger.class);
        this.taskInput = injector.instance(TaskInput.class);
        this.secretsVault = injector.instance(SecretsVaultService.class);
    }

    @Override
    public void run() {
        rpaRunner.execute(d -> {
            //Create client and go to login page
            InvoicePlaneClient client = new InvoicePlaneClient(this.logger);
            LoginPage loginPage = client.getLoginPage();

            SecureEntryDTO credentials = secretsVault.getEntry(INVOICE_PLANE_CREDENTIALS_ALIAS);
            DashboardPage dashboardPage = loginPage.login(credentials.getKey(), credentials.getValue());
            //Open invoice creation page by interacting with navigation menu
            CreateInvoicePage createInvoicePage = dashboardPage.topNavigation().invoices().createInvoice();

            //Get invoices info from task input
            String invoicesInput = taskInput.getRequiredVariable(INPUT_COLUMN_NAME);
            InvoiceDto invoiceDto = new Gson().fromJson(invoicesInput, InvoiceDto.class);

            //Create invoice, save and logout from the application
            ExtendedInvoiceCreationPage extendedInvoiceCreationPage = createInvoicePage.createInvoice(invoiceDto);
            extendedInvoiceCreationPage.fillInvoiceDataAndSave(invoiceDto);
            extendedInvoiceCreationPage.topNavigation().logout();
        });
    }
}
