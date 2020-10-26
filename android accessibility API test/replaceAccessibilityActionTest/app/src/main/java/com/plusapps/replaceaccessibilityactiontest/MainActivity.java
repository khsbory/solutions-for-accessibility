package com.plusapps.replaceaccessibilityactiontest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;

import android.os.Bundle;
import android.view.View;
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
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });


        ViewCompat.replaceAccessibilityAction(button, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK, "클릭", new AccessibilityViewCommand() {
            @Override
            public boolean perform(@NonNull View view, @Nullable CommandArguments args) {
                return true;
            }
        });
    }
}
