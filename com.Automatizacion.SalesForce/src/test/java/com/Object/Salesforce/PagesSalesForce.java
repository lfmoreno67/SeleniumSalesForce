package com.Object.Salesforce;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PagesSalesForce extends ObjectSeleniumSalesForce{

	WebDriver driver;
	
	//Localizadores Pagina Login
	By user = By.id("username");
	By password = By.id("password");
	By login = By.id("Login");
	By mensaje = By.xpath("//span[@title='Colombia']");
	
	//Lozalizadores Menu Principal 
	By prospectos = By.xpath("//a[@title=\"Prospectos\"]");
	By title_prospectos = By.cssSelector("span[class='triggerLinkText selectedListView uiOutputText']");
	By Button_Nuevo = By.xpath("//div[@class='slds-truncate' and @title='Nuevo']");
	By titlePagNuevo = By.cssSelector("h2[class='inlineTitle slds-p-top--large slds-p-horizontal--medium slds-p-bottom--medium slds-text-heading--medium']");
	
	//Localizadores Menu Prospecto Nuevo
	By Menu_Desplegable = By.cssSelector("a[aria-required='true']");
	By Cedula = By.cssSelector("a[title='Cédula de Ciudadanía']");
	By Caja_Texto = By.cssSelector("input[class=' input']");
	By Empresa = By.cssSelector("a[title='Grande']");
	By Ciudad = By.cssSelector("input[maxlength='500']");
	By CiudadLista =By.xpath("//div[@title='Bogotá D.C.']");
	By Apellido = By.cssSelector("input[class='lastName compoundBLRadius compoundBRRadius form-element__row input']");
	By Guardar = By.cssSelector("button[title='Guardar']");
	
	public PagesSalesForce(WebDriver driver) {
		super(driver);
	}
	
	public void iniciarSesion(String usuario, String clave){
		getScreenshot("1_PaginaInicio");
		escribir(usuario, user);
		escribir(clave, password);
		getScreenshot("2_Login");
		clic(login);
		esperafluent(mensaje);
		getScreenshot("3_Login Correcto");
		if(estaEnPantalla(mensaje)) {
			System.out.println("El mensaje de COLOMBIA esta en pantalla");
		}else {
			System.out.println("El mensaje de COLOMBIA no esta en pantalla");
		}
	}
	
	public void prospectos(){
		
		ejecutadorJavaScript(prospectos);
		esperafluent(title_prospectos);
		getScreenshot("4_prospectos");
		if(estaEnPantalla(title_prospectos)) {
			System.out.println("El mensaje prospectos esta en pantalla");
			esperainp(10);
			clic(Button_Nuevo);
			esperafluent(titlePagNuevo);
			getScreenshot("5_Nuevo");
		}else{
			System.out.println("El mensaje prospectos no esta en pantalla");
		}
	}
	
	public void nuevoProspecto(String cedula, String cliente, String ciudad, String apellido, String correo){
		
		buscarElementosClick(Menu_Desplegable, 0);
		clic(Cedula);
		buscarElementosEscribir(Caja_Texto, 0, cedula);
		buscarElementosClick(Menu_Desplegable, 1);
		clic(Empresa);
		buscarElementosEscribir(Caja_Texto, 2, cliente);
		escribir(ciudad, Ciudad);
		getScreenshot("6_Datos");
		esperainp(15);
		buscarElementosClick(CiudadLista, 0);
		buscarElementosClick(Menu_Desplegable, 2);
		escribir(apellido, Apellido);
		buscarElementosEscribir(Caja_Texto, 3, correo);
		getScreenshot("7_Datos");
		clic(Guardar);
		getScreenshot("8_Guardar Datos");
	}
	

	
	
	
}
