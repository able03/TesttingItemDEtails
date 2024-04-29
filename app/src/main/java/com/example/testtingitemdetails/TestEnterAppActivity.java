package com.example.testtingitemdetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class TestEnterAppActivity extends AppCompatActivity
{

    private EditText et_name, et_age;
    private MaterialButton btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_enter_app);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initValues();
        btn_submit.setOnClickListener(click -> {
            new CardStaticModel(et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
    private void initValues()
    {
        et_name = findViewById(R.id.editTextText);
        et_age = findViewById(R.id.editTextText2);
        btn_submit = findViewById(R.id.button2);
    }


}