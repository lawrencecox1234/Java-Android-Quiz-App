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

interface ItemClickedHome{
    void onClick(int position);
}

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    private ArrayList<QuestionsAnswers> questionsAnswersHistory = new ArrayList<QuestionsAnswers>();
    private ArrayList<QuestionsAnswers> questionsAnswersScience = new ArrayList<QuestionsAnswers>();
    private ArrayList<QuestionsAnswers> questionsAnswersGeography = new ArrayList<QuestionsAnswers>();

    String[] quizCategories = {"Science", "History", "Geography"};

    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setQuestionsAnswersScience();
        setQuestionAnswersHistory();
        setQuestionsAnswersGeography();

        recyclerView = findViewById(R.id.recycler_home);

        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

        homeAdapter = new HomeAdapter(quizCategories, this, new ItemClicked() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                Bundle args = new Bundle();
                if (position == 0) {
                    args.putSerializable("ARRAYLIST", (Serializable) questionsAnswersScience);
                }
                else if (position == 1){
                    args.putSerializable("ARRAYLIST", (Serializable) questionsAnswersHistory);
                }
                else {
                    args.putSerializable("ARRAYLIST", (Serializable) questionsAnswersGeography);
                }
                intent.putExtra("BUNDLE", args);
                activityResultLaunch.launch(intent);

            }
        });

        recyclerView.setAdapter(homeAdapter);

    }

    public void setQuestionsAnswersScience(){

        String[] scienceQuestions = {"What is the chemical symbol of iron?", "What is the approximate value of the acceleration due to gravity?",
                "What is the second element in the periodic table?", "Which animal can lay eggs?", "How long ago was the asteroid that killed the dinosaurs?"};

        String[][] scienceAnswers = new String[][] {{"I", "Fe", "Ir", "Ts"},
                {"8.5", "8.7", "9.2", "9.8"}, {"Helium", "Nitrogen", "Hydrogen", "Lithium"}, {"Rats", "Platypus", "Bats", "Shrews"},
                {"51 million years ago", "62 million years ago", "65 million years ago", "66 million years ago"}};

        int[] correctAnswers = {1,3,0,1,3};

        for (int i = 0; i < scienceAnswers.length; i++){
            questionsAnswersScience.add(new QuestionsAnswers(scienceQuestions[i], scienceAnswers[i], correctAnswers[i]));
        }

    }

    public void setQuestionAnswersHistory(){

        String[] historyQuestions = {"Who was Henry the VIII's 5th wife?", "When did Queen Victoria die?", "When did World War 1 end?",
        "When did William the Conqueror start ruling England?","When did hitler invade the soviet union?"};

        String[][] historyAnswers = new String[][] {{"Jane Seymour", "Catherine Parr", "Catherine Howard", "Anne of Cleves"},
                {"1896", "1897", "1898", "1901"}, {"1902", "1918", "1923", "1929"}, {"1052", "1059", "1062", "1066"},
                {"1940", "1941", "1942", "1943"}};

        int[] correctAnswers = {2,3,1,3,1};

        for (int i = 0; i < historyAnswers.length; i++){
            questionsAnswersHistory.add(new QuestionsAnswers(historyQuestions[i], historyAnswers[i], correctAnswers[i]));
        }

    }

    public void setQuestionsAnswersGeography(){

        String[] geographyQuestions = {"What is the largest country in the world by land mass?", "What is the capital of Indonesia?",
                "What is the capital of Australia?", "How many states are there in America?", "What is the most northern City in England?"};

        String[][] geographyAnswers = new String[][] {{"USA", "China", "Canada", "Russia"},
                {"Bali", "Jakarta", "Sesjha", "Manila"}, {"Perth", "Sydney", "Canberra", "Melbourne"},
                {"49", "50", "51", "52"}, {"Newcastle", "Liverpool", "Sheffield", "Leeds"}};

        int[] correctAnswers = {3,1,2,1,0};

        for (int i = 0; i < geographyAnswers.length; i++){
            questionsAnswersGeography.add(new QuestionsAnswers(geographyQuestions[i], geographyAnswers[i], correctAnswers[i]));
        }

    }

}