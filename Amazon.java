// Authors: KM, AA, AL
// Date: 9/25/22


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;
import java.math.*;
import java.util.logging.Level;
import org.openqa.selenium.WebElement;



public class Amazon {

    private static final int numListed = 6;
    private static String [] names = new String [numListed];
    private static BigDecimal [] price = new BigDecimal [numListed];
    private static String [] images = new String [numListed];

    private static String [] enames = new String [numListed];
    private static String [] eprice = new String [numListed];
    private static String [] eimages = new String [numListed];

    private static String [] tnames = new String [numListed];
    private static String [] tprice = new String [numListed];
    private static String [] timages = new String [numListed];

    public static void main (String [] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\Kaden\\Java\\chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);

        ChromeDriver driver = new ChromeDriver();
        Scanner input = new Scanner(System.in);

        System.out.println("Enter an item: ");
        String item = input.nextLine();

        Item(driver, item);

        Ebay ebay = new Ebay();
        Etsy etsy = new Etsy ();
        ebay.eBay(driver, item, numListed, enames, eprice, eimages);
        etsy.etsy(driver, item, numListed, tnames, tprice, timages);

        writeResult(driver);
    }

    public static void Item (ChromeDriver driver, String item) {

        //driver.findElement(By.className("nav-line-1-container")).click();
        //driver.findElement(By.id("ap_email")).sendKeys("kajom95457@bongcs.com", Keys.ENTER);
        //driver.findElement(By.id("ap_email")).sendKeys("kajom95457", Keys.ENTER);

        driver.get("https://www.amazon.com/s?k=" + item + "&crid=7ABRGHBBQQ5W&sprefix=shoe%2Caps%2C392&ref=nb_sb_noss_2");

        List<WebElement> elements = driver.findElements(By.tagName("h2"));


        List<WebElement> wholeNum = driver.findElements(By.className("a-price-whole")); // Not finding anything, so idnex 0 out of 0
        List<WebElement> decimal = driver.findElements(By.className("a-price-fraction")); // Not in decimal form
        List<WebElement> img = driver.findElements(By.tagName("img"));

        for (int i = 0; i < numListed; i++) {
            // Puts in names
            names[i] = elements.get(i + 1).getText();

            // Calculates price
            String temp = wholeNum.get(i + 1).getText().replace("$", "") + "." + decimal.get(i+1).getText(); // Converts to decimal form

            // Puts in price
            try {
                price[i] = new BigDecimal(temp);
            } catch (Exception NumberFormatException) { // If there's no price given, you want to skip it
                price[i] = null;
            }

            images[i] = img.get(i + 8).getAttribute("src"); // Top 4 ads + 2
        }
    }


    public static void writeResult (ChromeDriver driver) {


        // Printing the item names and prices
        for (int i = 0; i < names.length; i++ ) {

            System.out.println("Item: " + names[i]);
            if (price[i] != null)
                System.out.println("Price: " + price[i]);
            else
                System.out.println("Price: No Price Listed. Not an advised buy.");

            System.out.println("Image link: " + images[i] + "\n");

            System.out.println("Item: " + enames[i]);
            System.out.println("Price: " + eprice[i]);
            System.out.println("Image link: " + eimages[i] + "\n");

            System.out.println("Item: " + tnames[i]);
            System.out.println("Price: " + tprice[i]);
            System.out.println("Image link: " + timages[i] + "\n");
        }
    }
}
