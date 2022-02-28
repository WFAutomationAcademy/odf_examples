package com.workfusion.examples.aa_examples_bots.notepad.automation.pages;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.web.common.Button;
import com.workfusion.automation.rpa.elements.web.common.TextBox;
import com.workfusion.automation.rpa.pages.AbstractPage;
import com.workfusion.examples.aa_examples_bots.notepad.automation.pages.popup.OpenFileDialog;
import com.workfusion.examples.aa_examples_bots.notepad.automation.pages.popup.SaveFilePromptPopup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

//Class represents Notepad window and provides methods to interact with it
public class MainPage extends AbstractPage {

    private final TextBox txbEditor = new TextBox(driver, By.cssSelector(".Edit"), "Text Editor");
    private final Button btnClose = new Button(driver, By.cssSelector(".Button[name=\"Close\"]"), "Close");

    private static final String MAIN_WINDOW_SELECTOR = "[CLASS:Notepad]";

    public MainPage(DriverWrapper driver) {
        super(driver);
    }

    public void typeText(String textToType) {
        txbEditor.sendKeys(textToType);
    }

    public void copyAllTextToClipboard() {
        txbEditor.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        txbEditor.sendKeys(Keys.chord(Keys.CONTROL, "c"));
    }

    public void pasteTextFromClipboardToTheBeginning() {
        txbEditor.sendKeys(Keys.chord(Keys.CONTROL, Keys.HOME));
        txbEditor.sendKeys(Keys.chord(Keys.CONTROL, "v"));
    }

    public void openFile(String pathToFile) {
        //Open open file dialog
        txbEditor.sendKeys(Keys.chord(Keys.CONTROL, "o"));
        SaveFilePromptPopup saveFilePromptPopup = new SaveFilePromptPopup(driver);
        OpenFileDialog openFileDialog = new OpenFileDialog(driver);
        //Wait for open file dialog is opened
        boolean isDialogOpened = wait.waitForTrue(y -> {
            if (saveFilePromptPopup.switchToDialog()) {
                //If prompt to save previous data is displayed, close it by click on "Don't Save"
                saveFilePromptPopup.dontSave();
            }
            return openFileDialog.switchToDialog();
        });
        if (!isDialogOpened) {
            throw new RuntimeException("Open File dialog wasn't found");
        }
        //Interact with open file dialog and open requested file
        openFileDialog.openFile(pathToFile);
        switchToNotepadWindow();
    }

    public void closeNotepad(boolean saveChangesIfPrompted) {
        btnClose.click();
        SaveFilePromptPopup saveFilePromptPopup = new SaveFilePromptPopup(driver);
        String currentWindowHandle = driver.getWindowHandle();
        //Wait for Notepad window is closed
        //(Set of handles of existed windows should not contain handle of Notepad window)
        boolean isNotepadClosed = wait.waitForTrue(y -> {
            if (saveFilePromptPopup.switchToDialog()) {
                //If prompt to save changes is displayed
                if (saveChangesIfPrompted) {
                    //save changes
                    saveFilePromptPopup.save();
                } else {
                    //don't save changes
                    saveFilePromptPopup.dontSave();
                }
            }
            return !driver.getWindowHandles().contains(currentWindowHandle);
        });
        if (!isNotepadClosed) {
            throw new RuntimeException("Could not close Notepad");
        }
    }

    public void switchToNotepadWindow() {
        boolean isSwitched = wait.waitForTrue(y ->
                driver.switchToWindow(MAIN_WINDOW_SELECTOR)
        );
        if (!isSwitched) {
            throw new RuntimeException("Open File dialog wasn't found");
        }
    }
}
