package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import  android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private Boolean menuWlaczone;
    private Integer licznik = 1;
    public TextView textView;
    public Switch switcha;
    public Sterownik sterownik;
    public Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.piec_layout);
        sterownik = new Sterownik();
        sterownik.thread.start();
        this.thread.start();
        menuWlaczone = false;
        menu = new Menu();

    }

    public void zwieksz(View view) {
        if(menuWlaczone) {
            licznik++;
            if(licznik == 5) { licznik = 1;}
            textView = findViewById(R.id.ekran);
            textView.setText("A" + licznik.toString());
        }else if(menu.getTrybManualny())
        {
            if(sterownik.getPracaPodajnika())
            {
                sterownik.setPracaPodajnika(false);
            }else sterownik.setPracaPodajnika(true);
        }
    }

    public void zmniejsz(View view){
        if(menuWlaczone) {
            licznik--;
            if(licznik == 0) {licznik = 4;}
            textView = findViewById(R.id.ekran);
            textView.setText("A" + licznik.toString());
        }else if(menu.getTrybManualny())
        {
            if(sterownik.getPracaNadmuchu())
            {
                sterownik.setPracaNadmuchu(false);
            }else sterownik.setPracaNadmuchu(true);
        }

    }

    public void otworzMenu(View view)
    {
        if(!menuWlaczone) {
            if (!menu.getTrybManualny()) {
                menuWlaczone = true;
                textView = findViewById(R.id.ekran);
                textView.setText("A" + licznik.toString());
            } else {
                if (sterownik.getPracaPompy()) {
                    sterownik.setPracaPompy(false);
                } else sterownik.setPracaPompy(true);
            }
        }else
        {
            menu.zatwierdz(licznik);
        }
    }

    public void wyjscie(View view) {
        if(!menu.getTrybManualny()) {
            menuWlaczone = false;
            licznik = 1;
        }else {
           // menu.setTrybManualny();
        }
    }

    /*public Thread watek_zliczajacy = new Thread(new Runnable() {
        public void run() {
            do{
                               try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }while(true);
        }
    });*/


    Thread thread = new Thread() {

        @Override
        public void run() {
            try {
                while (!thread.isInterrupted()) {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(!menuWlaczone) {
                                switcha = (Switch) findViewById(R.id.kontrolkaNadmuchu);
                                if(sterownik.getPracaNadmuchu()){
                                    switcha.setChecked(true);
                                }else switcha.setChecked(false);

                                switcha = (Switch) findViewById(R.id.kontrolkaPodajnika);
                                if(sterownik.getPracaPodajnika()){
                                    switcha.setChecked(true);
                                }else switcha.setChecked(false);

                                switcha = (Switch) findViewById(R.id.kontrolkaPompy);
                                if(sterownik.getPracaPompy()){
                                    switcha.setChecked(true);
                                }else switcha.setChecked(false);

                                switcha = (Switch) findViewById(R.id.kontrolkaOgnia);
                                if(sterownik.getOgien()){
                                    switcha.setChecked(true);
                                }else switcha.setChecked(false);

                                switcha = (Switch) findViewById(R.id.kontrolkaPracyRecznej);
                                if(menu.getTrybManualny()){
                                    switcha.setChecked(true);
                                }else switcha.setChecked(false);
                                textView = findViewById(R.id.ekran);
                                textView.setText(Integer.toString(sterownik.getTemperatura()));
                            }
                        }
                    });
                }
            } catch (InterruptedException e) {
            }
        }
    };




}
