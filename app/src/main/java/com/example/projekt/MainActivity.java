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
    private Integer[] liczba_w_ustawieniach;
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
        sterownik = new Sterownik();
        this.thread.start();
        sterownik.piec.start();
        menuWlaczone = false;
        ustawienia_wlaczone = false;
        menu = new Menu(context);
        liczba_w_ustawieniach = new Integer[]{1, 0, 0};
        wyjscie_z_ustawien();


    }

    public void wejscie_do_ustawien() {
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
        textView.setText("A" + liczba_w_ustawieniach[0].toString());
    }

    public void zwieksz(View view) {
        if (menuWlaczone) {
            liczba_w_ustawieniach[0]++;
            if (liczba_w_ustawieniach[0] == 7) {
                liczba_w_ustawieniach[0] = 1;
            }
            textView = findViewById(R.id.ekran);
            textView.setText("A" + liczba_w_ustawieniach[0].toString());
        } else if (sterownik.getTrybManualny()) {
            if (sterownik.getPracaPodajnika()) {
                sterownik.setPracaPodajnika(false);
            } else sterownik.setPracaPodajnika(true);
        }
    }

    public void zmniejsz(View view) {
        if (menuWlaczone) {
            liczba_w_ustawieniach[0]--;
            if (liczba_w_ustawieniach[0] == 0) {
                liczba_w_ustawieniach[0] = 6;
            }
            textView = findViewById(R.id.ekran);
            textView.setText("A" + liczba_w_ustawieniach[0].toString());
        } else if (sterownik.getTrybManualny()) {
            if (sterownik.dmuchawa.getPracaNadmuchu()) {
                sterownik.dmuchawa.setPracaNadmuchu(false);
            } else sterownik.dmuchawa.setPracaNadmuchu(true);
        }

    }

    public void otworzMenu(View view) {
        if (!menuWlaczone) {
            if (!sterownik.getTrybManualny()) {
                menuWlaczone = true;
                textView = findViewById(R.id.ekran);
                textView.setText("A" + liczba_w_ustawieniach[0].toString());
                // wejscie_do_ustawien();
                btn = (Button) findViewById(R.id.opcje);
                btn.setVisibility(view.INVISIBLE);
                btn.setEnabled(false);
                btn = (Button) findViewById(R.id.ustaw);
                btn.setVisibility(view.VISIBLE);
                btn.setEnabled(true);

            } else {
                if (sterownik.pompa.getPracaPompy()) {
                    sterownik.pompa.setPracaPompy(false);
                } else sterownik.pompa.setPracaPompy(true);
            }
        } else {
            menu.zatwierdz(sterownik, liczba_w_ustawieniach);
        }

    }

    public void ustawienia(View view) {
        ustawienia_wlaczone = true;
        wejscie_do_ustawien();
        liczba_w_ustawieniach = menu.zakresUstawienia(liczba_w_ustawieniach, sterownik);
    }

    public void zatwierdz(View view) {
        sterownik = menu.zatwierdz(sterownik, liczba_w_ustawieniach);
    }

    public void zwiekszLiczbe(View view) {
        liczba_w_ustawieniach[2]++;
        if (liczba_w_ustawieniach[2] == liczba_w_ustawieniach[1] + 1) {
            liczba_w_ustawieniach[2] = 0;
        }
    }

    public void zmniejszLiczbe(View view) {
        liczba_w_ustawieniach[2]--;
        if (liczba_w_ustawieniach[2] == -1) {
            liczba_w_ustawieniach[2] = liczba_w_ustawieniach[1];
        }
    }

    public void wyjscie(View view) {
        if (!sterownik.getTrybManualny()) {
            menuWlaczone = false;
            liczba_w_ustawieniach[0] = 1;
            wyjscie_z_ustawien();
            ustawienia_wlaczone = false;

        } else {
            if (menuWlaczone) {
                menuWlaczone = false;
                liczba_w_ustawieniach[0] = 1;
                wyjscie_z_ustawien();
                ustawienia_wlaczone = false;
            } else sterownik.setTrybManualny();
        }

    }




    Thread thread = new Thread() {

        @Override
        public void run() {
            try {
                while (!thread.isInterrupted()) {
                    Thread.sleep(500);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!menuWlaczone) {
                                switcha = (Switch) findViewById(R.id.kontrolkaNadmuchu);
                                if (sterownik.dmuchawa.getPracaNadmuchu()) {
                                    switcha.setChecked(true);
                                } else switcha.setChecked(false);

                                switcha = (Switch) findViewById(R.id.kontrolkaPodajnika);
                                if (sterownik.getPracaPodajnika()) {
                                    switcha.setChecked(true);
                                } else switcha.setChecked(false);

                                switcha = (Switch) findViewById(R.id.kontrolkaPompy);
                                if (sterownik.pompa.getPracaPompy()) {
                                    switcha.setChecked(true);
                                } else switcha.setChecked(false);

                                switcha = (Switch) findViewById(R.id.kontrolkaOgnia);
                                if (sterownik.getOgien()) {
                                    switcha.setChecked(true);
                                } else switcha.setChecked(false);

                                switcha = (Switch) findViewById(R.id.kontrolkaPracyRecznej);
                                if (sterownik.getTrybManualny()) {
                                    switcha.setChecked(true);
                                } else switcha.setChecked(false);

                                textView = findViewById(R.id.ekran);
                                textView.setText(Integer.toString(sterownik.getTemperatura()));
                            }
                            if (ustawienia_wlaczone) {
                                textView = findViewById(R.id.ekran);
                                textView.setText(liczba_w_ustawieniach[2].toString());
                            }
                        }
                    });
                }
            } catch (InterruptedException e) {
            }
        }
    };
}




