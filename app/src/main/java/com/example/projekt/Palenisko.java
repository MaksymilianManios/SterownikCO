package com.example.projekt;

public class Palenisko {
    private boolean czyPlonie;
    private Integer poziomPaliwaWPalenisku;
    public PodajnikPaliwa podajnik;

    public Palenisko()
    {
        this.czyPlonie = true;
        podajnik = new PodajnikPaliwa();
        this.poziomPaliwaWPalenisku = 100;
    }

    public Integer getPoziomPaliwaWPalenisku() {
        return poziomPaliwaWPalenisku;
    }

    public Boolean getOgi(){ return  czyPlonie;}

    public void setPoziomPaliwaWPalenisku(Integer poziomPaliwaWPalenisku) {
        this.poziomPaliwaWPalenisku = poziomPaliwaWPalenisku;
    }

    public void Zgas(){
        czyPlonie = false;
    }

    public void Rozpal(){
        if(getPoziomPaliwaWPalenisku() > 0) {
            czyPlonie = true;
        }
    }

    public void uzupelnijPaliwo(){
        setPoziomPaliwaWPalenisku(podajnik.PodajPaliwo());
    }
}
