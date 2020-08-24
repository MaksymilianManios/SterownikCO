package com.example.projekt;

public final class Piec {// extends Thread {

    //###################################################################################
    private Integer paliwo; //ile paliwa znajduje się w piecu
    private Integer nadmuch; //moc nadmuchu
    private Integer pompa; //moc działania pompy
    private Integer temperatura; //wypadkowa paliwa, nadmuchu i pompy

    public Integer getPaliwo() {
        return paliwo;
    }

    public void setPaliwo(Integer paliwo) {
        this.paliwo = paliwo;
    }

    public Integer getNadmuch() {
        return nadmuch;
    }

    public void setNadmuch(Integer nadmuch) {
        this.nadmuch = nadmuch;
    }

    public Integer getPompa() {
        return pompa;
    }

    public void setPompa(Integer pompa) {
        this.pompa = pompa;
    }

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }
    //####################################################################################

    // PRZYDALO BY SIE POLACZYC OBIEKTY KLAS NIZEJ WYMIENIONYCH W PIEC I ZROBIC JEGO SYMULACJE.
    //TZ. NAPISAC METODY POZWALAJACE NA JEGO AUTONOMICZNA PRACE A NASTEPNIE ZAMKNIECIE TAKIEJ PETLI W METODZIE
    // WYWOLYWANEJ NA KONIEC KONSTRUKTORA. PRZECIAZONA METODA RUN()

    // KOCIOL / PALENISKO                        - SPALA PALIWO ODDAJE TEMPERATURE
    // ODPROWADZENIE ENERGI CIEPLNEJ             - PRZECHWYTUJE ENERGIE CIEPLNA I JA WYPROWADZA Z OBIEGU
    // PODAJNIK PALIWA                           - DOSTARCZA PALIWA DO OBIEGU DYSPONUJE ZASOBAMI
    // DOPROWADZENIE TLENU / DMUCHAWA            - UWYDAJNIA REAKCJE SPALANIA




}
