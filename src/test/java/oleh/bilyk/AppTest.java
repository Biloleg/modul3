package oleh.bilyk;

import oleh.bilyk.pages.MainPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        Driver.get().navigate().to("https://www.highcharts.com/demo/line-basic");
 new MainPage().openPieChartSubmenu().openPieDrilldownChart();
 System.out.println("");
    }
}
