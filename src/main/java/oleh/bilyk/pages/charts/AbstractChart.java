package oleh.bilyk.pages.charts;

import oleh.bilyk.helpers.StringHelper;
import oleh.bilyk.webDriver.DriverWaiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

import static oleh.bilyk.webDriver.Driver.getDriver;

public abstract class AbstractChart {
    protected final By CHARTS;
    protected final By POINTS;
    protected final By TOOLTIP;
    protected DriverWaiter waiter = new DriverWaiter();

    protected AbstractChart(By chartsBy, By pointsBy, By tooltipBy) {
        CHARTS = chartsBy;
        POINTS = pointsBy;
        TOOLTIP = tooltipBy;
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

    protected abstract double getTooltipValueForChart(String chartName);

    protected double getPointValue(WebElement point) {
        String pointData = point.getAttribute("aria-label");
        return StringHelper.parseStringToDouble(
                StringHelper.getMatchedGroup(
                        ".* (\\d+\\.?\\d+).*", pointData, 1));
    }

    protected List<WebElement> getCharts() {
        return getDriver().findElements(CHARTS);
    }

    protected List<WebElement> getPoints(WebElement chart) {
        return chart.findElements(POINTS);
    }

    protected String getChartLabel(WebElement chart) {
        return chart.getAttribute("aria-label").split(",")[0];
    }
}
