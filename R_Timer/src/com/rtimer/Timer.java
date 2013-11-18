package com.rtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

public class Timer extends Activity {
	
	MediaPlayer mpAudio;
	
	@Override
	public void onBackPressed() {
		mpAudio.pause	();
	   this.finish();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		mpAudio = MediaPlayer.create(this, R.raw.beep);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer);
		
		// get intent 
		Intent intent = getIntent();
		
		// retrieve values from bundle
		final int mainTimerValue = intent.getIntExtra("mainTimerValue", 0);
		final int inBetweenTimerValue = intent.getIntExtra("inBetweenTimerValue", 0);		
//		final String timerType = intent.getStringExtra("timerType");
		// start timer
		String timerType = "seconds";
		if(timerType == "minutes"){
			final int minuteMain = mainTimerValue * 60;
			final int minuteBetween = inBetweenTimerValue*60;
			mainTimer(minuteMain, minuteBetween);
		}
		else {
			mainTimer(mainTimerValue,inBetweenTimerValue);
		}
		
		

		//((TextView)findViewById(R.id.timerLabel)).setText("" + mainTimerValue);
	}


	// main countdown timer
	protected void mainTimer(final int mainTimerValue, final int inBetweenTimerValue) {
		
		final TextView timerLabel =  ((TextView)findViewById(R.id.timerLabel));
		
		
		 new CountDownTimer(mainTimerValue*1000, 1000) {

		     public void onTick(long millisUntilFinished) {
		    	 
		    	 long minutes = millisUntilFinished / 60000;
		    	 long seconds = (millisUntilFinished % 60000)/1000;
		    	 timerLabel.setText("" + minutes + ":" + seconds);
		    	 
		         }

		     public void onFinish() {
		    	inBetweenTimer(mainTimerValue, inBetweenTimerValue);
		     }
		}.start();  
	}

	// in between countdown timer
	protected void inBetweenTimer(final int mainTimerValue, final int inBetweenTimerValue) {
			final TextView timerLabel =  ((TextView)findViewById(R.id.timerLabel));
			
			new CountDownTimer(inBetweenTimerValue*1000, 1000){
			
			 public void onTick(long millisUntilFinished) {

				mpAudio.start();
		    	 long minutes = millisUntilFinished / 60000;
		    	 long seconds = (millisUntilFinished % 60000)/1000;
		    	 timerLabel.setText("" + minutes + ":" + seconds);
		    	 
	
			 }
	
		     public void onFinish() {

		    	 mpAudio.pause();
		    	 mainTimer(mainTimerValue, inBetweenTimerValue);
		     }
		}.start();
	}

}
