package com.workfusion.examples.aa_examples_bots.scrapwebtable.task;

import com.workfusion.examples.aa_examples_bots.scrapwebtable.automation.client.W3SchoolsClient;
import com.workfusion.examples.aa_examples_bots.scrapwebtable.automation.pages.W3SchoolsTablesPage;
import com.workfusion.odf2.compiler.BotTask;
import com.workfusion.odf2.core.cdi.Injector;
import com.workfusion.odf2.core.task.AdHocTask;
import com.workfusion.odf2.core.task.TaskInput;
import com.workfusion.odf2.core.task.output.MultipleResults;
import com.workfusion.odf2.core.task.output.SingleResult;
import com.workfusion.odf2.core.task.output.TaskRunnerOutput;
import com.workfusion.odf2.core.webharvest.rpa.RpaDriver;
import com.workfusion.odf2.core.webharvest.rpa.RpaFactory;
import com.workfusion.odf2.core.webharvest.rpa.RpaRunner;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@BotTask(requireRpa = true)
public class ScrapTableWithRpaTask implements AdHocTask {

    private final RpaRunner rpaRunner;
    private final Logger logger;

    @Inject
    public ScrapTableWithRpaTask(Injector injector) {
        RpaFactory rpaFactory = injector.instance(RpaFactory.class);
        this.rpaRunner = rpaFactory.builder(RpaDriver.UNIVERSAL).closeOnCompletion(true).build();
        this.logger = injector.instance(Logger.class);
    }

    @Override
    public TaskRunnerOutput run(TaskInput taskInput) {
        //AtomicReference object is used to have ability to get access and set results list from lambda expression
        AtomicReference<List<Map<String, String>>> tableData = new AtomicReference<>();
        rpaRunner.execute(d -> {
            //Create client
            W3SchoolsClient client = new W3SchoolsClient(this.logger);
            //Get W3SchoolsTablesPage instance
            W3SchoolsTablesPage tablesPage = client.getTablesPage();

            tableData.set(tablesPage.getCustomersTableData());
        });
        MultipleResults results = new MultipleResults();
        tableData.get().forEach(row -> results.addRow(new SingleResult(row)));
        return results;
    }
}
