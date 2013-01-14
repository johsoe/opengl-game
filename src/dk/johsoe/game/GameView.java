package dk.johsoe.game;

import android.content.Context;
import android.opengl.GLSurfaceView;
import dk.johsoe.game.movement.MovementController;

public class GameView extends GLSurfaceView {

	private GameRender mRender;
	private Context mContext;
	private MovementController mMovementController = null;
	
	public GameView(Context context, MovementController movementController) {
		super(context);
		mContext = context;
		mMovementController = movementController;
	}
	
	public void init() {
		mRender = new GameRender( mContext, mMovementController );
		setRenderer( mRender );
		
	}

}
