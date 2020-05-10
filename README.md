# Ohjelmistotekniikka kevät 2020

## Dokumentaatio

[Käyttöohje](https://github.com/Mazuel/ohte-kevat-2020/blob/master/WorkingHours/documentation/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/Mazuel/ohte-kevat-2020/blob/master/WorkingHours/documentation/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/Mazuel/ohte-kevat-2020/blob/master/WorkingHours/documentation/tuntikirjanpito.md)

[Testidokumentti](https://github.com/Mazuel/ohte-kevat-2020/blob/master/WorkingHours/documentation/testaus.md)

## Releaset

Viikko 5: [Ensimmäinen release](https://github.com/Mazuel/ohte-kevat-2020/releases/tag/viikko5)

## Komentorivin toiminnot
Kaikki komennot täytyy suorittaa hakemistossa WorkingHours

Käynnistys komentoriviltä:

```mvn compile exec:java -Dexec.mainClass=workinghours.Main```

Jar-tiedoston luominen ja käynnistäminen:
1. mvn package
2. java -jar target/WorkingHours-0.0.1-SNAPSHOT.jar

Testien suorittaminen:

```mvn test```

Testikattavuusraportin luominen:

```mvn test jacoco:report```

Checkstyle-raportin luominen:

```mvn jxr:jxr checkstyle:checkstyle```
