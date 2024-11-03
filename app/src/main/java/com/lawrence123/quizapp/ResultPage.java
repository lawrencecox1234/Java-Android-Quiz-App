package com.lawrence123.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultPage extends AppCompatActivity {

    TextView edtDescription;

    Button btnRestart, btnHome;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_page);

        final Intent intent = getIntent();

        linearLayout = findViewById(R.id.btn_holder);
        edtDescription = findViewById(R.id.edt_edit_description);

        btnRestart = findViewById(R.id.btn_restart);

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(123, intent);
                finish();
                //getOnBackPressedDispatcher().onBackPressed();
            }
        });

        btnHome = findViewById(R.id.btn_home);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(124, intent);
                finish();
                //getOnBackPressedDispatcher().onBackPressed();
            }
        });

        edtDescription.setText(intent.getStringExtra("correct_count"));

    }

}
