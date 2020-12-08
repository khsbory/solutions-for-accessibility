package com.nvisions.solutionsforaccessibility.korailTalk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nvisions.solutionsforaccessibility.R;

import static androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK;

public class KorailTalkWithAccessibilityActivity extends AppCompatActivity {


    private int peopleCountInt = 1;
    private SeekBar seekBar;

    private int stationType = 0;
    private final int START_STATION = 0;
    private final int DESTINATION_STATION = 1;

    private String startStationName = "서울";
    private String destinationStationName = "대전";


    public static void handleVolumeUp() {
    }

    public static void handleVolumeDown() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korail_talk_with_accessibility);
        setTitle(getString(R.string.goodExample));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setButtonsForAccessibility();
    }

    private void setButtonsForAccessibility() {
        TextView startStationView = findViewById(R.id.startStation);
        ViewCompat.replaceAccessibilityAction(startStationView, ACTION_CLICK, getString(R.string.changeStartStation), null);
        TextView destinationStationView = findViewById(R.id.destinationStation);
        ViewCompat.replaceAccessibilityAction(destinationStationView, ACTION_CLICK, getString(R.string.changeArrivingStation), null);
    }

    private void showBoard() {
        TextView seoulStationView = findViewById(R.id.seoul);
        seoulStationView.setSelected(false);
        TextView busanStationView = findViewById(R.id.busan);
        busanStationView.setSelected(false);
        TextView dongdaeguStationView = findViewById(R.id.dongdaegu);
        dongdaeguStationView.setSelected(false);
        TextView daejeonStationView = findViewById(R.id.daejeon);
        daejeonStationView.setSelected(false);

        seoulStationView.setBackgroundColor(Color.TRANSPARENT);
        busanStationView.setBackgroundColor(Color.TRANSPARENT);
        dongdaeguStationView.setBackgroundColor(Color.TRANSPARENT);
        daejeonStationView.setBackgroundColor(Color.TRANSPARENT);

        if (stationType == START_STATION) {
            switch (startStationName) {
                case "서울":
                    seoulStationView.setSelected(true);
                    seoulStationView.setBackgroundColor(Color.BLUE);
                    break;
                case "부산":
                    busanStationView.setSelected(true);
                    busanStationView.setBackgroundColor(Color.BLUE);
                    break;
                case "동대구":
                    dongdaeguStationView.setSelected(true);
                    dongdaeguStationView.setBackgroundColor(Color.BLUE);
                    break;
                case "대전":
                    daejeonStationView.setSelected(true);
                    daejeonStationView.setBackgroundColor(Color.BLUE);
                    break;
            }
        } else {
            switch (destinationStationName) {
                case "서울":
                    seoulStationView.setSelected(true);
                    seoulStationView.setBackgroundColor(Color.BLUE);
                    break;
                case "부산":
                    busanStationView.setSelected(true);
                    busanStationView.setBackgroundColor(Color.BLUE);
                    break;
                case "동대구":
                    dongdaeguStationView.setSelected(true);
                    dongdaeguStationView.setBackgroundColor(Color.BLUE);
                    break;
                case "대전":
                    daejeonStationView.setSelected(true);
                    daejeonStationView.setBackgroundColor(Color.BLUE);
                    break;
            }
        }

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
            startStationView.setSelected(false);
            startStationView.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
           } else {
            TextView destinationStationView = findViewById(R.id.destinationStation);
            destinationStationView.setSelected(false);
            destinationStationView.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
          }
    }

    public void closeBoard(View view) {
        hideBoard();
    }

    public void setStartStation(View view) {

        stationType = START_STATION;
        showBoard();
        TextView startStationView = findViewById(R.id.startStation);
        TextView destinationStationView = findViewById(R.id.destinationStation);
        startStationView.setSelected(true);
        destinationStationView.setSelected(false);
            }

    public void setDestinationStation(View view) {

        stationType = DESTINATION_STATION;
        showBoard();
        TextView destinationStationView = findViewById(R.id.destinationStation);
        TextView startStationView = findViewById(R.id.startStation);
        destinationStationView.setSelected(true);
        startStationView.setSelected(false);
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
            startStationName = "부산";
        } else {
            TextView destinationStationView = findViewById(R.id.destinationStation);
            destinationStationView.setText("부산");
            destinationStationName = "부산";
        }
        hideBoard();
    }

    public void setDongdaeguStation(View view) {
        if (stationType == START_STATION) {
            TextView startStationView = findViewById(R.id.startStation);
            startStationView.setText("동대구");
            startStationName = "동대구";
        } else {
            TextView destinationStationView = findViewById(R.id.destinationStation);
            destinationStationView.setText("동대구");
            destinationStationName = "동대구";
        }
        hideBoard();
    }

    public void setSeoulStation(View view) {
        if (stationType == START_STATION) {
            TextView startStationView = findViewById(R.id.startStation);
            startStationView.setText(getString(R.string.seoul));
            startStationName = getString(R.string.seoul);
        } else {
            TextView destinationStationView = findViewById(R.id.destinationStation);
            destinationStationView.setText(getString(R.string.seoul));
            destinationStationName = getString(R.string.seoul);
        }
        hideBoard();
    }

    public void setDaejeonStation(View view) {
        if (stationType == START_STATION) {
            TextView startStationView = findViewById(R.id.startStation);
            startStationView.setText(getString(R.string.daejeon));
            startStationName = getString(R.string.daejeon);
        } else {
            TextView destinationStationView = findViewById(R.id.destinationStation);
            destinationStationView.setText(getString(R.string.daejeon));
            destinationStationName = getString(R.string.daejeon);
        }
        hideBoard();
    }

    public void setPeopleCount(View view) {
        peopleCountInt = 1;

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("알림");
        alert.setMessage("인원수를 선택해주세요");

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        seekBar=new SeekBar(this);
        int id = View.generateViewId();
        seekBar.setId(id);
        seekBar.setMin(1);
        seekBar.setMax(8);

        final TextView peopleCount = new TextView(this);
        peopleCount.setText("1명");
        peopleCount.setAccessibilityLiveRegion(View.ACCESSIBILITY_LIVE_REGION_ASSERTIVE);
        peopleCount.setPadding(20, 20, 0,0);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                peopleCount.setText(i + "명");
                peopleCountInt = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        linearLayout.addView(seekBar);
        linearLayout.addView(peopleCount);
        alert.setView(linearLayout);

        alert.setPositiveButton("확인",new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog,int id)
            {
                dialog.dismiss();

                TextView peopleCountView = findViewById(R.id.peopleCount);
                peopleCountView.setText(peopleCountInt + "명");
            }
        });

        alert.setNegativeButton("취소",new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog,int id)
            {
                dialog.dismiss();
            }
        });

        seekBar.setAccessibilityDelegate(new CustomSeekBarDelegate());

        //토크백이 활성화된 상태에서 아래 코드는 작동안함
