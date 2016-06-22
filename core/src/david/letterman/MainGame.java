package david.letterman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainGame extends ApplicationAdapter {

	SpriteBatch batch;
	//texture of our man
	private Texture manText;
	//music for test purposes
	private Music testMusic;
	//rectangle object for our man texture, may have to change in future
	private Rectangle man;
	//Orthographic camera
	private OrthographicCamera camera;

	//Dave
	Stage stage;
	ImageButton button;
	Skin skin;
	TextureAtlas buttonAtlas;

	ImageButton.ImageButtonStyle imageButtonStyle;

	@Override
	public void create () {
//		//set texture for man
		manText = new Texture(Gdx.files.internal("Letterman.png"));

		//set music
		//testMusic = Gdx.audio.newMusic("stuff.mp3");
		//starts playing music on loop
		//testMusic.setLooping(true);
		//testMusic.play();
		//set camera
		camera = new OrthographicCamera();
		//800x480 is the resolution of SOME android devices
		//subject to change if we implement resolution scaling
		camera.setToOrtho(false, 800, 480);

		//set man, he is centered as of right now and his height and width
		//are arbitrary and subject to change
		man = new Rectangle();
		man.x = 400;
		man.y = 240;
		//changes based on png
		man.width = 64;
		man.height = 64;

		batch = new SpriteBatch();


		stage = new Stage();
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
		skin.addRegions(buttonAtlas);
		imageButtonStyle = new ImageButton.ImageButtonStyle();
		imageButtonStyle.up = skin.getDrawable("right up large");
		imageButtonStyle.down = skin.getDrawable("down right large");
		button = new ImageButton(imageButtonStyle);
		button.setPosition(200, 100); //** Button location **//
		button.setHeight(181); //** Button Height **//
		button.setWidth(640); //** Button Width **//
		stage.addActor(button);
		button.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
				return true;
			}

			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("my app", "Released");
			}
		});
	}

	@Override
	/*
	 *  Method called by the game loop from the application every time rendering should be performed.
	 *  Game logic updates are usually also performed in this method.
	 */
	public void render () {
		super.render();

//		//initializes white background
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		//updates camera
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(manText, man.x, man.y);
		batch.end();
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			man.x = touchPos.x;
			man.y = touchPos.y;
		}

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
		stage.dispose();
		manText.dispose();
		batch.dispose();
	}

}
