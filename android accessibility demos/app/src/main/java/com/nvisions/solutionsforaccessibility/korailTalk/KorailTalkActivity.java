package com.nvisions.solutionsforaccessibility.korailTalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nvisions.solutionsforaccessibility.R;

public class KorailTalkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korail_talk);
    }

    public void launchKarailTalkWithoutAccessibilityActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), KorailTalkWithoutAccessibilityActivity.class);
        startActivity(intent);
    }

    public void launchKarailTalkWithAccessibilityActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), KorailTalkWithAccessibilityActivity.class);
        startActivity(intent);
    }
}