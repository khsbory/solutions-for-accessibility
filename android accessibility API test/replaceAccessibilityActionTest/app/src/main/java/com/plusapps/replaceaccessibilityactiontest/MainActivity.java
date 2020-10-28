package com.plusapps.replaceaccessibilityactiontest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;

import android.os.Bundle;
import android.util.Log;
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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Log.d("plusapps", "버튼 클릭!");
            }
        });


        ViewCompat.replaceAccessibilityAction(button, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK, "클릭", new AccessibilityViewCommand() {
            @Override
            public boolean perform(@NonNull View view, @Nullable CommandArguments args) {
                Log.d("plusapps", "replaceAccessibilityAction 버튼 클릭!");
                //return true를 하면 여기서 끝남. false를 하면 ClickListener가 호출됨
                return true;
            }
        });
    }
}
