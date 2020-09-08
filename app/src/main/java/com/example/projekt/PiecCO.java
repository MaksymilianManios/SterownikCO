package com.example.projekt;

public class PiecCO extends Thread {
    // PALENISKO
    public static final int WSPOLCZYNNIK_PRACY_I = 0; // - piec sie nie pali
    public static final int WSPOLCZYNNIK_PRACY_II = 1; // - nadmuch OFF
    public static final int WSPOLCZYNNIK_PRACY_III = 2; // - nadmuch ON

    // PODAJNIK
    public static final int POJEMNOSC_ZASOBNIKA = 2000;
    public static final int POJEMNOSC_PALENISKA = 100;
    public static final int UZUPELNIENIE_ZASOBNIKA = 250;
    public static final int DAWKA_PALIWA = 10;

    // NADMUCH
    public Dmuchawa dmuchawa;
    // POMPA
    public OEC pompa;
    public static final int TEMPERATURA_DOCELOWA = 60;
    public static final int TEMPERATURA_POKOJOWA = 20;
    public static final int AMPLITUDA_TEMPERATUR = TEMPERATURA_DOCELOWA - TEMPERATURA_POKOJOWA;
    // CZAS PRACY AKTYWNEJ
    public static final int CZAS_MAKSYMALNY_CALKOWITY = 40;
    public static final int CZAS_MAKSYMALNY_W_NADMUCHU = CZAS_MAKSYMALNY_CALKOWITY/WSPOLCZYNNIK_PRACY_III/2;


    // zmienne kontrolne :
    private Boolean ogien;     // <-true jesli sie pali
   /* private Boolean podajnikPracuje; // <-true jesli pracuje
    private Boolean nadmuchPracuje;  // <-true jesli pracuje
    private Boolean pompaPracuje;    // <-true jesli pracuje
    */
    public Boolean czyMaSiePalic; // decyduje czy funkcja run ma przerwac petle
    public Integer czasPracyZNadmuchem = 10;
    public Integer czasPracyBezNadmuchu = 20;
    private Integer czasPracyAktywnej = 30;
    private Integer poziomPaliwaWPiecu;
    private Integer zapasPaliwaWZasobniku;
    private Integer spalanie = 0;
    private Integer grzanie = 0;
    private Integer temperatura = 0;


    public void setPoziomPaliwaWPiecu(Integer poziomPaliwaWPiecu) {
        this.poziomPaliwaWPiecu = poziomPaliwaWPiecu;
    }

    public void setZapasPaliwaWZasobniku(Integer zapasPaliwaWZasobniku) {
        this.zapasPaliwaWZasobniku = zapasPaliwaWZasobniku;
    }





     // Opakowana funkja zapalajaca kontrolke pompy
  // Ustala Wspolczynnik Pracy na podstawie stanu zmiennych kontrolnych



    // Gettery :
   public void setCzasPracyAktywnej(Integer czasPracyAktywnej) {
       this.czasPracyAktywnej = czasPracyAktywnej;
   }

