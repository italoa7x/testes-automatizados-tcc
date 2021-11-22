package com.ifpb.edu.tcc.testes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

@FixMethodOrder(MethodSorters.JVM)

public class MapaTest {

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
	public void deveExpandirOmenuDeVisoesDoMapa() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		MobileElement campoMatricula = driver.findElement(MobileBy.AccessibilityId("LoginPageRegistrationValue"));

		campoMatricula.sendKeys(MATRICULA_USUARIO_TEST);

		MobileElement campoSenha = driver.findElement(MobileBy.AccessibilityId("LoginPagePasswordValue"));

		campoSenha.sendKeys(SENHA_USUARIO_TEST);

		MobileElement botaoEntrar = driver.findElement(MobileBy.AccessibilityId("LoginButtonEntry"));

		Thread.sleep(2000);

		botaoEntrar.click();

		Thread.sleep(5000);

		List<MobileElement> menuFlutuante = driver.findElements(MobileBy.className("android.view.ViewGroup"));

		for (MobileElement el : menuFlutuante) {
			System.out.println(el.getText());
		}

		// menuFlutuante.click();

		Thread.sleep(5000);
	}

	@Test
	public void deveAlterarAvisaoDoMapaParaAvisaoDeSateliteEvoltarParaAvisaoPadrao() throws InterruptedException {
		MobileElement menuFlutuante = driver.findElement(MobileBy.AccessibilityId("FloatingMenuButton"));
		menuFlutuante.click();
		Thread.sleep(3000);

		// procura a opcao de mapa de satelite e pressiona
		MobileElement opcaoMapaSatelite = driver.findElement(MobileBy.AccessibilityId("FloatingMenuOptionSatelite"));
		opcaoMapaSatelite.click();
		Thread.sleep(3000);

		// abre o menu e procura a opcao de mapa geografico (mapa padrao) e o pressiona
		menuFlutuante.click();
		Thread.sleep(3000);

		MobileElement opcaoMapaGeografico = driver.findElement(MobileBy.AccessibilityId("FloatingMenuOptionOutdoors"));
		opcaoMapaGeografico.click();
		Thread.sleep(3000);

	}

	@After
	public void closeDriver() {
		driver.quit();

	}
}
