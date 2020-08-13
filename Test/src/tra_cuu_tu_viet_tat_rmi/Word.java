package tra_cuu_tu_viet_tat_rmi;

import java.io.Serializable;

public class Word implements Serializable {

    private String acronym;
    private String full;

    public Word() {
    }

    public Word(String acronym, String full) {
        this.acronym = acronym;
        this.full = full;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

}