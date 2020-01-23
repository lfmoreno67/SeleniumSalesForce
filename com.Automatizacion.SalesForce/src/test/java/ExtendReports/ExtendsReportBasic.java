package ExtendReports;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.Object.Salesforce.ObjectSeleniumSalesForce;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtendsReportBasic extends ObjectSeleniumSalesForce {
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;

	public ExtendsReportBasic(WebDriver driver) {
		super(driver);
		htmlReporter = new ExtentHtmlReporter("Prueba_Reportes_SalesForce.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
	public ExtentTest crearTest(String nameTest, String description) {
		test = extent.createTest(nameTest, description);
		return test;
	}
	
	public void testResult(ExtentTest test, String nombre) throws IOException{
		
		String temp = null;
		temp=getScreenshot(nombre);
		test.pass("Validacion correcta", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
	}
	
	public void tearDown() {
		extent.flush();
	}

}
