package dk.johsoe.game.movement;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;


public class MovementController implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mOrientation;
	private Context mContext;
	
	private float mPitch = 0f;
	
	public MovementController( Context context ) {
		mContext = context;
		
		mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
	    mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	}
	
	@Override
	public void onAccuracyChanged( Sensor sensor, int accuracy ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged( SensorEvent event ) {
		float azimuth_angle = event.values[0];
	    float pitch_angle = event.values[1];
	    float roll_angle = event.values[2];
	    
	    //Log.i("","roll_angle --> " + roll_angle);
	    //Log.i("","pitch_angle --> " + pitch_angle);
	    
	    mPitch = pitch_angle;
	}
	
	public void register() {
		mSensorManager.registerListener(this, mOrientation, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	public void unregister() {
		mSensorManager.unregisterListener(this);
	}

}
