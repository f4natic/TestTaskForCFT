import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestWeb {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {

        driver = new ChromeDriver();
        driver.get("http://www.rambler.ru/");
    }

    @AfterAll
    public static void dispose() {
        driver.quit();
    }

    @Test
    public void testWebSiteIsAlive() {
        String line = driver.getPageSource();
        Assertions.assertTrue(!line.isEmpty());
    }

}
