package com.example.setbeforetextexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private EditText mInputText;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initEditText();
        initTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }

    private void initEditText() {
        mInputText = findViewById(R.id.input);
    }


    private void initTimer() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateEditText();
            }
        }, 1000, 1000 * 10);
    }

    private void updateEditText() {

        CharSequence beforeText = mInputText.getText();
        AccessibilityEvent event = AccessibilityEvent.obtain(
                AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
        mInputText.onInitializeAccessibilityEvent(event);
        mInputText.onPopulateAccessibilityEvent(event);
        event.setFromIndex(0);
        event.setRemovedCount(beforeText.length());
        //event.setAddedCount(text.length());
        event.setBeforeText(beforeText);
        //event.setSource(this,
               // AccessibilityNodeProviderImpl.VIRTUAL_VIEW_ID_INPUT);
        mInputText.getParent().requestSendAccessibilityEvent(mInputText, event);


    }
}