package joat.mon.stopthenoise;


import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;



public class MainActivity extends Activity implements OnAudioFocusChangeListener {



	OnAudioFocusChangeListener myListner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

    	stopNoise();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	public void stopNoise() {
		int result;
		AudioManager myAudioManager;
		
		myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
	
	
		// Request audio focus for playback
		result = myAudioManager.requestAudioFocus(this,
		                                 // Use the music stream.
		                                 AudioManager.STREAM_MUSIC,
		                                 // Request permanent focus.
		                                 AudioManager.AUDIOFOCUS_GAIN);
		   
		if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
		    
			Toast.makeText(getApplicationContext(), "Grabbed Audio Focus.", Toast.LENGTH_LONG).show(); 
			
		    // We did our best to stop other players, abandon audio focus 
			myAudioManager.abandonAudioFocus((OnAudioFocusChangeListener) this);
		}	else {
			Toast.makeText(getApplicationContext(), "Failed To Get Audio Focus", Toast.LENGTH_LONG).show(); 
			}
		finish();
	}
	
	public void testButtonClicked(View v) {
		//Get which button and change color of box
		
		 switch (v.getId()) {
		    case R.id.btnStopNoise:
		    	Log.e("Button Is", "Stop Noise");
		    	stopNoise();
		        break;
		 }
	}
	//This code is required to make 'implements OnAudioFocusChangeListener' happy
	// DO NOT REMOVE
	@Override
	public void onAudioFocusChange(int focusChange) {
		// Nothing to do here!
		
	}
	
	
	
}
