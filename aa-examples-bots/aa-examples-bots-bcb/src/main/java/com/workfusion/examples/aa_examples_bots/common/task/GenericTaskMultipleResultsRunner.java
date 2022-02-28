package com.workfusion.examples.aa_examples_bots.common.task;

import com.workfusion.odf2.core.task.OdfTaskRunner;
import com.workfusion.odf2.core.task.result.MultipleResults;
import com.workfusion.odf2.core.task.result.TaskResult;

import java.util.stream.Collectors;

public class GenericTaskMultipleResultsRunner implements OdfTaskRunner<GenericTaskMultipleResults> {

    @Override
    public MultipleResults run(GenericTaskMultipleResults task) {
        return TaskResult.of(task.run().stream().map(TaskResult::of).collect(Collectors.toList()));
    }
}
