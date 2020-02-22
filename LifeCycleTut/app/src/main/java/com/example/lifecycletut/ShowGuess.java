package com.example.lifecycletut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

public class ShowGuess extends AppCompatActivity {

    private TextView showGuessText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);

        Bundle extra = getIntent().getExtras();
        showGuessText = findViewById(R.id.received_textview);

        //? What if a value pair is null
        if (extra != null) {
            showGuessText.setText(extra.getString("guess"));
            Log.d("Name extra", "onCreate: " + extra.getString("name"));
            Log.d("Name extra 2", "onCreate: " + extra.getInt("age"));
        }

        showGuessText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("message_back", "From Second Activity");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
