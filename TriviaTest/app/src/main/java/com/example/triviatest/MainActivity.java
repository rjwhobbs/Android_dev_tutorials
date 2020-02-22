package com.example.triviatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.triviatest.data.AnswerListAsyncResponse;
import com.example.triviatest.data.QuestionBank;
import com.example.triviatest.model.Question;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                Log.d("Inside", "processFinished: " + questionArrayList);
            }
        });
    }
}
