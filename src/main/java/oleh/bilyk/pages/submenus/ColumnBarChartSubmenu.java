package oleh.bilyk.pages.submenus;

import oleh.bilyk.Driver;
import oleh.bilyk.pages.charts.AjaxLineChart;
import oleh.bilyk.pages.charts.BasicColumnChart;
import org.openqa.selenium.By;

public class ColumnBarChartSubmenu {
    private final By BASIC_BAR_ITEM = By.cssSelector("[href='\\/demo\\/bar-basic']");
    private final By BASIC_COLUMN_ITEM = By.cssSelector("[href='\\/demo\\/column-basic']");
    private final By LABELS_LINE_ITEM = By.cssSelector("[href='\\/demo\\/line-labels']");

    public boolean verify(){
        return Driver.get().findElement(BASIC_BAR_ITEM).isDisplayed();
    }

    public BasicColumnChart openBasicColumnChart(){
        Driver.get().findElement(BASIC_COLUMN_ITEM).click();
        return new BasicColumnChart();
    }
}
