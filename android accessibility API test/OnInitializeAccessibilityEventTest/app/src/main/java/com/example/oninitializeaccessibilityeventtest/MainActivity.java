package com.example.oninitializeaccessibilityeventtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();

    }

    private void initButton() {
        Button button = findViewById(R.id.button);

        //접근성 관련 초기값을 지정 
        AccessibilityEvent event = new AccessibilityEvent();
        event.setChecked(false);
        button.onInitializeAccessibilityEvent(event);

    }
}