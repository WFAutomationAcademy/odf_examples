package com.workfusion.examples.aa_examples_bots.webapplication.automation.pages;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.web.common.Button;
import com.workfusion.automation.rpa.elements.web.common.TextBox;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final TextBox txbEmail = new TextBox(driver, By.id("email"), "Email");
    private final TextBox txbPassword = new TextBox(driver, By.id("password"), "Password");
    private final Button btnLogin = new Button(driver, By.name("btn_login"), "Login");

    public LoginPage(DriverWrapper driver) {
        super(driver);
    }

    public DashboardPage login(String email, String password) {
        txbEmail.setText(email);
        txbPassword.setText(password);
        btnLogin.click();
        return new DashboardPage(driver);
    }
}
