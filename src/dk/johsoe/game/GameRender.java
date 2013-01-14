package dk.johsoe.game;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import dk.johsoe.game.model.Square;
import dk.johsoe.game.model.TextureSquare;
import dk.johsoe.game.movement.MovementController;

public class GameRender implements GLSurfaceView.Renderer {

	private float[] mCameraMatrix = new float[16];
	private float[] mProjectionMatrix = new float[16];
	private MovementController mMovementController = null;
	
	private final String vertexShaderCode =
	        // This matrix member variable provides a hook to manipulate
	        // the coordinates of objects that use this vertex shader
	        "uniform mat4 uMVPMatrix;   \n" +

	        "attribute vec4 vPosition;  \n" +
	        "void main(){               \n" +

	        // the matrix must be included as part of gl_Position
	        " gl_Position = uMVPMatrix * vPosition; \n" +

	        "}  \n";
	
	private Square mSquare = null;
	private TextureSquare mTexSquare = null;
	private Context mContext = null;
	
	public GameRender( Context context, MovementController movementController ) {
		mSquare = new Square();
		mTexSquare = new TextureSquare();
		mContext = context;
		mMovementController = movementController;
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		/*
		// White bg
		GLES20.glClearColor(1f, 1f, 1f, 1f);
		
	    final float eyeX = 0.0f;
	    final float eyeY = 0.0f;
	    final float eyeZ = 1.5f;
	 
	    final float lookX = 0.0f;
	    final float lookY = 0.0f;
	    final float lookZ = -5.0f;
	 
	    final float upX = 0.0f;
	    final float upY = 1.0f;
	    final float upZ = 0.0f;
	 
	    // Set the view matrix. This matrix can be said to represent the camera position.
	    Matrix.setLookAtM(mCameraMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);
	    */
		// Load the texture for the square
		mTexSquare.loadGLTexture(gl, mContext);

		gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping ( NEW )
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 	//Black Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do

		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		/*
		// Update viewport to new width and height
		GLES20.glViewport(0, 0, width, height);
		
		final float ratio = (float) width / height;
	    final float left = -ratio;
	    final float right = ratio;
	    final float bottom = -1.0f;
	    final float top = 1.0f;
	    final float near = 1.0f;
	    final float far = 10.0f;
	 
	    Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
	    */
		if(height == 0) { 						//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}

		gl.glViewport(0, 0, width, height); 	//Reset The Current Viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix

		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity(); 		
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// clear Screen and Depth Buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		// Reset the Modelview Matrix
		gl.glLoadIdentity();

		// Drawing
		gl.glTranslatef(mMovementController.getPitch()*-1f, -5f, -20.0f);	
		//gl.glTranslatef(0.0f, 0.0f, -5.0f);		// move 5 units INTO the screen
		// is the same as moving the camera 5 units away
		//mSquare.draw(gl);						// Draw the triangle
		mTexSquare.draw( gl );

	}

}
