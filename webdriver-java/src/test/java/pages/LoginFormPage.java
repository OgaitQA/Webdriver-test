package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage {
    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    // abordagem estrutural

    public LoginFormPage digitarLogin(String login){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);

        return this;
    }

    public  LoginFormPage digitarSenha(String password){
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);

        return this;
    }

    public SecretaPage clickSIGNIN(){
        // Clicar no link que possui o texto "Sign in"
        navegador.findElement(By.linkText("SIGN IN")).click();

        return new SecretaPage(navegador);
    }

    // abordagem funcional

    public SecretaPage fazerLogin(String login, String senha){
      digitarLogin(login);
      digitarSenha(senha);
      clickSIGNIN();

      return new SecretaPage(navegador);
    }
}
