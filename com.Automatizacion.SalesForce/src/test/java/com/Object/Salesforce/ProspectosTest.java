package com.Object.Salesforce;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.DataDrivenExcel.Salesforce.ExcelDataProvider;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ExtendReports.ExtendsReportBasic;

public class ProspectosTest extends ExcelDataProvider{
	
	private WebDriver driver;
	PagesSalesForce salesforce = new PagesSalesForce(driver);
	ExtendsReportBasic test = new ExtendsReportBasic(driver);
	ExtentTest test1, test2;
	
  @BeforeClass
  public void beforeClass() {
	 test = new ExtendsReportBasic(driver);
	 test1=test.crearTest("Prueba Login", "Automatización del login SalesForce");
	 test2=test.crearTest("Prueba Prospectos", "Creación de un nuevo prospecto");
  }
  
  @BeforeTest
  public void setup() {
	salesforce = new PagesSalesForce(driver);
	driver = salesforce.chromeinicio();
	salesforce.visit("https://tigob2b--partial.my.salesforce.com/");
  }
  
  @DataProvider(name = "testDataLogin")
	public Object[][] getDataLogin() {
		Object data[][]= testData("Login");
		return data;
	}

  @Test(priority=1, dataProvider="testDataLogin")
  public void Login(String usuario, String clave) throws Exception {
	  salesforce.iniciarSesion(usuario, clave);
	  test1.pass("Inicio Sesión Correcto");
	  test.testResult(test1, "Logueo Correcto");
	  salesforce.prospectos();
	  test1.pass("Hizo clic en el menu prospectos");
	  test.testResult(test1, "Click en el menu prospectos");
	  test1.log(Status.DEBUG, "This step shows usage of log(status, details)");
  }
  
  @DataProvider(name = "testDataProspectos")
	public Object[][] getDataProspectos() {
		Object data[][]= testData("Prospectos");
		return data;
	}
  
  @Test(priority=2, dataProvider="testDataProspectos")
  public void Prospectos(String cedula, String cliente, String ciudad,
		  String apellido, String correo) throws InterruptedException, Exception {
	 
	  salesforce.nuevoProspecto(cedula, cliente, ciudad, apellido, correo);
	  test2.pass("Creo Prospecto");
	  test.testResult(test2, "Creacion Correcta");
	  test2.info("Uso General");
  }
  
  @AfterClass
  public void tearDown() throws IOException {
	 test.tearDown();
  }


}
