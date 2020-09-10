package com.example.projekt;

public class PiecCO extends Thread {
    // PALENISKO
    public Palenisko paleni;
    // NADMUCH
    public Dmuchawa dmuchawa;
    // POMPA
    public OEC pompa;
    public static final int TEMPERATURA_POKOJOWA = 20;

    // zmienne kontrolne :
    public Boolean czyMaSiePalic; // decyduje czy funkcja run ma przerwac petle
    private Integer spalanie = 0;
    private Integer grzanie = 0;
    private Integer temperatura = 0;

    public Boolean getOgien() {
        return paleni.getOgi();
    }

    public Integer getGrzanie() {
        return grzanie;
    }

    public void setGrzanie(Integer grzanie) {
        this.grzanie = grzanie;
    }

    public void setSpalanie(Integer spalanie) {
        this.spalanie = spalanie;
    }

    public Integer getSpalanie() {
        return spalanie;
    }
    public Integer getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }

    public void RozpalPiecCO() {
        if (paleni.getPoziomPaliwaWPalenisku() > 0) {
            paleni.Rozpal();
        }
    }

    public void ZamienPaliwoNaCieplo() {
        SpalPaliwo();
        PodgrzejWode();
    }

    public void szybkoscNagrzewania() {
        if (getOgien()) {
            if (dmuchawa.getPracaNadmuchu()) {
                setGrzanie(dmuchawa.getPredkoscNadmuchu());
                setSpalanie(20);
            } else {
                setGrzanie(1);
                setSpalanie(10);
            }
        } else {
            setGrzanie(0);
            setSpalanie(0);
        }
        if (pompa.getPracaPompy()) {
            setGrzanie(getGrzanie() - 2);
        }
    }

     public PiecCO(){
        czyMaSiePalic = true;
        dmuchawa = new Dmuchawa();
        pompa = new OEC();
        paleni = new Palenisko();
        spalanie = 0;
        temperatura = TEMPERATURA_POKOJOWA;
    }

    public void zgasPiec(){
         paleni.Zgas();
    }

    public void PodgrzejWode(){
        setTemperatura(getTemperatura() + getGrzanie());
    }
    public void SpalPaliwo(){
        paleni.setPoziomPaliwaWPalenisku(paleni.getPoziomPaliwaWPalenisku() - getSpalanie());
    }
}
