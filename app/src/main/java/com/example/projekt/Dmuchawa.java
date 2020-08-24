package com.example.projekt;

public class Dmuchawa {
    private boolean czyWlaczona;
    public void ZalaczDmuchawe(){
        czyWlaczona = true;
    }
    public void OdlaczDmuchawe(){
        czyWlaczona = false;
    }
    public boolean GetStanDmuchawy(){
        return czyWlaczona;
    }
}
