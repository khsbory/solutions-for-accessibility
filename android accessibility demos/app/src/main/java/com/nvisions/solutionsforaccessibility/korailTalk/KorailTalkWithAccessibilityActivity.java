package com.nvisions.solutionsforaccessibility.korailTalk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.TextView;

import com.nvisions.solutionsforaccessibility.R;

import static androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK;

public class KorailTalkWithAccessibilityActivity extends AppCompatActivity {

    private int stationType = 0;
    private final int START_STATION = 0;
    private final int DESTINATION_STATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korail_talk_with_accessibility);
        setButtonsForAccessibility();
    }

    private void setButtonsForAccessibility() {
        TextView startStationView = findViewById(R.id.startStation);
        ViewCompat.replaceAccessibilityAction(startStationView, ACTION_CLICK,
                "출발역 변경", null
                );

        TextView destinationStationView = findViewById(R.id.destinationStation);
        ViewCompat.replaceAccessibilityAction(destinationStationView, ACTION_CLICK,
                "도착역 변경", null);
    }


    private void showBoard() {
        ConstraintLayout board = findViewById(R.id.board);
        board.setVisibility(View.VISIBLE);

        Button closeButton = findViewById(R.id.closeButton);
        closeButton.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
    }

    private void hideBoard() {
        ConstraintLayout board = findViewById(R.id.board);
        board.setVisibility(View.GONE);

        if (stationType == START_STATION) {
            TextView startStationView = findViewById(R.id.startStation);
            startStationView.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
           } else {
            TextView destinationStationView = findViewById(R.id.destinationStation);
            destinationStationView.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
          }
    }

    public void closeBoard(View view) {
        hideBoard();
    }

    public void setStartStation(View view) {
        showBoard();
        stationType = START_STATION;

    }

    public void setDestinationStation(View view) {
        showBoard();
        stationType = DESTINATION_STATION;
    }

    public void changeStation(View view) {
        String startStation = null;
        TextView startStationView = findViewById(R.id.startStation);
        startStation = startStationView.getText().toString();

        String destinationStation = null;
        TextView destinationStationView = findViewById(R.id.destinationStation);
        destinationStation = destinationStationView.getText().toString();


        startStationView.setText(destinationStation);
        destinationStationView.setText(startStation);

        view.announceForAccessibility("출발 " + destinationStation + " 도착 " + startStation);
    }

    public void setBusanStation(View view) {
        if (stationType == START_STATION) {
            TextView startStationView = findViewById(R.id.startStation);
            startStationView.setText("부산");
        } else {
            TextView destinationStationView = findViewById(R.id.destinationStation);
            destinationStationView.setText("부산");
        }
        hideBoard();
    }

    public void setDongdaeguStation(View view) {
        if (stationType == START_STATION) {
            TextView startStationView = findViewById(R.id.startStation);
            startStationView.setText("동대구");
        } else {
            TextView destinationStationView = findViewById(R.id.destinationStation);
            destinationStationView.setText("동대구");
        }
        hideBoard();
    }
}