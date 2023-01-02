package com.doomlike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.tools.javac.comp.Env;

public class Main extends ApplicationAdapter {
	private CameraInputController cameraController;
	private PerspectiveCamera camera;
	private ModelBatch modelBatch;
	private ModelBuilder modelBuilder;
	private Environment environment;

	private Model model;
	private ModelInstance instance;


	@Override
	public void create () {
		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(10f,10f,10f);
		camera.lookAt(0,0,0);
		camera.near = 1f;
		camera.far = 300f;
		camera.update();

		cameraController = new CameraInputController(camera);
		Gdx.input.setInputProcessor(cameraController);

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		modelBatch = new ModelBatch();

		modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(5f,5f,5f,
					new Material(ColorAttribute.createDiffuse(Color.GREEN)),
				VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

		instance = new ModelInstance(model);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		cameraController.update();

		modelBatch.begin(camera);
		modelBatch.render(instance, environment);
		modelBatch.end();
	}
	
	@Override
	public void dispose () {
		model.dispose();
		modelBatch.dispose();
	}
}
