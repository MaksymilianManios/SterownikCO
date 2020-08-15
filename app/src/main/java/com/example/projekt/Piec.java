package com.example.projekt;

public final class Piec {
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
    
}
