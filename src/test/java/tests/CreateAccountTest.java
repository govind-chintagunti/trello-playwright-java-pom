package tests;

import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import base.BaseTest;

public class CreateAccountTest extends BaseTest {

    @Test
    public void testCreateAccount() {
        navigateToCreateAccount();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email"))
                .fill("chintaguntigovind1@gmail.com");
        page.locator("#signup-submit").click();
        page.locator("span:text('Close')").click();
        page.waitForTimeout(10000);
    }
}