package david.letterman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	/*
	 *  Method called by the game loop from the application every time rendering should be performed.
	 *  Game logic updates are usually also performed in this method.
	 */
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		//ed hall comment
		//ed hall desktop comment test
		//ed hall is a huge pussy
		//kek
	}
	public void resize (int width, int height) {
	}

	/*
	 * On Android this method is called when the Home button is pressed or an incoming call is received.
	 * On desktop this is called just before dispose() when exiting the application.
	 * A good place to save the game state.
	 */
	public void pause () {
	}

	public void resume () {
	}

	public void dispose () {
	}

}
