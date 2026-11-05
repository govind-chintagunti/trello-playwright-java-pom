package base;

import java.nio.file.Paths;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

import pages.Homepage;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    @BeforeMethod
    public void BrowserOpen() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://trello.com/");
    }

    protected void navigateToLogin() {
        Homepage homepage = new Homepage(page);
        homepage.clickLoginButton();
    }

    protected void navigateToCreateAccount() {
        navigateToLogin();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Create an account")).click();
        page.waitForURL("**/signup**");
        page.getByRole(AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("Sign up for Trello")).waitFor();
    }

    public void screenShot(String fileName) {
        String timeStamp = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filePath = "screenshots/" + fileName + "_" + timeStamp + ".png";

        page.screenshot(
                new Page.ScreenshotOptions().setPath(Paths.get("screenshots/" + fileName + "_" + timeStamp + ".png")));
    }

    @AfterMethod
    public void BrowserClose() {
        page.close();
        browser.close();
        playwright.close();
    }

}
