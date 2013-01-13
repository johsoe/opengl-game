package dk.johsoe.game;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GameView extends GLSurfaceView {

	private GameRender mRender;
	private Context mContext;
	
	public GameView(Context context) {
		super(context);
		mContext = context;
	}
	
	public void init() {
		mRender = new GameRender( mContext );
		setRenderer( mRender );
		
	}

}
