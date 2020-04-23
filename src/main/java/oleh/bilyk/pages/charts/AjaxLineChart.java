package oleh.bilyk.pages.charts;

import oleh.bilyk.helpers.StringHelper;
import oleh.bilyk.pages.MainPage;
import oleh.bilyk.webDriver.DriverWaiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private static final By TOOLTIP = By.cssSelector(".highcharts-tooltip > text");
    private DriverWaiter waiter = new DriverWaiter();

    public AjaxLineChart() {
        waiter.waitForElementDisplayed(CHARTS);
    }

    public void checkTooltipsValue() {
        Actions action = new Actions(getDriver());
        for (WebElement chart : getCharts()) {
            action.moveToElement(chart).build().perform();
            String chartLabel = getChartLabel(chart);
            for (WebElement point : getPoints(chart)) {
                action.moveToElement(point).build().perform();
                waiter.waitForElementDisplayed(TOOLTIP, 3000);
                double pointValue = getPointValue(point);
                double tooltipValue = getTooltipValueForChart(chartLabel);
                Assert.assertEquals(pointValue, tooltipValue,
                        String.format("Tooltip value '%s' is not equals to point value '%s' for chart '%s'",
                                tooltipValue, pointValue, chartLabel));
            }
        }
    }

    private double getTooltipValueForChart(String chartName) {
        String tooltipText = getDriver().findElement(TOOLTIP).getText();
        List<String> tooltipData = new ArrayList<>(Arrays.asList(tooltipText.split("●")));
        tooltipData.remove(0);
        Map<String, Double> tooltipValues = tooltipData.stream()
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(
                        a -> String.valueOf(a[0]).trim().toLowerCase(),
                        a -> StringHelper.parseStringToDouble(a[1])));
        return tooltipValues.get(chartName.toLowerCase());
    }

    private double getPointValue(WebElement point) {
        String pointData = point.getAttribute("aria-label");
        return StringHelper.parseStringToDouble(
                StringHelper.getMatchedGroup(
                        ".* (\\d+\\.?\\d+).*", pointData, 1));
    }

    private List<WebElement> getCharts() {
        return getDriver().findElements(CHARTS);
    }

    private List<WebElement> getPoints(WebElement chart) {
        return chart.findElements(POINTS);
    }

    private String getChartLabel(WebElement chart) {
        return chart.getAttribute("aria-label").split(",")[0];
    }
}
