package com.mygdx.game;




import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Helpers.GameObject;
import com.mygdx.game.Helpers.Player;
import com.mygdx.game.Screens.Game1;


public class MyGame extends Game {
//	public PerspectiveCamera cam;

	public ModelBatch modelBatch;
	public AssetManager assets;
	public Array<GameObject> instances = new Array<GameObject>();
	public Environment environment;
	public boolean loading;
	public ModelInstance ship;
	public ModelInstance space;
public SpriteBatch batch;
public Texture cicle,styc;
public Player player;

	@Override
	public void create () {
///////////////////////
		modelBatch = new ModelBatch();
		batch=new SpriteBatch();
		cicle=new Texture("interfeys/Cicle.png");
		styc=new Texture("interfeys/Stik.png");
/////////////////////



		/////////////////////////
		environment = new Environment();


		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));







		assets = new AssetManager();
		assets.load("data/ship2.g3db", Model.class);

		assets.load("data/untitled.g3db", Model.class);
		loading = true;

		setScreen(new Game1(this));
	}

	public void doneLoading() {
		Model model=assets.get("data/ship2.g3db", Model.class);
//		ship = new ModelInstance(assets.get("data/ship2.g3db", Model.class));
//		ship.transform.setToRotation(Vector3.Y, -90).trn(0, 0, 0);
//		instances.add(ship);
		instances.add(new GameObject(model,model.nodes.get(0).id,true));

		player=new Player(instances.get(0));


		space = new ModelInstance(assets.get("data/untitled.g3db", Model.class));
		space.transform.scl(100);
		loading = false;

	}

	@Override
	public void dispose () {
		modelBatch.dispose();
		instances.clear();
		assets.dispose();
	}

	@Override
	public void resume () {
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void pause () {
	}
}