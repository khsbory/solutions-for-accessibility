package com.nvisions.solutionsforaccessibility.korailTalk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import com.nvisions.solutionsforaccessibility.R;

public class KorailTalkWithoutAccessibilityActivity extends AppCompatActivity {


    private int stationType = 0;
    private final int START_STATION = 0;
    private final int DESTINATION_STATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korail_talk_without_accessibility);
    }


    private void showBoard() {
        ConstraintLayout board = findViewById(R.id.board);
        board.setVisibility(View.VISIBLE);

    }

    private void hideBoard() {
        ConstraintLayout board = findViewById(R.id.board);
        board.setVisibility(View.GONE);
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
    }

    public void setBusanStation(View view) {
        if (stationType == START_STATION) {
            TextView startStationView = findViewById(R.id.startStation);
            startStationView.setText("부산");
        } else {
            TextView destinationStationView = findViewById(R.id.destinationStation);
            destinationStationView.setText("부산");
        }
    }

    public void setDongdaeguStation(View view) {
        if (stationType == START_STATION) {
            TextView startStationView = findViewById(R.id.startStation);
            startStationView.setText("동대구");
        } else {
            TextView destinationStationView = findViewById(R.id.destinationStation);
            destinationStationView.setText("동대구");
        }
    }
}