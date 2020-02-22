package com.example.makeitrain;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

//    private Button showMoney;
//    private Button showTag;

    private TextView moneyText;

    private int moneyCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moneyText = findViewById(R.id.money_text);
//        showMoney = findViewById(R.id.button_make_it_rain);
//        showTag = findViewById((R.id.show_tag));

//        showMoney.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("MYTAG", "onClick: SHOW MONEY");
//            }
//        });

    }
    public void showTag(View v) {
        Toast.makeText(getApplicationContext(), R.string.app_name, Toast.LENGTH_SHORT)
                .show();
    }
    public void makeItRain(View v) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        moneyCounter += 1000;
        if (moneyCounter == 10000) {
            moneyText.setTextColor(Color.parseColor("#00ff00"));
        }
        moneyText.setText(String.valueOf(numberFormat.format(moneyCounter)));
        Log.d("MIR", "onClick: Make it rain " + moneyCounter);
    }
}
