package dk.johsoe.game;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GameView extends GLSurfaceView {

	private GameRender mRender;
	
	
	public GameView(Context context) {
		super(context);
	}
	
	public void init() {
		mRender = new GameRender();
		setRenderer( mRender );
		
	}

}
