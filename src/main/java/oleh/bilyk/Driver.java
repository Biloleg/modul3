package oleh.bilyk;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Driver {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver get(){
        if(driver.get()==null){
            driver.set(init());
        }
        return driver.get();
    }

    public static void kill(){
        driver.get().close();
        driver.get().quit();
        driver.remove();
    }

    private static WebDriver init() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
