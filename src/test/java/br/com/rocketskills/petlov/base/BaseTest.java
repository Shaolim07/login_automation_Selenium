package br.com.rocketskills.petlov.base;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import br.com.rocketskills.petlov.base.TestListener;

public class BaseTest {
    protected WebDriver driver;
    // ThreadLocal so listener can access the driver for the current thread/test
    protected static final ThreadLocal<WebDriver> THREAD_DRIVER = new ThreadLocal<>();

    @RegisterExtension
    public static TestListener listener = new TestListener();

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        String headlessEnv = System.getenv("HEADLESS");
        String ciEnv = System.getenv("CI");
        boolean headless = "true".equalsIgnoreCase(headlessEnv) || "true".equalsIgnoreCase(ciEnv);
        if (headless) {
            // newer headless mode
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }

        // allow origins to avoid errors in some environments
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        THREAD_DRIVER.set(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                THREAD_DRIVER.remove();
            }
        }
    }

    public static WebDriver getDriver() {
        return THREAD_DRIVER.get();
    }
}

