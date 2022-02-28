package com.workfusion.examples.aa_examples_bots.common.task;

import com.workfusion.odf2.core.task.OdfTask;
import com.workfusion.odf2.core.task.OdfTaskRunner;

import java.util.List;
import java.util.Map;

public interface GenericTaskMultipleResults extends OdfTask {

    @Override
    default Class<? extends OdfTaskRunner<?>> getRunnerClass() {
        return GenericTaskMultipleResultsRunner.class;
    }

    List<Map<String, String>> run();
}
