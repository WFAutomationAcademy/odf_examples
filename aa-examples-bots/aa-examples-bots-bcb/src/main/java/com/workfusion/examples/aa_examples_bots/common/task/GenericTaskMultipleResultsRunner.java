package com.workfusion.examples.aa_examples_bots.common.task;

import com.workfusion.odf2.core.task.OdfTaskRunner;
import com.workfusion.odf2.core.task.output.MultipleResults;
import com.workfusion.odf2.core.task.output.SingleResult;

public class GenericTaskMultipleResultsRunner implements OdfTaskRunner<GenericTaskMultipleResults> {

    @Override
    public MultipleResults run(GenericTaskMultipleResults task) {
        MultipleResults results = new MultipleResults();
        task.run().forEach(row -> results.addRow(new SingleResult(row)));
        return results;
    }
}