//        alert.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
//                Log.d("plusapps", "onKey");
//                if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
//                    return false;
//                }
//
//                if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//                    Log.d("plusapps", "banana");
//                    if (seekBar != null && seekBar.isShown()) {
//                        Log.d("plusapps", "banana2");
//                        peopleCountInt = peopleCountInt - 1;
//                        if (peopleCountInt < 1) {
//                            peopleCountInt = 1;
//                        }
//
//                        seekBar.setProgress(peopleCountInt);
//                    }
//                    //return true;
//                } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
//                    Log.d("plusapps", "apple");
//                    if (seekBar != null && seekBar.isShown()) {
//                        Log.d("plusapps", "apple2");
//                        peopleCountInt = peopleCountInt + 1;
//                        if (peopleCountInt > 8 ) {
//                            peopleCountInt = 8;
//                        }
//
//                        seekBar.setProgress(peopleCountInt);
//                    }
//
//                    //return true;
//                }
//
//
//
//                return false;
//            }
//        });

        alert.show();
    }
    /**
     * Create a custom delegate to modify what Talkback is going to read out loud
     */
    private class CustomSeekBarDelegate extends View.AccessibilityDelegate
    {
        /**
         * If the selected view is the slider, populate the text to read by talkback.
         * On Android 10 and 9, the view got selected from the beginning without touch the slider,
         * so the TYPE_VIEW_SELECTED event is controlled.
         * The text should be overwritten to trigger what Talkback do need to read.
         *
         * @param host The view selected
         * @param event The event to initialise
         */
        @Override public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event)
        {


//            super.onInitializeAccessibilityEvent(host, event);
//            if (event.getEventType() != AccessibilityEvent.TYPE_VIEW_SELECTED)
//            {
//
//            }
        }

        /**
         * Send all accessibility events except the focused accessibility event
         * because it reads the percentage, so it needs to be changed to no focused to read
         * the sliderText.
         *
         * @param host the view selected
         * @param eventType The accessibility event to send
         */
//        @Override public void sendAccessibilityEvent(View host, int eventType){
//            if (eventType == AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED)
//            {
//                eventType = AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED;
//            }

//            super.sendAccessibilityEvent(host, eventType);
//        }

        /**
         * If the slider changes, it won't send the AccessibilityEvent TYPE_WINDOW_CONTENT_CHANGED
         * because it reads the percentages, so in that way it will read the sliderText.
         * On Android 10 and 9, the view got selected when it changes, so the TYPE_VIEW_SELECTED
         * event is controlled.
         *
         * @param host the view selected
         * @param event the accessibility event to send
         */
//        @Override public void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event)
//        {
//            if (event.getEventType() != AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
//                    && event.getEventType() != AccessibilityEvent.TYPE_VIEW_SELECTED)
//            {
//                super.sendAccessibilityEventUnchecked(host, event);
//            }
//        }
    }
}

