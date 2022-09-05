package com.workfusion.examples.aa_examples_bots.notepad.task;

import com.amazonaws.util.IOUtils;
import com.workfusion.automation.rpa.driver.DriverSettings;
import com.workfusion.automation.rpa.driver.DriverType;
import com.workfusion.automation.rpa.driver.RobotDriverWrapper;
import com.workfusion.examples.aa_examples_bots.notepad.automation.pages.MainPage;
import com.workfusion.odf2.compiler.BotTask;
import com.workfusion.odf2.core.cdi.Injector;
import com.workfusion.odf2.core.cdi.Requires;
import com.workfusion.odf2.core.task.TaskInput;
import com.workfusion.odf2.core.task.generic.GenericTask;
import com.workfusion.odf2.core.task.output.SingleResult;
import com.workfusion.odf2.core.task.output.TaskRunnerOutput;
import com.workfusion.odf2.core.webharvest.BpRunId;
import com.workfusion.odf2.core.webharvest.rpa.RpaDriver;
import com.workfusion.odf2.core.webharvest.rpa.RpaFactory;
import com.workfusion.odf2.core.webharvest.rpa.RpaRunner;
import com.workfusion.odf2.service.s3.S3Bucket;
import com.workfusion.odf2.service.s3.S3Module;
import com.workfusion.odf2.service.s3.S3Service;
import com.workfusion.rpa.helpers.RPA;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@BotTask(requireRpa = true)
@Requires(S3Module.class)
public class NotepadAndClipboardTask implements GenericTask {

    private final RpaRunner rpaRunner;
    private final Logger logger;
    private final TaskInput taskInput;
    private final S3Service s3Service;
    private final UUID bpRunId;

    public static final String INPUT_COLUMN_NAME = "text_to_type";

    private static final String S3_BUCKET = "workfusiondevelopment";
    private static final String S3_FOLDER = "test";
    private static final String FILE_IN_RESOURCES = "input/notepad/new.txt";

    @Inject
    public NotepadAndClipboardTask(Injector injector, @BpRunId UUID bpRunId) {
        RpaFactory rpaFactory = injector.instance(RpaFactory.class);
        this.rpaRunner = rpaFactory.builder(RpaDriver.UNIVERSAL).closeOnCompletion(true).build();
        this.logger = injector.instance(Logger.class);
        this.taskInput = injector.instance(TaskInput.class);
        this.s3Service = injector.instance(S3Service.class);
        this.bpRunId = bpRunId;
    }

    @Override
    public TaskRunnerOutput run() {
        AtomicReference<String> linkToFileOnS3 = new AtomicReference<>();
        rpaRunner.execute(d -> {
            DriverSettings settings = new DriverSettings()
                    .setElementWaitSeconds(90)
                    .setPageLoadWaitSeconds(90)
                    .setNeedToLogElementsActions(true);
            RobotDriverWrapper wrapper = new RobotDriverWrapper(this.logger, settings);
            wrapper.switchDriver(DriverType.DESKTOP);

            //Get file content from resources folder
            byte[] content = getResourceFileContent();

            S3Bucket s3Bucket = this.s3Service.getBucket(S3_BUCKET);

            String filePathToUpload = S3_FOLDER + "/" + this.bpRunId + "_" + FilenameUtils.getName(FILE_IN_RESOURCES);

            //Upload file to S3
            linkToFileOnS3.set(s3Bucket.put(content, filePathToUpload).getDirectUrl());
            logger.info("Link to uploaded file: {}", linkToFileOnS3);

            //Download file from S3
            content = s3Bucket.get(filePathToUpload);

            File tempFile;
            try {
                //Create temp file and store downloaded file content into it
                tempFile = File.createTempFile("test", ".txt");
                Files.write(tempFile.toPath(), content);
            } catch (IOException e) {
                throw new RuntimeException("An error occurred during creating file", e);
            }

            RPA.open("notepad.exe");
            MainPage notepad = new MainPage(wrapper);
            notepad.switchToNotepadWindow();

            String textToType = taskInput.getRequiredVariable(INPUT_COLUMN_NAME);

            notepad.typeText(textToType);
            notepad.copyAllTextToClipboard();
            notepad.pasteTextFromClipboardToTheBeginning();

            notepad.openFile(tempFile.getAbsolutePath());
            notepad.copyAllTextToClipboard();
            notepad.pasteTextFromClipboardToTheBeginning();

            notepad.closeNotepad(false);

            //Remove local temp file
            if (!tempFile.delete()) {
                //If file cannot be deleted now, mark it to be deleted later by JVM
                tempFile.deleteOnExit();
            }
        });
        return new SingleResult().withColumn("link_to_uploaded_file", linkToFileOnS3.get());
    }

    private byte[] getResourceFileContent() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_IN_RESOURCES);
        if (inputStream == null) {
            throw new RuntimeException(FILE_IN_RESOURCES + " is not found");
        }
        byte[] content;
        try {
            content = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred during reading resource file", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ignored) {
            }
        }
        return content;
    }
}
