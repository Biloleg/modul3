package oleh.bilyk.pages.submenus;

import oleh.bilyk.Driver;
import oleh.bilyk.pages.MainPage;
import oleh.bilyk.pages.charts.AjaxLineChart;
import org.openqa.selenium.By;

public class LineChartSubmenu extends MainPage {
    private final By BASIC_LINE_ITEM = By.cssSelector("[href='\\/demo\\/line-basic']");
    private final By AJAX_LINE_ITEM = By.cssSelector(".nav-sidebar [href='\\/demo\\/line-ajax']");
    private final By LABELS_LINE_ITEM = By.cssSelector("[href='\\/demo\\/line-labels']");

    public boolean verify(){
        return Driver.get().findElement(BASIC_LINE_ITEM).isDisplayed();
    }

    public AjaxLineChart openAjaxLineChart(){
        Driver.get().findElement(AJAX_LINE_ITEM).click();
        return new AjaxLineChart();
    }
}
