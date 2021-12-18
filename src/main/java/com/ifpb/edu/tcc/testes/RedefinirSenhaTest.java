package com.ifpb.edu.tcc.testes;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
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

public class RedefinirSenhaTest {

	// get by id = buscar pelo resource-id
	// get by accessibility id = content desv
	private AndroidDriver<MobileElement> driver;
	private DesiredCapabilities capabilities = new DesiredCapabilities();

	private final String MATRICULA_USUARIO_TEST = "201715020018";
	private final String SENHA_USUARIO_TEST = "24021998";

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
	public void tentaCaptarMensagemDeErroParaRedefinicaoDeSenhaInformandoDuasSenhasDiferentes()
			throws InterruptedException {
		String mensagemErro = "As senhas não conferem!";
		String novaSenha = "abc1234";
		String confirmacaoSenhaIncorreta = "abc123";
		// Login

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		// Realiza o login

		MobileElement campoMatricula = driver.findElement(MobileBy.AccessibilityId("LoginPageRegistrationValue"));

		campoMatricula.sendKeys(MATRICULA_USUARIO_TEST);

		MobileElement campoSenha = driver.findElement(MobileBy.AccessibilityId("LoginPagePasswordValue"));

		campoSenha.sendKeys(SENHA_USUARIO_TEST);

		MobileElement botaoEntrar = driver.findElement(MobileBy.AccessibilityId("LoginButtonEntry"));

		Thread.sleep(1500);

		botaoEntrar.click();

		Thread.sleep(2000);

		// Redireciona o usuário para a tela de redefinir senha

		MobileElement campoNovaSenha = driver.findElement(MobileBy.AccessibilityId("FirstLoginInputNewPassword"));

		campoNovaSenha.sendKeys(novaSenha);

		MobileElement campoConfirmacaoNovaSenha = driver
				.findElement(MobileBy.AccessibilityId("FirstLoginInputConfirmNewPassword"));

		campoConfirmacaoNovaSenha.sendKeys(confirmacaoSenhaIncorreta);

		Thread.sleep(2000);

		// captar o erro
		MobileElement labelError = driver.findElement(MobileBy.AccessibilityId("FirstLoginPasswordsInvalids"));

		assertEquals(mensagemErro, labelError.getText());

	}

	@Test
	public void testaAvalidacaoDoTamanhoDaSenhaEseSaoIguais() throws InterruptedException {
		String mensagemSenhasValidadas = "As senhas são válidas!";
		String novaSenha = "abc1234";
		String confirmacaoSenhaIncorreta = "abc1234";
		// Login

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		// Realiza o login

		MobileElement campoMatricula = driver.findElement(MobileBy.AccessibilityId("LoginPageRegistrationValue"));

		campoMatricula.sendKeys(MATRICULA_USUARIO_TEST);

		MobileElement campoSenha = driver.findElement(MobileBy.AccessibilityId("LoginPagePasswordValue"));

		campoSenha.sendKeys(SENHA_USUARIO_TEST);

		MobileElement botaoEntrar = driver.findElement(MobileBy.AccessibilityId("LoginButtonEntry"));

		Thread.sleep(1500);

		botaoEntrar.click();

		Thread.sleep(2000);

		// Redireciona o usuário para a tela de redefinir senha

		MobileElement campoNovaSenha = driver.findElement(MobileBy.AccessibilityId("FirstLoginInputNewPassword"));

		campoNovaSenha.sendKeys(novaSenha);

		MobileElement campoConfirmacaoNovaSenha = driver
				.findElement(MobileBy.AccessibilityId("FirstLoginInputConfirmNewPassword"));

		campoConfirmacaoNovaSenha.sendKeys(confirmacaoSenhaIncorreta);

		Thread.sleep(2000);

		MobileElement botaoAlterar = driver.findElement(MobileBy.AccessibilityId("FirstLoginButtonChangePassword"));
		// captar a mensagem de senhas validas
		MobileElement labelSenhasValidas = driver.findElement(MobileBy.AccessibilityId("FirstLoginPasswordsValids"));

		assertEquals(mensagemSenhasValidadas, labelSenhasValidas.getText());

	}

	@After
	public void closeDriver() {
		driver.quit();

	}
}
