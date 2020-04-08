package oleh.bilyk.pages;

import oleh.bilyk.Driver;
import oleh.bilyk.pages.submenus.ColumnBarChartSubmenu;
import oleh.bilyk.pages.submenus.LineChartSubmenu;
import oleh.bilyk.pages.submenus.PieChartSubmenu;
import org.openqa.selenium.By;

public class MainPage {
    private final By LINE_CHARTS_ITEM = By.xpath("//span[.='Line charts']");
    private final By AREA_CHARTS_ITEM = By.xpath("//span[.='Area charts']");
    private final By COLUMN_BAR_CHARTS_ITEM = By.xpath("//span[.='Column and bar charts']");
    private final By PIE_CHARTS_ITEM = By.xpath("//span[.='Pie charts']");

    public boolean verify(){
        return Driver.get().findElement(LINE_CHARTS_ITEM).isDisplayed()
                && Driver.get().findElement(AREA_CHARTS_ITEM).isDisplayed();
    }

    public LineChartSubmenu openLineCharts(){
        Driver.get().findElement(LINE_CHARTS_ITEM).click();
        return new LineChartSubmenu();
    }

    public ColumnBarChartSubmenu openColumnBarChartSubmenu(){
        Driver.get().findElement(LINE_CHARTS_ITEM).click();
        return new ColumnBarChartSubmenu();
    }

    public PieChartSubmenu openPieChartSubmenu(){
        Driver.get().findElement(PIE_CHARTS_ITEM).isDisplayed();
        Driver.get().findElement(PIE_CHARTS_ITEM).click();
        return new PieChartSubmenu();
    }
}
