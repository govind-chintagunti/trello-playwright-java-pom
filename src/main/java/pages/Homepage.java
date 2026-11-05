package pages;

import com.microsoft.playwright.Page;

public class Homepage {
    private Page page;

    public Homepage(Page page) {
        this.page = page;
    }

    public void clickLoginButton() {
        page.getByText("Log in").first().click();
    }
}
