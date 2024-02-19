package gr.unipi.CountryStructure;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Name {
    private String common;
    private String official;

    // Το JsonIgnore το χρησιμοποιούμε για να αγνοήσουμε το πεδίο nativeName κατά την αποσειριοποίηση του JSON
    @JsonIgnore
    private String nativeName;

    public Name() {
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}