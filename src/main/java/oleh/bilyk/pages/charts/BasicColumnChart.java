package oleh.bilyk.pages.charts;

import oleh.bilyk.helpers.StringHelper;
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
public class BasicColumnChart {
    private static final By CHARTS = By.cssSelector(".highcharts-series-group > .highcharts-series");
    private static final By POINTS = By.xpath("*[contains(@class,'highcharts-point') and contains(@class,'highcharts-color-')]");
    private static final By TOOLTIP = By.cssSelector(".highcharts-container tbody tr");
    private DriverWaiter waiter = new DriverWaiter();

    public BasicColumnChart() {
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
        Map<String, Double> tooltipValues =
                getDriver().findElements(By.cssSelector(".highcharts-container tbody tr"))
                        .stream()
                        .map(e -> e.getAttribute("innerText").split(":\\t"))
                        .collect(Collectors.toMap(
                                a -> String.valueOf(a[0]).trim().toLowerCase(),
                                a -> StringHelper.parseStringToDouble(
                                        StringHelper.getMatchedGroup("(\\d+\\.\\d+).*", a[1], 1)
                                )));
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
