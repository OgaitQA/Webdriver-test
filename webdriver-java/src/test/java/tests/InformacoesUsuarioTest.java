package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTest.csv")
//@DataLoader(filePaths = "removerUmContatoDeUmUsuario.csv")

public class InformacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){
        // Abrindo o navegador
        System.setProperty("webdriver.chrome.driver","C:\\Users\\tiago\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        navegador.manage().window().maximize();

        // Navegando para a página do Taskit!
        navegador.get("http://www.juliodelima.com.br/taskit");

        // Clicar no link que possui o texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click();

        // Identificando o formulário de login
        WebElement formularioSignInbox = navegador.findElement(By.id("signinbox"));

        // Digitar  no campo com name "login" que está dentro do formulário de id "signinbox" o texto "julio0001"
        formularioSignInbox.findElement(By.name("login")).sendKeys("julio0001");

        // Digitar  no campo com name "password" que está dentro do formulário de id "signinbox" o texto "123456"
        formularioSignInbox.findElement(By.name("password")).sendKeys("123456");

        // Clicar no link que possui o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        // Clicar no link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        // Clicar no link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name ="tipo")String tipo,@Param(name = "contato")String contato,@Param(name = "mensagem")String mensagemEsperada) {

        // Clicar no botão através do seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Identificar a popup onde está o formulário de id addmoredata
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        // Na combo de name "type" escolher a opção "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        // No campo de name "contact" digitar "+5571982451367"
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        // Clicar no link de text "SAVE" que está na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        // Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada, mensagem);

        //Realizar o Screenshot ao final de cada teste
        String screenshotArquivo = "C:\\Users\\tiago\\test-report\\taskit\\" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, screenshotArquivo);
    }

    @Test
    public void removerUmContatoDeUmUsuario(@Param(name = "contato")String contato, @Param(name = "mensagem")String mensagemEsperada){
        // Clicar no elemento pelo seu Xpath //span[text()="+557923456789"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\""+ contato + "\"]/following-sibling::a")).click();

        // Confirmar a janela javascript
        navegador.switchTo().alert().accept();

        // Validar que a mensagem apresentada foi Rest in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada, mensagem);

        String screenshotArquivo = "C:\\Users\\tiago\\test-report\\taskit\\" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, screenshotArquivo);

        // Aguardar até 10 segundos para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador,10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        // Clicar no link com o texto "Logout"
        navegador.findElement(By.linkText("Logout")).click();

    }

    @After
     public void TearDown(){
        // Fechar o navegador
        navegador.quit();
    }
}
