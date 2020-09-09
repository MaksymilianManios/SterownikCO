package com.example.projekt;

public class Sterownik extends PiecCO {
    //public Piec piec;

    private Integer tempZadana;
    private Boolean trybManualny;
    private Integer histereza;

    /*
    private Integer czasPracyPodajnika;
    private Integer czasPrzerwyPodajnika;
     */

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
        paleni.setPoziomPaliwaWPalenisku( paleni.getPoziomPaliwaWPalenisku() + paleni.podajnik.PodajPaliwo());
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
                            //sterownik.setPracaPompy(true);
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
                        } else {
                            if (!(getTemperatura() > (getTempZadana() - getHistereza()))) {
                                dmuchawa.setPracaNadmuchu(true);
                                //sterownik.setPracaPompy(false);
                            }
                        }



                        /*
                        if (sterownik.getTemperatura() <= (sterownik.getTempZadana()) || sterownik.getTemperatura() < (sterownik.getTempZadana() - sterownik.getHistereza()))
                        {
                            sterownik.setPracaNadmuchu(true);
                        }else {
                            if (sterownik.getTemperatura() > (sterownik.getTempZadana() - sterownik.getHistereza())) {
                                sterownik.setPracaNadmuchu(false);
                            }
                        }

                         */
                        if (getTemperatura() >= getTemperaturaZalaczeniaPompy()){
                            pompa.setPracaPompy(true);
                        }else{
                            pompa.setPracaPompy(false);
                        }
                        if (GetPoziomPaliwa() <= 10) {
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
                    //setPracaPodajnika(true);
                    RozpalPiecCO();

                    //setPracaPodajnika(false);
                }


            }while(true);
        }
    };


}

