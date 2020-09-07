package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import  android.widget.Switch;
import  android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Boolean menuWlaczone;
    private Boolean ustawienia_wlaczone;
    private Integer licznik = 1;
    private Integer liczba_w_ustawieniach = 0;
    public TextView textView;
    public Switch switcha;
    public Sterownik sterownik;
    public Menu menu;
    private Button btn;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        //setContentView(R.layout.piec_layout);
        sterownik = new Sterownik();
        //sterownik.thread.start();
        this.thread.start();
        this.piec.start();
        menuWlaczone = false;
        ustawienia_wlaczone =false;
        menu = new Menu(context);
        wyjscie_z_ustawien();

    }

    public void wejscie_do_ustawien()
    {
        btn = (Button) findViewById(R.id.plus_w_menu);
        btn.setEnabled(true);
        btn.setVisibility(view.VISIBLE);
        btn = (Button) findViewById(R.id.plus);
        btn.setVisibility(view.INVISIBLE);
        btn.setEnabled(false);
        btn = (Button) findViewById(R.id.opcje);
        btn.setVisibility(view.INVISIBLE);
        btn.setEnabled(false);
        btn = (Button) findViewById(R.id.zatwierdzanie);
        btn.setVisibility(view.VISIBLE);
        btn.setEnabled(true);
        btn = (Button) findViewById(R.id.ustaw);
        btn.setVisibility(view.GONE);
        btn.setEnabled(false);
        btn = (Button) findViewById(R.id.minus_w_menu);
        btn.setVisibility(view.VISIBLE);
        btn.setEnabled(true);

    }
    public void wyjscie_z_ustawien() {
        btn = (Button) findViewById(R.id.plus_w_menu);
        btn.setEnabled(false);
        btn.setVisibility(view.GONE);
        btn = (Button) findViewById(R.id.plus);
        btn.setVisibility(view.VISIBLE);
        btn.setEnabled(true);
        btn = (Button) findViewById(R.id.opcje);
        btn.setVisibility(view.VISIBLE);
        btn.setEnabled(true);
        btn = (Button) findViewById(R.id.zatwierdzanie);
        btn.setVisibility(view.GONE);
        btn.setEnabled(false);
        btn = (Button) findViewById(R.id.minus_w_menu);
        btn.setVisibility(view.GONE);
        btn.setEnabled(false);
        btn = (Button) findViewById(R.id.ustaw);
        btn.setVisibility(view.GONE);
        btn.setEnabled(false);
        textView = findViewById(R.id.ekran);
        textView.setText("A" + licznik.toString());
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
               // wejscie_do_ustawien();
                btn = (Button) findViewById(R.id.opcje);
                btn.setVisibility(view.INVISIBLE);
                btn.setEnabled(false);
                btn = (Button) findViewById(R.id.ustaw);
                btn.setVisibility(view.VISIBLE);
                btn.setEnabled(true);

            } else {
                if (sterownik.getPracaPompy()) {
                    sterownik.setPracaPompy(false);
                } else sterownik.setPracaPompy(true);
            }
        }else
        {
            menu.zatwierdz(licznik, sterownik, licznik);
        }

    }

    public void ustawienia(View view){
        ustawienia_wlaczone = true;
        wejscie_do_ustawien();
        menu.setTrybManualny();

    }

    public void zatwierdz(View view){
        menu.zatwierdz(licznik, sterownik, liczba_w_ustawieniach);
    }

    public void zwiekszLiczbe(View view){
        liczba_w_ustawieniach++;
    }

    public void zmniejszLiczbe(View view){
        liczba_w_ustawieniach--;
    }

    public void wyjscie(View view) {
        if(!menu.getTrybManualny()) {
            menuWlaczone = false;
            licznik = 1;
            //wyjscie_z_menu();
            wyjscie_z_ustawien();
            ustawienia_wlaczone = false;
            liczba_w_ustawieniach = 0;
        }else {
            if(menuWlaczone)
            {
                menuWlaczone = false;
                licznik = 1;
                //wyjscie_z_menu();
                wyjscie_z_ustawien();
                ustawienia_wlaczone = false;
                liczba_w_ustawieniach = 0;
            }else menu.setTrybManualny();
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
                            if(ustawienia_wlaczone){
                                textView = findViewById(R.id.ekran);
                                textView.setText(liczba_w_ustawieniach.toString());
                            }
                        }
                    });
                }
            } catch (InterruptedException e) {
            }
        }
    };


    public Thread piec = new Thread() {

        @Override
        public void run() {
            do {

                if (sterownik.getOgien()) {
                    if (!menu.getTrybManualny()) {

                        if (sterownik.getTemperatura() >= sterownik.getTempZadana()) {
                            sterownik.setPracaNadmuchu(false);
                            sterownik.setPracaPompy(true);
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
                            if (!(sterownik.getTemperatura() > (sterownik.getTempZadana() - sterownik.getHistereza()))) {
                                sterownik.setPracaNadmuchu(true);
                                sterownik.setPracaPompy(false);
                            }
                        }

                        if (sterownik.getPoziomPaliwaWPiecu() <= 10) {
                            sterownik.setPracaPodajnika(true);
                            sterownik.UzpelnijPaliwoWPiecu();
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
                            sterownik.setPracaPodajnika(false);
                        }

                        try {
                            sterownik.szybkoscNagrzewania();
                            sterownik.ZamienPaliwoNaCieplo();
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
                            sterownik.szybkoscNagrzewania();
                            sterownik.ZamienPaliwoNaCieplo();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                        //setPracaPodajnika(true);
                     sterownik.RozpalPiecCO();

                        //setPracaPodajnika(false);
                }


            }while(true);
        }
    };

}




