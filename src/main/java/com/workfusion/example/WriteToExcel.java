package com.workfusion.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.workfusion.component.files.excel.SimpleExcelGenerator;

public class WriteToExcel {

    public static void main(String[] args) {
        WriteToExcel test = new WriteToExcel();
        try {
            test.run();
        } catch (IOException e) {
            throw new ExampleException("Error during excel creation", e);
        }
    }

    public void run() throws IOException {
        Path tempFile = Files.createTempFile("unit-test", ".xlsx");
        File file = tempFile.toFile();
        System.out.println("File path=" + file.getPath());
        file.deleteOnExit();

        FileOutputStream out = new FileOutputStream(file);

        List<String> headers = Arrays.asList("Header 1", "Header 2", "Header 3");
        List<Map<String, String>> rows = new ArrayList<>();
        rows.add(createRowMap(1));
        rows.add(createRowMap(2));
        rows.add(createRowMap(3));

        SimpleExcelGenerator.generate(out, headers, rows);
    }

    private Map<String, String> createRowMap(int rowIndex) {
        Map<String, String> result = new HashMap<>();
        result.put("Header 1", "Cell" + rowIndex + "1");
        result.put("Header 2", "Cell" + rowIndex + "2");
        result.put("Header 3", "Cell" + rowIndex + "3");
        return result;
    }

}
