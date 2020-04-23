package oleh.bilyk;

import oleh.bilyk.pages.MainPage;
import oleh.bilyk.webDriver.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class AppTest {
@AfterMethod
public void tearDown(){
    Driver.kill();
}
    @Test
    public void lineChartTest() {
        new MainPage().openLineCharts().openAjaxLineChart().checkTooltipsValue();
    }

    @Test
    public void columnChartTest() {
        new MainPage().openColumnBarChartSubmenu().openBasicColumnChart().checkTooltipsValue();
    }
}
