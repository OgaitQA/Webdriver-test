package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MePage extends BasePage {
    public MePage(WebDriver navegador) {
        super(navegador);
    }

    public MePage clicarAbaMoreDataAboutYou(){
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

        return this;
    }

    public AddContactPage clicaBotaoAddMoreDataAboutYou(){
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        return new AddContactPage(navegador);
    }

    public MePage clicaIconeDeletar(String contato){
        navegador.findElement(By.xpath("//span[text()=\""+ contato + "\"]/following-sibling::a")).click();

        return this;
    }

    public MePage confirmarJanelaJava(){
        navegador.switchTo().alert().accept();

        return this;
    }
}
