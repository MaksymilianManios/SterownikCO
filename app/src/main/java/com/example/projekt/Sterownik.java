package com.example.projekt;

public class Sterownik extends PiecCO{
    //public Piec piec;
    private Boolean pracaPodajnika;
    private Boolean pracaNadmuchu;
    private Boolean pracaPompy;
    private Boolean ogien;
    private Integer tempZadana;
    private Integer histereza;
    private Integer czasPracyPodajnika;
    private Integer czasPrzerwyPodajnika;
    private Integer temperaturaZalaczeniaPompy;
    private Integer czasPrzerwyPodtrzymania;
    private PiecCO piecCO;

    public Sterownik(){

        this.pracaNadmuchu = false;
        this.pracaPodajnika = false;
        this.pracaPompy = false;
        this.tempZadana=60;
        this.czasPracyPodajnika = 20;
        this.czasPrzerwyPodajnika = 40;
        this.temperaturaZalaczeniaPompy = 35;
        this.czasPrzerwyPodtrzymania = 5;
        this.histereza = 5;
        this.ogien = false;
    }

    public Boolean getOgien() {
        return ogien;
    }

    public void setOgien(Boolean ogien) {
        this.ogien = ogien;
    }

    public Integer getTemperaturaZalaczeniaPompy() {
        return temperaturaZalaczeniaPompy;
    }

    public void setTemperaturaZalaczeniaPompy(Integer temperaturaZalaczeniaPompy) {
        this.temperaturaZalaczeniaPompy = temperaturaZalaczeniaPompy;
    }

    public Integer getCzasPrzerwyPodtrzymania() {
        return czasPrzerwyPodtrzymania;
    }

    public void setCzasPrzerwyPodtrzymania(Integer czasPrzerwyPodtrzymania) {
        this.czasPrzerwyPodtrzymania = czasPrzerwyPodtrzymania;
    }

    public Boolean getPracaPodajnika() {
        return pracaPodajnika;
    }

    public void setPracaPodajnika(Boolean pracaPodajnika) {
        this.pracaPodajnika = pracaPodajnika;
    }

    public Boolean getPracaNadmuchu() {
        return pracaNadmuchu;
    }

    public void setPracaNadmuchu(Boolean pracaNadmuchu) {
        this.pracaNadmuchu = pracaNadmuchu;
    }

    public Boolean getPracaPompy() {
        return pracaPompy;
    }

    public void setPracaPompy(Boolean pracaPompy) {
        this.pracaPompy = pracaPompy;
    }

    public Integer getTempZadana() {
        return tempZadana;
    }

    public void setTempZadana(Integer tempZadana) {
        this.tempZadana = tempZadana;
    }

    public Integer getCzasPracyPodajnika() {
        return czasPracyPodajnika;
    }

    public void setCzasPracyPodajnika(Integer czasPracyPodajnika) {
        this.czasPracyPodajnika = czasPracyPodajnika;
    }

    public Integer getCzasPrzerwyPodajnika() {
        return czasPrzerwyPodajnika;
    }

    public void setCzasPrzerwyPodajnika(Integer czasPrzerwyPodajnika) {
        this.czasPrzerwyPodajnika = czasPrzerwyPodajnika;
    }


    public void TransferPaliwaZasobnikPalenisko(){
        ZabierzPaliwoZZasobnika();
        UzpelnijPaliwoWPiecu();
    } // Symulacja pracy slimaka

    private void ZabierzPaliwoZZasobnika(){
        if(getZapasPaliwaWZasobniku() <= DAWKA_PALIWA){
            UzpelnijZasobnikPaliwa();
        }
        setZapasPaliwaWZasobniku(getZapasPaliwaWZasobniku() - DAWKA_PALIWA);
    } // Zabiera dawke paliwa z zasobnika

    private void UzpelnijPaliwoWPiecu(){
        setPoziomPaliwaWPiecu(getPoziomPaliwaWPiecu() + DAWKA_PALIWA);
    } // Uzupelnia palenisko o dawke paliwa

    private void UzpelnijZasobnikPaliwa(){
        setZapasPaliwaWZasobniku(POJEMNOSC_ZASOBNIKA);
    }  // Wypelnia zasobnik paliwa

    public boolean RozpalPiecCO(){
        if(getPoziomPaliwaWPiecu() <= 0){
            return false;
        }
        setPiecSiePali(true);
        setOgien(true);
        return getPiecSiePali();
    }

    public void ZamienPaliwoNaCieplo(){
        SpalPaliwo();
        PodgrzejWode();
    }

    public void szybkoscNagrzewania(){
        if(getOgien()){
            if(getPracaNadmuchu()){
                setGrzanie(2);
                setSpalanie(20);
            }else{
                setGrzanie(1);;
                setSpalanie(10);
            }
        }else {
            setGrzanie(0);
            setSpalanie(0);
        }
        if(getPracaPompy())
        {
            setGrzanie(getGrzanie() - 2);
        }
    }

    public Thread thread = new Thread(new Runnable() {
        public void run() {
            do {

                if (getOgien()) {

                    if(getTemperatura()>=getTempZadana()){
                        setPracaNadmuchu(false);
                        setPracaPompy(true);
                        /*
                        try {
                            ZamienPaliwoNaCieplo();
                            Thread.sleep(1000);
                            ZamienPaliwoNaCieplo();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                         */
                    }else{
                        if(!(getTemperatura() > (getTempZadana() - histereza))) {
                            setPracaNadmuchu(true);
                            setPracaPompy(false);
                        }
                    }

                    if(getPoziomPaliwaWPiecu()<= 10){
                        setPracaPodajnika(true);
                        UzpelnijPaliwoWPiecu();
                        /*
                            try {
                                szybkoscNagrzewania();
                                ZamienPaliwoNaCieplo();
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        */



                    }else{
                        setPracaPodajnika(false);
                    }

                    try {
                        szybkoscNagrzewania();
                        ZamienPaliwoNaCieplo();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //setPracaNadmuchu(true);
                    /*
                    for(int licznikPomocniczy = 0;licznikPomocniczy<czasPracyZNadmuchem  && getTemperatura() <= getTempZadana();licznikPomocniczy++){
                        try {
                            szybkoscNagrzewania();
                            ZamienPaliwoNaCieplo();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    setPracaNadmuchu(false);
                    for(int licznikPomocniczy = 0; licznikPomocniczy<czasPracyBezNadmuchu && getTemperatura() <= TEMPERATURA_DOCELOWA;licznikPomocniczy++){
                        try {
                            szybkoscNagrzewania();
                            ZamienPaliwoNaCieplo();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                     */
                } else {
                    //setPracaPodajnika(true);
                    RozpalPiecCO();

                    //setPracaPodajnika(false);
                }


            }while(true);
        }
    });

}
