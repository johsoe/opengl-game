package dk.johsoe.game;

import dk.johsoe.game.movement.MovementController;
import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {
	
	GameView mGameView = null;
	MovementController mMovementController = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        mMovementController = new MovementController( this );
        mGameView = new GameView( this, mMovementController );
        mGameView.init();
        
        setContentView( mGameView );
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mGameView.onResume();
        mMovementController.register();
    }
     
    @Override
    protected void onPause() {
        super.onPause();
        mGameView.onPause();
        mMovementController.unregister();
    }
}