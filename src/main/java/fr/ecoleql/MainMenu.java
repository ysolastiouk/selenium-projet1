package fr.ecoleql;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenu extends WebPage {

    @FindBy(xpath = "//button[contains(text(),'Calendrier')]")
    WebElement menuCalendrier;
    @FindBy(xpath = "//a[@class='z-menu-item-cnt'][contains(normalize-space(),'Projets')]")
    WebElement optionProjetMenuCalendrier;

    @FindBy(xpath = "//button[contains(text(),'Ressources')]")
    WebElement menuRessources;
    @FindBy(xpath = "//a[@href='/libreplan/resources/worker/worker.zul']") // mieux? --> //a[@class='z-menu-item-cnt'][contains(normalize-space(),'Participants')]
    WebElement menuRessourcesParticipant; //FIXME --> menuRessourcesParticipants (s manquant...) 
    @FindBy(xpath = "//a[@class='z-menu-item-cnt'][contains(normalize-space(),'Critère')]") 
    WebElement menuRessourcesCritere;

    @FindBy(xpath = "//button[contains(text(),'Coût')]")
    WebElement menuCout;
    @FindBy(xpath = "//button[contains(text(),'Configuration')]")
    WebElement menuConfiguration;
    @FindBy(xpath = "//button[contains(text(),'Communications')]")
    WebElement menuCommunications;
    @FindBy(xpath = "//button[contains(text(),'Rapports')]")
    WebElement menuRapports;
    @FindBy(xpath = "//button[contains(text(),'Zone personnelle')]")
    WebElement menuZonePersonnelle;

    @FindBy(partialLinkText = "Déconnexion")
    WebElement btnLogout;

    protected Actions actions;
    private WebDriverWait wait;

    public PageCritere clickMenuRessourcesCritere(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Instancier actions
        actions = new Actions(driver);
        // Se déplacer sur l'option
        actions.moveToElement(menuRessources, 0, 0).perform();
        // Attendre que la sous-option soit cliquable
        wait.until(ExpectedConditions.elementToBeClickable(menuRessourcesCritere));
        // Cliquer sur la sous-option
        menuRessourcesCritere.click();
        // Instancier la nouvelle page
        PageCritere pageCritere = PageFactory.initElements(driver, PageCritere.class);
        // Attendre que le bouton de création d'item soit cliquable
        wait.until(ExpectedConditions.elementToBeClickable(pageCritere.btnCreer));

        return pageCritere;
    }

    public PageParticipants clickMenuRessourcesParticipants(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Instancier actions
        actions = new Actions(driver);
        // Se déplacer sur l'option
        actions.moveToElement(menuRessources, 0, 0).perform();
        // Attendre que la sous-option soit cliquable
        wait.until(ExpectedConditions.elementToBeClickable(menuRessourcesParticipant));
        // Cliquer sur la sous-option
        menuRessourcesParticipant.click();
        // Instancier la nouvelle page
        PageParticipants pageParticipants = PageFactory.initElements(driver, PageParticipants.class);
        // Attendre que le bouton de création de c soit cliquable
        wait.until(ExpectedConditions.elementToBeClickable(pageParticipants.btnCreer));

        return pageParticipants;
    }

    public PageListeProjets clickMenuCalendierProjet(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Instancier actions
        actions = new Actions(driver);
        // Se déplacer sur l'option
        wait.until(ExpectedConditions.elementToBeClickable(menuCalendrier));
        wait.until(ExpectedConditions.elementToBeClickable(menuCalendrier));
        actions.moveToElement(menuCalendrier).build().perform();
        // Attendre que la sous-option soit cliquable
        wait.until(ExpectedConditions.elementToBeClickable(optionProjetMenuCalendrier));
        // Cliquer sur la sous-option
        optionProjetMenuCalendrier.click();
        // Instancier la nouvelle page "ListesProjets"
        PageListeProjets pageListeProjets = PageFactory.initElements(driver, PageListeProjets.class);
        // Attendre que le menu "Liste des projets" soit affiché
        wait.until(ExpectedConditions.elementToBeClickable(pageListeProjets.btnCreerProjet));

        return pageListeProjets;
    }

    public boolean isLoggedIn() {
        if (btnLogout.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

}
