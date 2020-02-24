package com.example.triviatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.triviatest.data.AnswerListAsyncResponse;
import com.example.triviatest.data.QuestionBank;
import com.example.triviatest.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTextview;
    private TextView questionCounterTextview;
    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private int currentQuestionIndex = 0;
    private List<Question> questionList;
    private int questionListSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        questionCounterTextview = findViewById(R.id.counter_text);
        questionTextview = findViewById(R.id.question_textview);


            nextButton.setOnClickListener(this);
            prevButton.setOnClickListener(this);
            trueButton.setOnClickListener(this);
            falseButton.setOnClickListener(this);



        questionList = new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {

                questionTextview.setText(questionArrayList.get(currentQuestionIndex).getAnswer());
                questionListSize = questionArrayList.size();
                Log.d("xx1", "processFinished: " + questionArrayList);
            }
        });
        Log.d("xx2", "processFinished: " + questionList);
    }

    public void debug() {
        Log.d("xx3", "debug " + questionList);
    }

    @Override
    public void onClick(View v) {
        if (questionListSize != 0) {
            switch (v.getId()) {
                case R.id.prev_button:
                    currentQuestionIndex = (currentQuestionIndex + questionListSize - 1) % questionListSize;
                    updateQuestion();
                    break;
                case R.id.next_button:
                    currentQuestionIndex = (currentQuestionIndex + 1) % questionListSize;
                    updateQuestion();
                    break;

                case R.id.true_button:
                    break;

                case R.id.false_button:
                    break;

            }
        }
    }

    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getAnswer();
        questionTextview.setText(question);
//        questionCounterTextview.setText(currentQuestionIndex + " / " + questionList.size()); // 0 / 234

    }
}
