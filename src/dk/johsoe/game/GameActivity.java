package dk.johsoe.game;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {
	
	GameView mGameView = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        mGameView = new GameView( this );
        mGameView.init();
        
        setContentView( mGameView );
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mGameView.onResume();
    }
     
    @Override
    protected void onPause() {
        super.onPause();
        mGameView.onPause();
    }
}