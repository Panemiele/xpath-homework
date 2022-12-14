package it.uniroma3.ingdati.xpath.logic;

import it.uniroma3.ingdati.xpath.domain.ExtractedData;
import it.uniroma3.ingdati.xpath.domain.ExtractedLabeledData;
import it.uniroma3.ingdati.xpath.utils.Utils;
import it.uniroma3.ingdati.xpath.utils.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ExtractorLogic {

    private static final Logger logger = Logger.getLogger(ExtractorLogic.class.toString());
    private static final int MAX_WAIT_DURATION = 5;
    private static WebDriver driver;

    public ExtractorLogic() {
        System.setProperty("webdriver.edge.driver", Utils.getResourceFullPath(Utils.getDriversLocation()));
    }

    public ExtractedData extractData(String url, List<String> xpaths) {
        logger.info("ExtractorLogic - extractLabeledData(): url=" + url + ", xpaths=" + xpaths);

        ExtractedData extractedData = new ExtractedData();
        extractedData.setUrl(url);

        WebDriver driver = WebDriverSingleton.getInstance().getWebDriver();
        driver.get(url);

        for(String xpathExpression : xpaths) {
            String result = driver.findElement(By.xpath(xpathExpression)).getText();
            extractedData.getXpath2data().put(xpathExpression, result);
        }
        driver.close();

        logger.info("ExtractorLogic - extractLabeledData(): extractedData=" + extractedData);

        return extractedData;
    }

    public ExtractedLabeledData extractLabeledData(String url, Map<String,List<String>> label2xpath) {
        logger.info("ExtractorLogic - extractLabeledData(): url=" + url + ", label2xpath=" + label2xpath);

        ExtractedLabeledData extractedLabeledData = new ExtractedLabeledData();
        extractedLabeledData.setUrl(url);

        WebDriver driver = WebDriverSingleton.getInstance().getWebDriver();
        driver.get(url);

        for(Map.Entry<String,List<String>> label2xpathEntry : label2xpath.entrySet()) {
            String label = label2xpathEntry.getKey();

            extractedLabeledData.getLabel2xpathData().putIfAbsent(label,new HashMap<>());
            for(String xpath: label2xpathEntry.getValue()) {
                try {
                    String result = new WebDriverWait(driver, Duration.ofSeconds(MAX_WAIT_DURATION))
                            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).getText();
                    extractedLabeledData.getLabel2xpathData().get(label).put(xpath, result);
                } catch(Exception e) {
                    logger.info("Error while extracting " + xpath);
                    logger.info(e.getMessage());
                }
            }
        }

        logger.info("ExtractorLogic - extractLabeledData(): extractedLabeledData=" + extractedLabeledData);

        return extractedLabeledData;
    }


}
