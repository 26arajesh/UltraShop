// Authors: KM, AA, AL
// Date: 9/25/22

import org.openqa.selenium.*;
import java.util.List;
import org.openqa.selenium.WebElement;

public class Etsy {

    public void etsy (WebDriver driver, String item, int numListed, String [] tnames, String [] tprice, String [] timages) {

        driver.get("https://www.etsy.com/search?q="+ item + "&explicit=1&order=highest_reviews");

        List <WebElement> elements = driver.findElements(By.tagName("h3")); // List of default titles, to be put in enames
        List <WebElement> prices = driver.findElements(By.xpath("//span[@class = 'currency-value']")); // Default list of prices, to be put in eprice

        //System.out.println("Length of prices: " + prices.size());

        List <WebElement> img = driver.findElements(By.tagName("img"));

        for (int i = 0; i < numListed; i++) {
            // Puts in names
            tnames[i] = elements.get(i + 1).getText();

            // Puts in price
            tprice[i] = prices.get(i + 1).getText().replace("$", ""); // Converts to decimal form

            //System.out.println("Name: " + tnames[i] + " Price: " + tprice[i]);

            // Puts in images
            timages[i] = img.get(i + 8).getAttribute("src"); // +8 for backpack, +6 for red shoes
        }
    }
}