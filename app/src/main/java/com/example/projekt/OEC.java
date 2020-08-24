package com.example.projekt;

public class OEC { // Odprowadzenie Energi Cieplnej

    public static final Double TEMPERATURA_MAKSYMALNA = 65.0;
    public static final Double WSPOLCZYNNIK_OCHLODZENIA = 0.05;
    public static final Double WSPOLCZYNNIK_GRZANIA_I = 2.0;  //DLA WYLACZONEJ DMUCHAWY
    public static final Double WSPOLCZYNNIK_GRZANIA_II = 5.0; //DLA WLACZONEJ DMUCHAWY

    private int pojemnoscUkladu;
    private int pojemnoscBufora;
    private Double temperaturaUkladu;
    private Double temperaturaBufora;
    private Double wspolczynnikGrzania;

    public OEC(int pojemnoscBufora,int pojemnoscUkladu,double temperatura){
        this.pojemnoscBufora = pojemnoscBufora;
        this.pojemnoscUkladu = pojemnoscUkladu;
        temperaturaUkladu = temperaturaBufora = temperatura;
        wspolczynnikGrzania = 5.0;
    }

    public void Papuj(){
        double temperatura = (pojemnoscUkladu*temperaturaUkladu + pojemnoscBufora*temperaturaBufora) / (pojemnoscBufora+pojemnoscUkladu);
        temperaturaBufora = temperaturaUkladu = temperatura;
    }

    public Double GetTemperaturaUkladu(){
        return temperaturaUkladu;
    }

    public Double GetTemperaturaBufora(){
        return temperaturaBufora;
    }
}
