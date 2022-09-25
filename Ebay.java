// Authors: KM, AA, AL
// Date: 9/25/22

import org.openqa.selenium.*;
import java.util.List;
import org.openqa.selenium.WebElement;

public class Ebay {

    public void eBay(WebDriver driver, String item, int numListed, String [] enames, String [] eprice, String [] eimages) {

        driver.get("https://www.ebay.com/sch/i.html?_from=R40&_trksid=p2380057.m570.l1313&_nkw=" + item + "&_sacat=0");

        List<WebElement> elements = driver.findElements(By.className("s-item__title")); // List of default titles, to be put in enames
        List <WebElement> prices = driver.findElements(By.className("s-item__price")); // Default list of prices, to be put in eprice

        List <WebElement> img = driver.findElements(By.tagName("img"));

        for (int i = 0; i < numListed; i++) {
            // Puts in names
            enames[i] = elements.get(i + 1).getText();

            // Puts in price
            String temp = prices.get(i + 1).getText().replace("$", ""); // Converts to decimal form
            eprice[i] = temp;

           //System.out.println("Name: " + enames[i] + " Price: " + eprice[i]);

            // Puts in images
            eimages[i] = img.get(i + 2).getAttribute("src");
        }
    }
}
