package com.example.projekt;

public class Sterownik extends PiecCO {
    //public Piec piec;

    private Integer tempZadana;
    private Boolean trybManualny;
    private Integer histereza;



    private Integer temperaturaZalaczeniaPompy;
    private Integer czasPrzerwyPodtrzymania;
    private PiecCO piecCO;

    public Sterownik() {


        this.tempZadana = 60;
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

    public Integer getTempZadana() {
        return tempZadana;
    }

    public Integer getHistereza() {
        return histereza;
    }

    public Boolean getPracaPodajnika(){
        return paleni.podajnik.getPracaPodajnika();
    }

    public Boolean getTrybManualny() { return trybManualny;}

    public Integer GetPoziomPaliwa() {return paleni.getPoziomPaliwaWPalenisku();}



    //Settery:

    public void setTemperaturaZalaczeniaPompy(Integer temperaturaZalaczeniaPompy) {
        this.temperaturaZalaczeniaPompy = temperaturaZalaczeniaPompy;
    }

    public void setTempZadana(Integer tempZadana) {
        this.tempZadana = tempZadana;
    }

    public void setHistereza(Integer histereza) {
        this.histereza = histereza;
    }

    public void setTrybManualny() {
        if(trybManualny){
            trybManualny = false;
        }else trybManualny = true;

    }

    public void  setPracaPodajnika(Boolean praca){
        paleni.podajnik.setPracaPodajnika(praca);
    }

    public void UzpelnijPaliwoWPiecu() {
        paleni.uzupelnijPaliwo();
    } // Uzupelnia palenisko o dawke paliwa


    public Thread piec = new Thread() {

        @Override
        public void run() {
            do {

                if (getOgien()) {
                    if(getTemperatura() > 90)
                    {
                        zgasPiec();
                        setTrybManualny();
                    }
                    if (!getTrybManualny()) {

                        if (getTemperatura() >= getTempZadana()) {
                            dmuchawa.setPracaNadmuchu(false);

                        } else {
                            if (!(getTemperatura() > (getTempZadana() - getHistereza()))) {
                                dmuchawa.setPracaNadmuchu(true);
                            }
                        }
                        if (getTemperatura() >= getTemperaturaZalaczeniaPompy()){
                            pompa.setPracaPompy(true);
                        }else{
                            pompa.setPracaPompy(false);
                        }
                        if (GetPoziomPaliwa() <= 10) {
                            setPracaPodajnika(true);
                            UzpelnijPaliwoWPiecu();

                        } else {
                            setPracaPodajnika(false);
                        }

                        try {
                            szybkoscNagrzewania();
                            ZamienPaliwoNaCieplo();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }else
                    {
                        try {
                            szybkoscNagrzewania();
                            ZamienPaliwoNaCieplo();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    RozpalPiecCO();
                }
            }while(true);
        }
    };
}

