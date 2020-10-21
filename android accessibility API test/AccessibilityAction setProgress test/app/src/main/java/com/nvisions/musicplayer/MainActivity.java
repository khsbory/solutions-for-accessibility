package com.nvisions.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;
    private Button mButtonPlay;
    private Button mButtonStop;
    private SeekBar mSeekBar;
    private TextView mPass;
    private TextView mDuration;
    private TextView mDue;
    private MediaPlayer mPlayer;
    private Handler mHandler;
    private Runnable mRunnable;
    int value1;
    boolean value2;
    //20.08.24 박정규
    //사용자가 조작했는지 체크
    boolean isFromUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AccessibilityManager accessibilityManager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);
        // Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        // Get the widget reference from xml layout
        mButtonPlay = findViewById(R.id.btn_play);
        mButtonStop = findViewById(R.id.btn_stop);
        mSeekBar = findViewById(R.id.seek_bar);
        mSeekBar.setVisibility(View.INVISIBLE);
        mButtonStop.setVisibility(View.INVISIBLE);
        mPass = findViewById(R.id.tv_pass);
        mDuration = findViewById(R.id.tv_duration);
        mDue = findViewById(R.id.tv_due);

        // Initialize the handler
        mHandler = new Handler();
        

        // Click listener for playing button
        mButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If media player another instance already running then stop it first
                stopPlaying();
                mButtonStop.setVisibility(View.VISIBLE);
                // Initialize media player
                mPlayer = MediaPlayer.create(mContext, R.raw.sunset);
                // Start the media player
                mPlayer.start();
                mSeekBar.setVisibility(View.VISIBLE);
                // Get the current audio stats
                getAudioStats();
                // Initialize the seek bar
                initializeSeekBar();
                String replay = getResources().getString(R.string.replay);
                mButtonPlay.setText(replay);
                mButtonPlay.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_SELECTED);
            }
        });

        // Set a click listener for top playing button
        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPlayer.isPlaying()) {
                    mPlayer.pause();
                    mButtonStop.setText(getResources().getString(R.string.play));
                } else {
                    mPlayer.start();
                    mButtonStop.setText(getResources().getString(R.string.pause));
                }
            }
        });

        // Set a change listener for seek bar
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //20.08.24 박정규
                //사용자가 조작해서 SeekBar의 Progress가 변경되면 isFromUser가 true값이 들어옴
                isFromUser = b;

                value1 = i;
                value2 = b;
                if (accessibilityManager.isEnabled()) {
                    if (mPlayer != null && value2) {
                        mPlayer.seekTo(value1 * 1000);
                        //SeekBar에서 특정 위치로 이동
                        Bundle arguments = new Bundle();
                        arguments.putFloat(AccessibilityNodeInfo.ACTION_ARGUMENT_PROGRESS_VALUE, (float) 50.0);//
                        mSeekBar.performAccessibilityAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS.getId(), arguments);

                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (mPlayer != null && value2) {
                    mPlayer.seekTo(value1 * 1000);
                }
            }
        });

        mSeekBar.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            @Override
            public void sendAccessibilityEvent(View host, int eventType) {
                //20.08.24 박정규
                //CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION는 SeekBar의 progress가 변경시 토크백으로 안내
                //사용자가 SeekBar를 조작하지 않았는데 progress가 진행된 경우는 토크백 안내 제외
                if (!isFromUser && eventType == AccessibilityEvent.CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION) {
                    return;
                }

                super.sendAccessibilityEvent(host, eventType);

                //원래 코드
//                if (eventType != AccessibilityEvent.CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION) {
//                    super.sendAccessibilityEvent(host, eventType);
//                }
            }

            @Override
            public boolean performAccessibilityAction(View host, int action, Bundle args) {
                //20.08.24 박정규
                //아래 코드는 필요없는 듯
//                switch (action) {
//                    case AccessibilityNodeInfo.ACTION_SCROLL_FORWARD:
//                    case AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD: {
//                        super.sendAccessibilityEvent(host, AccessibilityEvent.CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION);
//                        accessibilityManager.interrupt();
//                                          }
//                }
                return super.performAccessibilityAction(host, action, args);
            }
        });
    }

    protected void stopPlaying() {
        // If media player is not null then try to stop it
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
            if (mHandler != null) {
                mHandler.removeCallbacks(mRunnable);
            }
        }
    }

    protected void getAudioStats() {
        int duration = mPlayer.getDuration() / 1000; // In milliseconds
        int due = (mPlayer.getDuration() - mPlayer.getCurrentPosition()) / 1000;
        int pass = duration - due;
        String mPassText = getResources().getString(R.string.mPass);
        String second = getResources().getString(R.string.second);
        mPass.setText("" + pass + second);
        mPass.setContentDescription(mPassText + pass + second);

        mDuration.setText("" + duration + " seconds");
        mDue.setText("" + due + " seconds");
    }

    protected void initializeSeekBar() {
        mSeekBar.setMax(mPlayer.getDuration() / 1000);
        mRunnable = new Runnable() {
            @Override
            public void run() {
                if (mPlayer != null) {
                    int mCurrentPosition = mPlayer.getCurrentPosition() / 1000; // In milliseconds
                    mSeekBar.setProgress(mCurrentPosition);
                    getAudioStats();
                }
                mHandler.postDelayed(mRunnable, 1000);
            }
        };
        mHandler.postDelayed(mRunnable, 1000);
    }
}

