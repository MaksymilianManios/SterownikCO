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
    // POMPA
    public static final int TEMPERATURA_DOCELOWA = 60;
    public static final int TEMPERATURA_POKOJOWA = 20;
    public static final int AMPLITUDA_TEMPERATUR = TEMPERATURA_DOCELOWA - TEMPERATURA_POKOJOWA;
    // CZAS PRACY AKTYWNEJ
    public static final int CZAS_MAKSYMALNY_CALKOWITY = 40;
    public static final int CZAS_MAKSYMALNY_W_NADMUCHU = CZAS_MAKSYMALNY_CALKOWITY/WSPOLCZYNNIK_PRACY_III/2;
    // zmienne kontrolne :
    private Boolean piecSiePali;     // <-true jesli sie pali
    private Boolean podajnikPracuje; // <-true jesli pracuje
    private Boolean nadmuchPracuje;  // <-true jesli pracuje
    private Boolean pompaPracuje;    // <-true jesli pracuje

    public Boolean czyMaSiePalic; // decyduje czy funkcja run ma przerwac petle
    private Integer czasPracyZNadmuchem = 10;
    private Integer czasPracyBezNadmuchu = 20;
    private Integer czasPracyAktywnej = 30;
    private Integer poziomPaliwaWPiecu;
    private Integer zapasPaliwaWZasobniku;
    private Integer spalanie;
    private Integer grzanie;
    private Integer temperatura;


    public PiecCO(){
        czyMaSiePalic = true;
        piecSiePali = false;
        podajnikPracuje = false;
        nadmuchPracuje = false;
        pompaPracuje = false;
        zapasPaliwaWZasobniku = UZUPELNIENIE_ZASOBNIKA*2 ;
        poziomPaliwaWPiecu = POJEMNOSC_PALENISKA/2;
        spalanie = 0;
        temperatura = TEMPERATURA_POKOJOWA;
        SetCzasPracyAktywnej(CZAS_MAKSYMALNY_CALKOWITY,CZAS_MAKSYMALNY_W_NADMUCHU);
    }
    private void ZamienPaliwoNaCieplo(){
        UstalWspolczynnikPracy();
        SpalPaliwo();
        PodgrzejWode();
    }
    private void PodgrzejWode(){
        temperatura += grzanie;
    }
    private void SpalPaliwo(){
        poziomPaliwaWPiecu -= spalanie;
    }
    public void PompujWodeStop(){
        SetTemperaturaPoPompowaniu();
        OFF_PompaPracuje();
    }
    public void PompujWodeStart(){
        ON_PompaPracuje();
    } // Opakowana funkja zapalajaca kontrolke pompy
    public void UstalWspolczynnikPracy(){
        if(piecSiePali){
            if(nadmuchPracuje){
                SetWspolczynnikPracyIII();
            }else{
                SetWspolczynnikPracyII();
            }
        }else {
            SetWspolczynnikPracyI();
        }
    } // Ustala Wspolczynnik Pracy na podstawie stanu zmiennych kontrolnych
    private void TransferPaliwaZasobnikPalenisko(){
        ZabierzPaliwoZZasobnika();
        UzpelnijPaliwoWPiecu();
    } // Symulacja pracy slimaka
    private void ZabierzPaliwoZZasobnika(){
        if(getZapasPaliwaWZasobniku() <= DAWKA_PALIWA){
            UzpelnijZasobnikPaliwa();
        }
        zapasPaliwaWZasobniku -= DAWKA_PALIWA;
    } // Zabiera dawke paliwa z zasobnika
    private void UzpelnijPaliwoWPiecu(){
        poziomPaliwaWPiecu += DAWKA_PALIWA;
    } // Uzupelnia palenisko o dawke paliwa
    private void UzpelnijZasobnikPaliwa(){
        zapasPaliwaWZasobniku = POJEMNOSC_ZASOBNIKA;
    }  // Wypelnia zasobnik paliwa
    public boolean RozpalPiecCO(){
        if(PaleniskoPuste()){
            return false;
        }
        ON_PiecSiePali();
        return piecSiePali;
    } // Rozpal piec, zwraca rezultat operacji
    private boolean PaleniskoPuste(){
        return poziomPaliwaWPiecu <= 0;
    } // Czy w piecu jest paliwo? zwraca wynik operacji

    public void OFF_PiecSiePali(){piecSiePali = false;}
    private void ON_PiecSiePali(){piecSiePali = true;}
    private void OFF_PodajnikPracuje(){podajnikPracuje = false;}
    private void ON_PodajnikPracuje(){podajnikPracuje = true;}
    private void OFF_NadmuchPracuje(){nadmuchPracuje = false;}
    private void ON_NadmuchPracuje(){nadmuchPracuje = true;}
    private void OFF_PompaPracuje(){pompaPracuje = false;}
    private void ON_PompaPracuje(){pompaPracuje = true;}

    // Gettery :
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
    public Integer getCzasPracyZNadmuchem(){
        return czasPracyZNadmuchem;
    }
    public Integer getCzasPracyBezNadmuchu(){
        return czasPracyBezNadmuchu;
    }
    public Integer getCzasPracyAktywnej(){
        return czasPracyAktywnej;
    }
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
    private void SetczasPracyZNadmuchem(int czas){
        czasPracyZNadmuchem = czas;
    }
    private void SetczasPracyBezNadmuchu(){
        czasPracyBezNadmuchu = getCzasPracyAktywnej()-getCzasPracyZNadmuchem();
    }
    private void SetTemperaturaPoPompowaniu(){
        temperatura = TEMPERATURA_POKOJOWA;
    } // Ustawia temperature na pokojowa
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

    public Thread thread = new Thread(new Runnable() {
        public void run() {
            do {
                if (RozpalPiecCO()) {

                    if(getTemperatura()>=TEMPERATURA_DOCELOWA){
                        OFF_NadmuchPracuje();
                        PompujWodeStart();
                        try {
                            ZamienPaliwoNaCieplo();
                            Thread.sleep(1000);
                            ZamienPaliwoNaCieplo();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        PompujWodeStop();
                    }
                    if(getPoziomPaliwaWPiecu()<=AMPLITUDA_TEMPERATUR/WSPOLCZYNNIK_PRACY_III){
                        OFF_NadmuchPracuje();
                        ON_PodajnikPracuje();
                        do{
                            TransferPaliwaZasobnikPalenisko();
                            try {
                                ZamienPaliwoNaCieplo();
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } while(getPoziomPaliwaWPiecu()<POJEMNOSC_PALENISKA);
                        OFF_PodajnikPracuje();
                    }
                    ON_NadmuchPracuje();
                    for(int licznikPomocniczy = 0;licznikPomocniczy<czasPracyZNadmuchem  && temperatura <= TEMPERATURA_DOCELOWA;licznikPomocniczy++){
                        try {
                            ZamienPaliwoNaCieplo();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    OFF_NadmuchPracuje();
                    for(int licznikPomocniczy = 0; licznikPomocniczy<czasPracyBezNadmuchu && temperatura <= TEMPERATURA_DOCELOWA;licznikPomocniczy++){
                        try {
                            ZamienPaliwoNaCieplo();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                } else {
                    ON_PodajnikPracuje();
                    for (int licznikPomocniczy = 0; licznikPomocniczy < POJEMNOSC_PALENISKA / DAWKA_PALIWA; licznikPomocniczy++) {
                        TransferPaliwaZasobnikPalenisko();
                        try {
                            ZamienPaliwoNaCieplo();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    OFF_PodajnikPracuje();
                }
            }while(czyMaSiePalic);

        }
    });
}
