package oleh.bilyk.pages.submenus;

import oleh.bilyk.Driver;
import oleh.bilyk.pages.charts.AjaxLineChart;
import oleh.bilyk.pages.charts.PieDrilldownChart;
import org.openqa.selenium.By;

public class PieChartSubmenu {
    private final By PIE_CHART_ITEM = By.cssSelector("[href='\\/demo\\/pie-basic']");
    private final By DONUT_CHART_ITEM = By.cssSelector("[href='\\/demo\\/pie-donut']");
    private final By PIE_DRILLDOWN_ITEM = By.cssSelector("[href='\\/demo\\/pie-drilldown']");

    public boolean verify(){
        return Driver.get().findElement(PIE_CHART_ITEM).isDisplayed();
    }

    public PieDrilldownChart openPieDrilldownChart(){
        Driver.get().findElement(PIE_DRILLDOWN_ITEM).click();
        return new PieDrilldownChart();
    }
}
