package com.workfusion.example;


import com.workfusion.automation.rpa.utils.ScreenshotUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MakeScreenshot {

    /*
     * To make a screenshot can be used ScreenshotUtils class from rpa-custom-elements library
     * */

    public static void main(String... args) {
        MakeScreenshot test = new MakeScreenshot();
        try {
            test.createScreenshot();
        } catch (AWTException | IOException e) {
            throw new ExampleException("An error occurred during creating of screenshot");
        }
    }

    private void createScreenshot() throws AWTException, IOException {

        //By default, screenshot will be made for whole screen of main display
        ScreenshotUtils screenshotUtils = new ScreenshotUtils();

        /*
         * To set needed area as area for screenshot can be used the following methods:
         * withAreaForScreenshot(Rectangle rectangle)
         * withAreaForScreenshot(int x, int y, int width, int height)
         * */

        //To get a screenshot of all active displays, need to use withAllDisplays() method
        screenshotUtils.withAllDisplays();

        /*
         * To make a screenshot and get it, can be used the following methods:
         * getScreenshotAsByteArrayOutputStream() - returns screenshot as ByteArrayOutputStream
         * getScreenshotAsByteArray() - returns screenshot as array of bytes
         * getScreenshotAsBase64String() - returns screenshot as base64 encoded string
         * getScreenshotAsFile() - saves screenshot to local file and returns File object
         * */

        File screenshotAsFile = screenshotUtils.getScreenshotAsFile();
        System.out.println("Screenshot file: " + screenshotAsFile.getAbsolutePath());
    }
}
