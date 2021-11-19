package com.ifpb.edu.tcc.testes;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AbrirAppTest {

	private AndroidDriver<MobileElement> driver;
	DesiredCapabilities capabilities = new DesiredCapabilities();

	@Before
	public void setupData() throws MalformedURLException {
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "emultador-5544");
		capabilities.setCapability("automationName", "uiautomator2");
		capabilities.setCapability("appPackage", "com.projeto_combate_incendios");
		capabilities.setCapability("appActivity", "com.projeto_combate_incendios.MainActivity");

		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(url, capabilities);

	}


	@Test
	public void testaAbrirOAppEfazerLogin() throws InterruptedException {
		MobileElement campoMatricula = (MobileElement) driver.findElementById("LoginRegistration");

		campoMatricula.click();
		campoMatricula.sendKeys("12345");

		Thread.sleep(2000);

		MobileElement campoSenha = (MobileElement) driver.findElementById("LoginPassword");

		campoSenha.sendKeys("12345");

		MobileElement botaoEntrar = (MobileElement) driver.findElementById("LoginButton");

		Thread.sleep(2000);

		botaoEntrar.click();

		Thread.sleep(4000);

		driver.quit();

	}
}
