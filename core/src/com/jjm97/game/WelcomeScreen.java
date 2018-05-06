package com.jjm97.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by MLLJET002 on 9/12/17.
 */

public class WelcomeScreen implements Screen {
    Texture img = new Texture("HomeScreen.png");
    SpriteBatch batch = new SpriteBatch();

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1); // sets the colour which the buffer clears to, pick something nicer than bright red
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//clears the visual buffer for each render
        batch.begin();
        batch.draw(img, 80, 400);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
