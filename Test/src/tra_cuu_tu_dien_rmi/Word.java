package tra_cuu_tu_dien_rmi;

public class Word {
    public String english;
    public String vietnamese;
    public String meaning;

    public Word() {
    }

    public Word(String english, String vietnamese, String meaning) {
        this.english = english;
        this.vietnamese = vietnamese;
        this.meaning = meaning;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return "english: " + english + " vietnamese: " + vietnamese + " meaning:" + meaning;

    }

}