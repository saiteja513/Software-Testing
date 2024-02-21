package org.example;

import java.util.Scanner;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class InSem {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        String choice;
        do {
            System.out.println("Choose an option:");
            System.out.println("1. Open browser");
            System.out.println("2. Open URL");
            System.out.println("3. Open browser with credentials");
            System.out.println("4. Open URL with invalid credentials");
            System.out.println("5. Implement static locators");
            System.out.println("6. Implement dynamic CSS Selector");
            System.out.println("7. Implement dynamic XPath");
            System.out.println("8. Implement HTML controls");
            System.out.println("9. Implement alerts");
            System.out.println("0. Exit");

            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    openBrowser();
                    break;
                case "2":
                    openUrl();
                    break;
                case "3":
                    openBrowserWithCredentials();
                    break;
                case "4":
                    openUrlWithInvalidCredentials();
                    break;
                case "5":
                    implementStaticLocators();
                    break;
                case "6":
                    implementDynamicCssSelector();
                    break;
                case "7":
                    implementDynamicXPath();
                    break;
                case "8":
                    implementHtmlControls();
                    break;
                case "9":
                    implementAlerts();
                    break;
                case "0":
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (!choice.equals("0"));
        scanner.close();
    }

    public static void openBrowser() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static void openUrl() {
        System.out.println("Enter the URL:");
        String url = scanner.nextLine();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    public static void openBrowserWithCredentials() {
        System.out.println("Enter the username:");
        String username = scanner.nextLine();
        System.out.println("Enter the password:");
        String password = scanner.nextLine();
        if (username.equals("admin") && password.equals("password")) {
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else {
            System.out.println("Invalid credentials. Browser cannot be opened.");
        }
    }



    public static void openUrlWithInvalidCredentials()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the URL:");
        String url = scanner.nextLine();
        System.out.println("Enter the username:");
        String username = scanner.nextLine();
        System.out.println("Enter the password:");
        String password = scanner.nextLine();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        WebElement usernameField = driver.findElement(By.name("email"));
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.name("pass"));
        passwordField.sendKeys(password);
        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[2]/div[2]"));
        if(errorMessage.getText().equals("Invalid credentials"))
        {
            System.out.println("Invalid credentials. Login failed.");
        }
        else
        {
            System.out.println("Login successful.");
        }
        driver.quit();
    }

    public static void implementStaticLocators()
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Java programming");
        searchBox.sendKeys(Keys.ENTER);
        WebElement resultsLink = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div"));
        resultsLink.click();
        driver.quit();
    }

    public static void implementDynamicCssSelector()
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://lms.kluniversity.in/login/index.php");
        highlight(driver,driver.findElement(By.cssSelector("#page-login-index")));
    }

    public static void implementDynamicXPath()
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://lms.kluniversity.in/login/index.php");
        highlight(driver,driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/section[1]")));
    }

    public static void implementHtmlControls() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.hyrtutorials.com/p/html-dropdown-elements-practice.html");
        WebElement singleDropdown = driver.findElement(By.id("course"));
        singleDropdown.sendKeys("Java");
        WebElement multiDropdown = driver.findElement(By.id("ide"));
        multiDropdown.sendKeys("Eclipse");
        Thread.sleep(3000);
        multiDropdown.sendKeys("IntelliJ IDEA");
        Thread.sleep(3000);
        multiDropdown.sendKeys("Visual Studio");

    }

    public static void implementAlerts() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testpages.eviltester.com/styled/alerts/alert-test.html");
        Thread.sleep(4000);
        driver.findElement(By.cssSelector("#alertexamples")).click();
        driver.switchTo().alert().accept();
        Thread.sleep(4000);
        driver.findElement(By.cssSelector("#confirmexample")).click();
        driver.switchTo().alert().accept();
        Thread.sleep(4000);
        driver.findElement(By.cssSelector("#promptexample")).click();
        driver.switchTo().alert().getText();
        Thread.sleep(4000);
        driver.switchTo().alert().sendKeys("Hi Saiteja");
        driver.switchTo().alert().accept();
    }
    public static void highlight(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }
}