# Testausdokumentti

Ohjelmaa on testattu sekä yksikkö- ja integraatiotasolla että järjestelmätasolla. Yksikkö- ja integraatiotason testit on tehty JUnit:lla ja järjestelmätestaus manuaalisesti.


## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

WorkhourService-luokassa testataan UserDao:n ja WorkhourEventDao:n yhteistoimintaa, jotka tallentavat ja lukevat dataa tilapäisestä testitietokannasta, joka poistetaan kun testit ovat suoritettu.


### DAO-luokat

Molemmat Dao-luokat ovat testattu luomalla tilapäinen testitietokanta, joka vastaa sovelluksen varsinaista tietokantaa, tallentamalla, sekä  lukemalladataa siitä. 

### Testauskattavuus

![JacocoReport](https://github.com/Mazuel/ohte-kevat-2020/blob/master/Images/JacocoReport.JPG)
