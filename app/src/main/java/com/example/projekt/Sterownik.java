package com.example.projekt;

public class Sterownik extends PiecCO {
    //public Piec piec;
    private Boolean pracaPodajnika;
    private Integer tempZadana;
    private Boolean trybManualny;
    private Integer histereza;
    private Integer czasPracyPodajnika;
    private Integer czasPrzerwyPodajnika;
    private Integer temperaturaZalaczeniaPompy;
    private Integer czasPrzerwyPodtrzymania;
    private PiecCO piecCO;

    public Sterownik() {

        this.pracaPodajnika = false;
        this.tempZadana = 60;
        this.czasPracyPodajnika = 20;
        this.czasPrzerwyPodajnika = 40;
        this.temperaturaZalaczeniaPompy = 35;
        dmuchawa.setPredkoscNadmuchu(5);
        this.czasPrzerwyPodtrzymania = 5;
        this.histereza = 5;
        trybManualny = false;

    }

    //Gettery:


    public Integer getTemperaturaZalaczeniaPompy() {
        return temperaturaZalaczeniaPompy;
    }

    public Integer getCzasPrzerwyPodtrzymania() {
        return czasPrzerwyPodtrzymania;
    }

    public Boolean getPracaPodajnika() {
        return pracaPodajnika;
    }

    public Integer getTempZadana() {
        return tempZadana;
    }

    public Integer getCzasPracyPodajnika() {
        return czasPracyPodajnika;
    }

    public Integer getCzasPrzerwyPodajnika() {
        return czasPrzerwyPodajnika;
    }

    public Integer getHistereza() {
        return histereza;
    }

    public Boolean getTrybManualny() { return trybManualny;}




    //Settery:

    public void setTemperaturaZalaczeniaPompy(Integer temperaturaZalaczeniaPompy) {
        this.temperaturaZalaczeniaPompy = temperaturaZalaczeniaPompy;
    }

    public void setCzasPrzerwyPodtrzymania(Integer czasPrzerwyPodtrzymania) {
        this.czasPrzerwyPodtrzymania = czasPrzerwyPodtrzymania;
    }

    public void setPracaPodajnika(Boolean pracaPodajnika) {
        this.pracaPodajnika = pracaPodajnika;
    }

    public void setTempZadana(Integer tempZadana) {
        this.tempZadana = tempZadana;
    }

    public void setCzasPracyPodajnika(Integer czasPracyPodajnika) {
        this.czasPracyPodajnika = czasPracyPodajnika;
    }

    public void setCzasPrzerwyPodajnika(Integer czasPrzerwyPodajnika) {
        this.czasPrzerwyPodajnika = czasPrzerwyPodajnika;
    }

    public void setHistereza(Integer histereza) {
        this.histereza = histereza;
    }

    public void setTrybManualny() {
        if(trybManualny){
            trybManualny = false;
        }else trybManualny = true;

    }




}

