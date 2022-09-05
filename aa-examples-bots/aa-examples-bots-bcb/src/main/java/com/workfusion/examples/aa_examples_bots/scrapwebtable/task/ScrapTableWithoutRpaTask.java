package com.workfusion.examples.aa_examples_bots.scrapwebtable.task;

import com.workfusion.examples.aa_examples_bots.common.utils.HttpRequester;
import com.workfusion.odf2.compiler.BotTask;
import com.workfusion.odf2.core.task.AdHocTask;
import com.workfusion.odf2.core.task.TaskInput;
import com.workfusion.odf2.core.task.output.MultipleResults;
import com.workfusion.odf2.core.task.output.SingleResult;
import com.workfusion.odf2.core.task.output.TaskRunnerOutput;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@BotTask
public class ScrapTableWithoutRpaTask implements AdHocTask {

    private static final String URL_TO_USE = "http://www.w3schools.com/html/html_tables.asp";

    public ScrapTableWithoutRpaTask() {
    }

    @Override
    public TaskRunnerOutput run(TaskInput taskInput) {

        //Get page content by executing of GET request
        HttpRequester httpRequester = new HttpRequester();
        String response = httpRequester.sendGet(URL_TO_USE, null);

        //Parse page content into TagNode object
        HtmlCleaner html = new HtmlCleaner();
        TagNode document = html.clean(response);

        //Get table element
        TagNode table = document.findElementByAttValue("id", "customers", true, false);

        List<Map<String, String>> result = new ArrayList<>();
        try {
            //Search rows (headers row and rows with data) in subtree of table element
            Object[] headers = table.evaluateXPath(".//th");
            Object[] rows = table.evaluateXPath(".//tr[td]");
            for (Object row : rows) {
                //Get cells as children elements of every row
                TagNode[] cells = ((TagNode) row).getChildTags();
                Map<String, String> headersWithValues = new LinkedHashMap<>();
                for (int i = 0; i < cells.length; i++) {
                    //Add pairs of cell value and appropriate header into map
                    headersWithValues.put(((TagNode) headers[i]).getText().toString(), cells[i].getText().toString());
                }
                if (!headersWithValues.isEmpty()) {
                    //Add mapped row to list with rows
                    result.add(headersWithValues);
                }
            }
        } catch (XPatherException e) {
            throw new RuntimeException("An error occurred during parsing page content", e);
        }

        MultipleResults results = new MultipleResults();
        result.forEach(row -> results.addRow(new SingleResult(row)));
        return results;
    }
}
