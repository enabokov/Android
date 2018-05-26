package com.example.eduardnabokov.lab4;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView textMaxTime;
    private TextView textCurrentPosition;
    private Button buttonPause;
    private Button buttonStart;
    private SeekBar seekBar;
    private Handler threadHandler = new Handler();
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textCurrentPosition = (TextView)this.findViewById(R.id.textView_currentPosion);
        this.textMaxTime=(TextView) this.findViewById(R.id.textView_maxTime);
        this.buttonStart= (Button) this.findViewById(R.id.button_start);
        this.buttonPause= (Button) this.findViewById(R.id.button_pause);

        this.buttonPause.setEnabled(false);

        this.seekBar= (SeekBar) this.findViewById(R.id.seekBar);
        this.seekBar.setClickable(false);

        int songId = this.getRawResIdByName("onerepublic");
        this.mediaPlayer = MediaPlayer.create(this, songId);
    }

    public int getRawResIdByName(String resName)  {
        return this.getResources().getIdentifier(resName, "raw", this.getPackageName());
    }

    private String millisecondsToString(int milliseconds)  {
        long minutes = TimeUnit.MILLISECONDS.toMinutes((long) milliseconds);
        long seconds =  TimeUnit.MILLISECONDS.toSeconds((long) milliseconds) ;
        return minutes+":"+ seconds;
    }


    public void doStart(View view)  {
        int duration = this.mediaPlayer.getDuration();

        int currentPosition = this.mediaPlayer.getCurrentPosition();
        if(currentPosition== 0)  {
            this.seekBar.setMax(duration);
            String maxTimeString = this.millisecondsToString(duration);
            this.textMaxTime.setText(maxTimeString);
        } else if(currentPosition== duration)  {
            this.mediaPlayer.reset();
        }
        this.mediaPlayer.start();
        UpdateSeekBarThread updateSeekBarThread= new UpdateSeekBarThread();
        threadHandler.postDelayed(updateSeekBarThread,50);

        this.buttonPause.setEnabled(true);
        this.buttonStart.setEnabled(false);
    }

    class UpdateSeekBarThread implements Runnable {

        public void run()  {
            int currentPosition = mediaPlayer.getCurrentPosition();
            String currentPositionStr = millisecondsToString(currentPosition);
            textCurrentPosition.setText(currentPositionStr);

            seekBar.setProgress(currentPosition);
            // Delay thread 50 milisecond.
            threadHandler.postDelayed(this, 50);
        }
    }

    public void doPause(View view)  {
        this.mediaPlayer.pause();
        this.buttonPause.setEnabled(false);
        this.buttonStart.setEnabled(true);
    }

    public void doRewind(View view)  {
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        int duration = this.mediaPlayer.getDuration();
        int SUBTRACT_TIME = 5000;

        if(currentPosition - SUBTRACT_TIME > 0 )  {
            this.mediaPlayer.seekTo(currentPosition - SUBTRACT_TIME);
        }
    }

    public void doFastForward(View view)  {
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        int duration = this.mediaPlayer.getDuration();
        int ADD_TIME = 5000;

        if(currentPosition + ADD_TIME < duration)  {
            this.mediaPlayer.seekTo(currentPosition + ADD_TIME);
        }
    }

}