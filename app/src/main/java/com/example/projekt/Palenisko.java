package com.example.projekt;

public class Palenisko {

    public static final int MINIMALNY_POZIOM_PALIWA = 20;
    public static final int MAKSYMALNY_POZIOM_PALIWA = 80;
    public static final int ROZRUCHOWA_PORCJA_PALIWA = MAKSYMALNY_POZIOM_PALIWA + MINIMALNY_POZIOM_PALIWA/2;

    private boolean czyPlonie;
    private Integer paliwaWPalenisku;

    public Integer GetPaliwaWPalenisku(){
        return paliwaWPalenisku;
    }
    public void AddPaliwaWPalenisku(int DawkaPaliwa){
        paliwaWPalenisku += DawkaPaliwa;
        KielichPrzepelniony();
    }
    private void KielichPrzepelniony(){
        if(paliwaWPalenisku>MAKSYMALNY_POZIOM_PALIWA){
            paliwaWPalenisku = MAKSYMALNY_POZIOM_PALIWA;
            czyPlonie = false;
        }
    }
    private void KielichPusty(){
        if(paliwaWPalenisku<MINIMALNY_POZIOM_PALIWA){
            czyPlonie = false;
        }
    }
    public void Zgas(){
        czyPlonie = false;
    }
    public void Rozpal(){
        KielichPrzepelniony();
        czyPlonie = true;
        KielichPusty();
    }
}
