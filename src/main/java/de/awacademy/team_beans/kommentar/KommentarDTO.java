package de.awacademy.team_beans.kommentar;

import javax.validation.constraints.NotEmpty;

public class KommentarDTO {

    /**
     * Der Kommentar-DTO hat als einziges Attribut einen Inhalt. Andere Infos, die der Kommentar benötigt, wie User und
     * Timestamp, und Beitrags-ID kommen aus dem Repository bzw vom User-Controller
     */

    @NotEmpty
    private String inhalt = "";

    public String getInhalt()
    {
        return inhalt;
    }

    /**
     * Setter für den Inhalt wird von der View benötigt, damit ein Inhalt eingetragen und übergeben werdefn kann.
     * Auch wenn Intellij denkt, dass der Setter nicht beutzt wird! Sie wird benutzt! Nicht löschen!!!
     * @param inhalt
     */
    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }
}
