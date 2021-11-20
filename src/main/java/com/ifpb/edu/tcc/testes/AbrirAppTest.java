package com.ifpb.edu.tcc.testes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AbrirAppTest {

	// get by id = buscar pelo resource-id
	// get by accessibility id = content desv
	private AndroidDriver<MobileElement> driver;
	private DesiredCapabilities capabilities = new DesiredCapabilities();

	private final String MATRICULA_USUARIO_TEST = "12345";
	private final String SENHA_USUARIO_TEST = "abc1234";

	@Before
	public void setupData() throws MalformedURLException {
		capabilities.setCapability("autoGrantPermissions", "true");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "emulator-5554");
		capabilities.setCapability("automationName", "uiautomator2");
		capabilities.setCapability("appPackage", "com.projeto_combate_incendios");
		capabilities.setCapability("appActivity", "com.projeto_combate_incendios.MainActivity");

		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(url, capabilities);

	}

	@Test
	public void deveFazerLoginComSucessoInformandoMatriculaEsenhaValida() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		MobileElement campoMatricula = driver.findElement(MobileBy.AccessibilityId("LoginPageRegistrationValue"));

		campoMatricula.sendKeys(MATRICULA_USUARIO_TEST);

		MobileElement campoSenha = driver.findElement(MobileBy.AccessibilityId("LoginPagePasswordValue"));

		campoSenha.sendKeys(SENHA_USUARIO_TEST);

		MobileElement botaoEntrar = driver.findElement(MobileBy.AccessibilityId("LoginButtonEntry"));

		Thread.sleep(2000);

		botaoEntrar.click();

		Thread.sleep(20000);
	}

	@After
	public void closeDriver() {
		driver.quit();

	}
}