    public Boolean getOgien() {
        return ogien;
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
    public Integer getPoziomPaliwaWPiecu() {
        return poziomPaliwaWPiecu;
    }
    public Integer getZapasPaliwaWZasobniku() {
        return zapasPaliwaWZasobniku;
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
    public Integer getCzasPracyZNadmuchem(){
        return czasPracyZNadmuchem;
    }
    public Integer getCzasPracyBezNadmuchu(){
        return czasPracyBezNadmuchu;
    }
    public Integer getCzasPracyAktywnej(){
        return czasPracyAktywnej;
    }
    /*
    public String getNadmychDmucha(){
        if(nadmuchPracuje){
            return "Z Nadmuchem";
        }else{
            return "Bez Nadmuchu";
        }
    }

     */
    // Settery :
    public void SetCzasPracyAktywnej(int CzasPracyAktywnej,int CzasPracyZNadmuchem){
        if(CzasPracyAktywnej>CZAS_MAKSYMALNY_CALKOWITY){
            CzasPracyAktywnej = CZAS_MAKSYMALNY_CALKOWITY;
        }else if(CzasPracyAktywnej<CZAS_MAKSYMALNY_W_NADMUCHU)
            CzasPracyAktywnej = CZAS_MAKSYMALNY_W_NADMUCHU;
        if(CzasPracyZNadmuchem > CZAS_MAKSYMALNY_W_NADMUCHU){
            CzasPracyZNadmuchem = CZAS_MAKSYMALNY_W_NADMUCHU;
        }
        SetczasPracyZNadmuchem(CzasPracyZNadmuchem);
        czasPracyAktywnej = CzasPracyAktywnej;
        SetczasPracyBezNadmuchu();
    }
    public void SetczasPracyZNadmuchem(int czas){
        czasPracyZNadmuchem = czas;
    }
    public void SetczasPracyBezNadmuchu(){
        czasPracyBezNadmuchu = getCzasPracyAktywnej()-getCzasPracyZNadmuchem();
    }
    public void setOgien(Boolean ogien) {
        this.ogien = ogien;
    }
    public void SetTemperaturaPoPompowaniu(){
        setTemperatura(getTemperatura() - 10);
    } // Ustawia temperature na pokojowa
    /*
    private void SetWspolczynnikPracyI(){
        spalanie = WSPOLCZYNNIK_PRACY_I;
        grzanie = WSPOLCZYNNIK_PRACY_I;
    } // Ustaw spalanie i grzanie na 0
    private void SetWspolczynnikPracyII(){
        spalanie = WSPOLCZYNNIK_PRACY_II;
        grzanie = WSPOLCZYNNIK_PRACY_II;
    } // Ustaw spalanie i grzanie na 1
    private void SetWspolczynnikPracyIII(){
        spalanie = WSPOLCZYNNIK_PRACY_III;
        grzanie = WSPOLCZYNNIK_PRACY_III;
    } // Ustaw spalanie i grzanie na 2
    */

    //funkcje pieca

    public void RozpalPiecCO() {

        if (getPoziomPaliwaWPiecu() > 0) {
            setOgien(true);
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

    public void TransferPaliwaZasobnikPalenisko() {
        ZabierzPaliwoZZasobnika();
        UzpelnijPaliwoWPiecu();
    } // Symulacja pracy slimaka

    private void ZabierzPaliwoZZasobnika() {
        if (getZapasPaliwaWZasobniku() <= DAWKA_PALIWA) {
            UzpelnijZasobnikPaliwa();
        }
        setZapasPaliwaWZasobniku(getZapasPaliwaWZasobniku() - DAWKA_PALIWA);
    } // Zabiera dawke paliwa z zasobnika

    public void UzpelnijPaliwoWPiecu() {
        setPoziomPaliwaWPiecu(getPoziomPaliwaWPiecu() + DAWKA_PALIWA);
    } // Uzupelnia palenisko o dawke paliwa

    private void UzpelnijZasobnikPaliwa() {
        setZapasPaliwaWZasobniku(POJEMNOSC_ZASOBNIKA);
    }  // Wypelnia zasobnik paliwa

    public PiecCO(){
        czyMaSiePalic = true;
        this.ogien = false;
        dmuchawa = new Dmuchawa();
        pompa = new OEC();
        // podajnikPracuje = false;
        // nadmuchPracuje = false;
        // pompaPracuje = false;
        zapasPaliwaWZasobniku = UZUPELNIENIE_ZASOBNIKA*2 ;
        poziomPaliwaWPiecu = POJEMNOSC_PALENISKA/2;
        spalanie = 0;
        temperatura = TEMPERATURA_POKOJOWA;
        SetCzasPracyAktywnej(CZAS_MAKSYMALNY_CALKOWITY,CZAS_MAKSYMALNY_W_NADMUCHU);
    }

    public void PodgrzejWode(){
        setTemperatura(getTemperatura() + getGrzanie());
    }
    public void SpalPaliwo(){
        setPoziomPaliwaWPiecu(getPoziomPaliwaWPiecu() - getSpalanie());
    }
}
