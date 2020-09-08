package com.example.projekt;

public class Dmuchawa {
    private Boolean pracaNadmuchu;
    private Integer predkoscNadmuchu;

    public Dmuchawa()
    {
        this.predkoscNadmuchu = 0;
        this.pracaNadmuchu = true;
    }

    public Boolean getPracaNadmuchu() {
        return pracaNadmuchu;
    }

    public Integer getPredkoscNadmuchu() {
        return predkoscNadmuchu;
    }

    public void setPracaNadmuchu(Boolean pracaNadmuchu) {
        this.pracaNadmuchu = pracaNadmuchu;
    }

    public void setPredkoscNadmuchu(Integer predkoscNadmuchu) {
        this.predkoscNadmuchu = predkoscNadmuchu;
    }
}
