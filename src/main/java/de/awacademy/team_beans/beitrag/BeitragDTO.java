package de.awacademy.team_beans.beitrag;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BeitragDTO {

    @NotEmpty /*@Size(min=4, message = "Was hast du der Welt zu sagen?")*/
    private String ueberschrift = "";
    @NotEmpty /*@Size(min=5, message = "Text vergessen?")*/
    private String text = "";

    private long id;

    public String getUeberschrift() {
        return ueberschrift;
    }

    public void setUeberschrift(String ueberschrift) {
        this.ueberschrift = ueberschrift;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
