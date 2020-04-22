package oleh.bilyk.pages.submenus;

import oleh.bilyk.webDriver.Driver;
import oleh.bilyk.pages.charts.BasicColumnChart;
import org.openqa.selenium.By;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 22/04/2020
 * #Comments:
 */
public class ColumnBarChartSubmenu {
    private static final By BASIC_BAR_ITEM = By.cssSelector("[href='\\/demo\\/bar-basic']");
    private static final By BASIC_COLUMN_ITEM = By.cssSelector("[href='\\/demo\\/column-basic']");
    private static final By LABELS_LINE_ITEM = By.cssSelector("[href='\\/demo\\/line-labels']");

    //<editor-fold desc="Public Methods">
    public boolean verify(){
        return Driver.getDriver().findElement(BASIC_BAR_ITEM).isDisplayed();
    }

    public BasicColumnChart openBasicColumnChart(){
        Driver.getDriver().findElement(BASIC_COLUMN_ITEM).click();
        return new BasicColumnChart();
    }
    //</editor-fold>
}
