package com.nvisions.solutionsforaccessibility.labelFor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nvisions.solutionsforaccessibility.R;

public class LabelForActivity extends AppCompatActivity implements View.OnClickListener {
    private Button example1;
    private Button example2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_for);

        if(getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Button example1 = findViewById(R.id.example1);
        Button example2 = findViewById(R.id.example2);
        example1.setOnClickListener(this);
        example2.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.example1:
                Intent i = new Intent(getApplicationContext(), LabelForExample1Activity.class);
                startActivity(i);
                break;
            case R.id.example2:
                Intent i2 = new Intent(getApplicationContext(), LabelForExample2Activity.class);
                startActivity(i2);
                break;
        }
    }
}