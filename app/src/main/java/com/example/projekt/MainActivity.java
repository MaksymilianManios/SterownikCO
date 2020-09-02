package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public PiecCO piec;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.piec_layout);
        piec = new PiecCO();
        piec.thread.start();

        textView = findViewById(R.id.temperaturaTextView);
        textView.setText(Integer.toString(piec.getTemperatura()));
        textView = findViewById(R.id.nadmuchTextView);
        textView.setText(piec.getNadmychDmucha());
        textView = findViewById(R.id.spalanieTextView);
        textView.setText(Integer.toString(piec.getSpalanie()));
        textView = findViewById(R.id.zapasTextView);
        textView.setText(Integer.toString(piec.getPoziomPaliwaWPiecu()));
    }

    public void onClick(View view){
        textView = findViewById(R.id.temperaturaTextView);
        textView.setText(Integer.toString(piec.getTemperatura()));
        textView = findViewById(R.id.nadmuchTextView);
        textView.setText(piec.getNadmychDmucha());
        textView = findViewById(R.id.spalanieTextView);
        textView.setText(Integer.toString(piec.getSpalanie()));
        textView = findViewById(R.id.zapasTextView);
        textView.setText(Integer.toString(piec.getPoziomPaliwaWPiecu()));
    }
    public Thread watek_zliczajacy = new Thread(new Runnable() {
        public void run() {
            do{
                textView = findViewById(R.id.temperaturaTextView);
                textView.setText(Integer.toString(piec.getTemperatura()));
                textView = findViewById(R.id.nadmuchTextView);
                textView.setText(piec.getNadmychDmucha());
                textView = findViewById(R.id.spalanieTextView);
                textView.setText(Integer.toString(piec.getSpalanie()));
                textView = findViewById(R.id.zapasTextView);
                textView.setText(Integer.toString(piec.getPoziomPaliwaWPiecu()));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while(true);
        }
    });

}
