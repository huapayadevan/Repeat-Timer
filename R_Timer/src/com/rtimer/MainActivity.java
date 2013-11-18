package com.rtimer;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends Activity {

	private String timerType = "seconds";
	
	@Override
	public void onBackPressed() {
	   this.finish();
	}
//	
//	public void onSwitchClick(View view) {
//	    // Is the toggle on?
//	    boolean on = ((Switch) view).isChecked();
//	    
//	    if (on) {
//	        timerType = "minutes";
//	    } else {
//	        timerType = "seconds";
//	    }
//	    
//	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startButtonClick(View view){

		// get values for mainTimerInput
		EditText mainTimerInput = (EditText)findViewById(R.id.mainTimerInput);
		String mainTimerString = mainTimerInput.getText().toString();
		int mainTimerValue = Integer.parseInt(mainTimerString);
		
		
		// get values for inBetweenTimerInput
		EditText inBetweenTimerInput = (EditText)findViewById(R.id.inBetweenTimerInput);
		String inBetweenTimerString = inBetweenTimerInput.getText().toString();
		int inBetweenTimerValue = Integer.parseInt(inBetweenTimerString);


		// create intent to switch activity
		Intent switchActivity = new Intent(this, Timer.class);
		
		
//		if( ((Switch)findViewById(R.id.timerSwitch)).isChecked() ) timerType = "minutes";
	    
		// add parameters to pass to timer activity
		Bundle timerValues = new Bundle();
		timerValues.putInt("mainTimerValue", mainTimerValue);
		timerValues.putInt("inBetweenTimerValue", inBetweenTimerValue);
//		timerValues.putString("timerType", timerType);
		switchActivity.putExtras(timerValues);
		
		startActivity(switchActivity);
	}

}
