package com.Object.Salesforce;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ObjectSeleniumSalesForce {
	
	private static WebDriver driver;
	
	String path="./src/test/resources/Drivers/chromedriver.exe";
	
	public ObjectSeleniumSalesForce(WebDriver driver) {
		super();
		ObjectSeleniumSalesForce.driver = driver;
	}
	
	public WebDriver chromeinicio () {
		
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		
		return driver;
	}
	
	public void visit(String url) {
		driver.get(url);
	}
	
	public void escribir(String mensaje,By  locator) {
		driver.findElement(locator).sendKeys(mensaje);
	}
	
	public void clic(By locator) {
		driver.findElement(locator).click();
	}
	
	public boolean estaEnPantalla(By locator) {
		
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
		
	}
	public void  buscarElementosClick(By locator, int i){
		//Sentencia para crear la lista de Webelements
		List<WebElement> lista =driver.findElements(locator);
		/*Sentencia para seleccionar el WebElemet 
		en la lista con la posicion (i) que se recibe del parámetro
		de entrada y le da click al elemento*/
		lista.get(i).click();
		
	}
	
	public void  buscarElementosEscribir(By locator, int i, String palabra){
		//Sentencia para crear la lista de Webelements
		List<WebElement> lista =driver.findElements(locator);
		/*Sentencia para seleccionar el WebElemet 
		en la lista con la posicion (i) que se recibe del parámetro
		de entrada y escribe el texto en el WebElemet*/
		lista.get(i).sendKeys(palabra);
	}
	
	public void ejecutadorJavaScript(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void esperainp(int segundos) {
		driver.manage().timeouts().implicitlyWait(segundos, TimeUnit.SECONDS);
	}
	
	public void esperaexp(By locator) {
		WebDriverWait ewait = new WebDriverWait(driver, 10);
		ewait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void esperafluent(final By locator){
		
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).
				withTimeout(10, TimeUnit.SECONDS).
				pollingEvery(2, TimeUnit.SECONDS).
				ignoring(NoSuchElementException.class);
		
		WebElement objeto = fwait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver){
				return driver.findElement(locator);
				
			}
		}
		);
	}
	
	public String nombreObjeto (By locator) {
		WebElement objeto = driver.findElement(locator);
		return objeto.getText();
	}
	
	public static String getScreenshot(String nombre) {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = "./src/test/resources/Screenshots/"+nombre+".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(src,destination);
		} catch (IOException e) {
			System.out.println("Captura fallida"+e.getMessage());
		}
	 return path;
	}
	
	
	
}
