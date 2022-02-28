package com.workfusion.examples.aa_examples_bots;

import com.workfusion.odf.test.launch.OutputData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class AbstractTestWithLogger {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractTestWithLogger.class);

    //Method is used to log task results that should be exported
    protected void logResults(OutputData outputData) {
        for (Map<String, String> map : outputData.getRecords()) {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(";");
            }
            sb.append("}");
            LOGGER.info(sb.toString());
        }
    }
}
