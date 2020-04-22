package oleh.bilyk.pages.charts;

import oleh.bilyk.pages.MainPage;
import oleh.bilyk.webDriver.Driver;
import oleh.bilyk.webDriver.DriverWaiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static oleh.bilyk.webDriver.Driver.getDriver;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 22/04/2020
 * #Comments:
 */
public class AjaxLineChart extends MainPage {
    private static final By CHARTS = By.xpath("//*[contains(@class,'highcharts-markers')]");
    private static final By POINTS = By.xpath("*[contains(@class,'highcharts-point')]");

    public void printChartsLabel(){
        getCharts().stream().map(this::getChartLabel).forEach(System.out::println);
    }

    public void mouseOver(){
        Actions action = new Actions(getDriver());
        for (WebElement chart: getCharts()) {
            for (WebElement point:getPoints(chart)) {
                new DriverWaiter().sleep(300);
                action.moveToElement(point).build().perform();
            }
        }
    }

    private List<WebElement> getCharts(){
        return getDriver().findElements(CHARTS);
    }

    private List<WebElement> getPoints(WebElement chart){
        return chart.findElements(POINTS);
    }

    private String getChartLabel(WebElement chart){
        return chart.getAttribute("aria-label").toString().split(",")[0];
    }
}
