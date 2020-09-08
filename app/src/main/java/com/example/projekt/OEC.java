package com.example.projekt;

public class OEC { // Odprowadzenie Energi Cieplnej

    private Boolean pracaPompy;

    public OEC(){
        this.pracaPompy = false;
    }

    public Boolean getPracaPompy() {
        return pracaPompy;
    }

    public void setPracaPompy(Boolean pracaPompy) {
        this.pracaPompy = pracaPompy;
    }
}
