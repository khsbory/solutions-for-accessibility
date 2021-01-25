package com.example.oninitializeaccessibilityeventtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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


        if (Build.VERSION.SDK_INT >= 14) {
            // If the API version is equal of higher than the version in
            // which onInitializeAccessibilityNodeInfo was introduced we
            // register a delegate with a customized implementation.
            Button button = findViewById(R.id.button);

            button.setAccessibilityDelegate(new View.AccessibilityDelegate() {
                public void onInitializeAccessibilityEvent(View host,
                                                              AccessibilityEvent event1) {

                    Log.d("plusapps", "onInitializeAccessibilityEvent");
                    // Let the default implementation populate the info.
                    super.onInitializeAccessibilityEvent(host, event1);
                    // Set some other information.
                    event1.setChecked(false);
                }
            });
        }



    }
}