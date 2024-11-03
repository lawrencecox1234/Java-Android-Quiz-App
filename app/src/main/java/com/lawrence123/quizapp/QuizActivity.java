package com.lawrence123.quizapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

interface ItemClicked{
    void onClick(int position);
}

public class QuizActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    QuizAdapter quizAdapter;
    private ArrayList<QuestionsAnswers> questionsAnswers = new ArrayList<QuestionsAnswers>();

    static int questionNumber = 0;
    static int correctAnswers = 0;

    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 123) {
                        questionNumber = 0;
                        correctAnswers = 0;
                        loadQuestions();
                    }
                    if (result.getResultCode() == 124) {
                        questionNumber = 0;
                        correctAnswers = 0;
                        finish();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionNumber = 0;
        correctAnswers = 0;

        final Intent intent = getIntent();

        Bundle args = intent.getBundleExtra("BUNDLE");
        questionsAnswers = (ArrayList<QuestionsAnswers>) args.getSerializable("ARRAYLIST");

        //System.out.println(questionsAnswers.get(0).getQuestion());

        ImageButton imageButtonRestart = findViewById(R.id.img_restart);
        imageButtonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionNumber = 0;
                correctAnswers = 0;
                loadQuestions();
                //finish();
            }
        });

        ImageButton imageButtonHome = findViewById(R.id.img_home);
        imageButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionNumber = 0;
                correctAnswers = 0;
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(QuizActivity.this));

        loadQuestions();

    }

    public void loadQuestions() {

        String displayQuestion = questionsAnswers.get(questionNumber).getQuestion();

        TextView text_title = findViewById(R.id.edt_txt2);
        text_title.setText(displayQuestion);

        quizAdapter = new QuizAdapter(questionsAnswers.get(questionNumber).getAnswers(), this, new ItemClicked() {
            @Override
            public void onClick(int position) {
                resultWindow(position);
            }
        });

        recyclerView.setAdapter(quizAdapter);
    }

    private void resultWindow(int position) {

        LayoutInflater inflater = (LayoutInflater) QuizActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewInput = inflater.inflate(R.layout.question_summary, null, false);

        final TextView edtDesription = viewInput.findViewById(R.id.edt_description);

        int indexCorrect = questionsAnswers.get(questionNumber).getCorrectAnswer();

        if (position == indexCorrect){
            edtDesription.setText("Correct answer");
            correctAnswers += 1;
        }
        else {
            edtDesription.setText("Incorrect answer");
        }

        AlertDialog.Builder dialogObj = new AlertDialog.Builder(QuizActivity.this)
                .setView(viewInput)
                .setTitle("Result");

        dialogObj.setPositiveButton("Next question", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                questionNumber++;
                if (questionNumber > questionsAnswers.size() - 1){
                    questionNumber = 0;
                    Intent intent = new Intent(QuizActivity.this, ResultPage.class);

                    String questionCount = String.valueOf(questionsAnswers.size());
                    String resultString = "You correctly scored " + String.valueOf(correctAnswers) + "\n Out of " + questionCount + " questions";
                    intent.putExtra("correct_count", resultString);

                    activityResultLaunch.launch(intent);
                }
                else {
                    loadQuestions();
                }
            }
        }).show();

    }
}