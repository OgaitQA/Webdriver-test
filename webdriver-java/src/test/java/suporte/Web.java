package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Web {
    public static final String USERNAME = "tiagopereira5";
    public static final String AUTOMATE_KEY = "j1ostuLyTy5VZWhNATSQ";
    public static final String URLBROWSERSTACK = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static WebDriver createChrome() {
        // Abrindo o navegador
        System.setProperty("webdriver.chrome.driver","C:\\Users\\tiago\\drivers\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        navegador.manage().window().maximize();

        // Navegando para a página do Taskit!
        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;
    }

    public static WebDriver createrBrowserStack(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "72.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1920x1080");
        caps.setCapability("browserstack.debug", "true");

        WebDriver navegador = null;
        try {
             navegador = new RemoteWebDriver(new URL(URLBROWSERSTACK), caps);

             navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

             navegador.manage().window().maximize();

             // Navegando para a página do Taskit!
             navegador.get("http://www.juliodelima.com.br/taskit");
        } catch (MalformedURLException e) {
            System.out.println("Houveram problemas com a URL: " + e.getMessage());
        }

        return navegador;

    }
}
