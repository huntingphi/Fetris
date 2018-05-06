package com.jjm97.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

import javax.swing.JOptionPane;


public class Tetris extends Game {
    SpriteBatch batch;
    Texture img;
    Sprite sprite;
    Music sound;
    boolean playSound=true;

    void toggleSound(){
        playSound = !playSound;
    }

    @Override
    public void create() {
        this.setScreen(new WelcomeScreen());
        sound = Gdx.audio.newMusic(Gdx.files.internal("Twister Tetris.mp3"));
        sound.setLooping(true);
    }

    @Override
    public void render() {
        super.render();

        sound.play();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            this.setScreen(new PlayScreen(this));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            toggleSound();
            if(playSound==true){
//                sound.play();
                sound.setVolume(1);

            }else{

                sound.setVolume(0);//stop();
            }
        }
            if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            JOptionPane.showMessageDialog(null,
                    "Move Left - Left Arrow" +
                            "\nMove Right - Right Arrow" +
                            "\n Move Down - Down Arrow" +
                            "\n Drop Down - Spacebar" +
                            "\n Rotate - Right Shift" +
                            "\nMove Up - Up Arrow (Bonus Feature)" +
                            "\n Toggle Move Up - U" +
                            "\n Reset game - Enter" +
                            "\n Toggle music - M" +
                            "\n View this again - C");
        }
    }

    @Override
    public void dispose() {
    }
}
