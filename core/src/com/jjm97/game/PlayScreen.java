package com.jjm97.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;

import javax.swing.JOptionPane;

/**
 * Created by MLLJET002 on 9/8/17.
 */

public class PlayScreen implements Screen {
    boolean up = false;
    private int score;
    private String yourScoreName;
    BitmapFont yourBitmapFontName;
    Game game;
    boolean render = true;
    boolean rotated = true;
    int unitsDropped = 0;
    int unitsShifted = 0;
    float t0 = System.nanoTime();
    int HEIGHT = 22;
    int WIDTH = 12;
    int[] position = {5, 18};
    Shape currentBlock;
    SpriteBatch batch;
    Texture block, border,
            zBlock,//Block 21
            sBlock,//Block 22
            iBlock,//Block 23
            tBlock,//Block 24
            oBlock,//Block 25
            lBlock,//Block 26
            lBlockMir;//Block 27

    boolean firstRun = true;
    boolean isFalling = false;


    int grid[][] = new int[HEIGHT][WIDTH];

    public PlayScreen(Game g) {
        game = g;
        batch = new SpriteBatch();
        block = new Texture("blockWhite.png");
        border = new Texture("blockDarkGray.png");
        zBlock = new Texture("blockPink.png");
        sBlock = new Texture("blockRed.png");
        tBlock = new Texture("blockGreen.png");
        iBlock = new Texture("blockPurple.png");
        lBlock = new Texture("blockBlue.png");
        lBlockMir = new Texture("blockLightBlue.png");
        oBlock = new Texture("blockOrange.png");
        grid = makeGrid(HEIGHT, WIDTH);
    }

    @Override
    public void show() {
        score = 0;
        yourScoreName = "score: 0";
        yourBitmapFontName = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) game.setScreen(new PlayScreen(game));
        if (render == true) {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            yourBitmapFontName.setColor(1.0f, 0.0f, 0.0f, 1.0f);
            yourBitmapFontName.draw(batch, yourScoreName, 20, 100);
            drawBG();
            batch.end();

            if (firstRun == true) {
                newBlock();
                firstRun = false;
            } else {
                if (Gdx.input.isKeyJustPressed(Input.Keys.U)) toggleUp();
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    while (currentBlock.canMove(0) == true) {
                        moveBlock(0);
                    }
                    commit();
                }
                isGameOver();//Checks if game is over

                if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                    moveBlock(0);
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                    moveBlock(1);
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                    moveBlock(2);
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                    moveBlock(3);
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_RIGHT) && rotated == false) {
                    rotateBlock();
                }


            }

            if ((System.nanoTime() - t0) / (1000) > 500000) {//Slows dropping of block
                moveBlock(0);

                t0 = System.nanoTime(); //T0 for the initial time


            }

            checkFullLines();//Checks for full lines to be cleared
        }
    }

    void isGameOver() {//Tests if game is over (a block is occupying the starting block) and displays a message if so
        if (grid[18][5] != 0 && grid[18][5] < 20) {
            render = false;
            JOptionPane.showMessageDialog(null, "Game over!");
        }
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

    void drawBG() {//Draws the grid and its corresponding images/blocks
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (x == 0 || x == WIDTH - 1 || y == 0 || y == HEIGHT - 1)
                    batch.draw(border, x * 32 + 100, y * 32);
                else if (grid[y][x] == 0) batch.draw(block, x * 32 + 100, y * 32);
                if (grid[y][x] == 21 || grid[y][x] == 11) {
                    batch.draw(zBlock, x * 32 + 100, y * 32);
                }
                if (grid[y][x] == 22 || grid[y][x] == 12) {
                    batch.draw(sBlock, x * 32 + 100, y * 32);
                }
                if (grid[y][x] == 23 || grid[y][x] == 13) {
                    batch.draw(iBlock, x * 32 + 100, y * 32);
                }
                if (grid[y][x] == 24 || grid[y][x] == 14) {
                    batch.draw(tBlock, x * 32 + 100, y * 32);
                }
                if (grid[y][x] == 25 || grid[y][x] == 15) {
                    batch.draw(oBlock, x * 32 + 100, y * 32);
                }
                if (grid[y][x] == 26 || grid[y][x] == 16) {
                    batch.draw(lBlock, x * 32 + 100, y * 32);
                }
                if (grid[y][x] == 27 || grid[y][x] == 17) {
                    batch.draw(lBlockMir, x * 32 + 100, y * 32);
                }
            }
        }
    }

    void newBlock() {//Spawns a new block in the spawning position (X = 5 and Y=18)
        if (grid[18][5] == 0) {
            position[0] = 5;
            position[1] = 18;
            Shape s = new Shape();
            s.setRandomShape();
            s.setGrid(grid);
            s.addPiece(position);
            currentBlock = s;

        }
    }

    void toggleUp() {//Toggles whether upwards movement is enabled
        up = !up;
    }

    void rotateBlock() {//Rotates the block if a rotation is possible, otherwise does nothing
        grid = currentBlock.removePiece(position);

        Shape temp = currentBlock.rotateRight();
        temp.setPosition(position);
        temp.setGrid(grid);

        if (temp.canRotate() == true) {
            currentBlock = temp;
            currentBlock.addPiece(position);


        } else {
            currentBlock.addPiece(position);

        }

    }


    void moveBlock(int direction) {//Moves block in one of 4 directions, if movement in that direction is possible.
        //Otherwise it does nothing
        currentBlock.setPosition(position);

        switch (direction) {
            case 0:
                if (currentBlock.canMove(0) == true) {//move down

                    grid = currentBlock.removePiece(position);
                    unitsDropped += 1;
                    position[1] -= 1;
                    currentBlock.addPiece(position);
                    rotated = false;
                } else {
                    commit();
                    isFalling = false;
                }
                break;
            case 1:
                if (currentBlock.canMove(1) == true) {//move right
                    grid = currentBlock.removePiece(position);
                    unitsShifted += 1;
                    position[0] += 1;
                    currentBlock.addPiece(position);
                }
                break;
            case 2:
                if (currentBlock.canMove(2) == true) {//move left
                    grid = currentBlock.removePiece(position);
                    unitsShifted -= 1;
                    position[0] -= 1;
                    currentBlock.addPiece(position);
                }
                break;
            case 3:
                if (currentBlock.canMove(3) && up == true) {
                    grid = currentBlock.removePiece(position);
                    unitsDropped -= 1;
                    position[1] += 1;
                    currentBlock.addPiece(position);
                }
                break;
        }
    }

    void commit() {//"Commit" new permanent blocks (Permanent blocks are marked in the grid by values that are 10+their block number -
        // eg if I-block number is 2, therefore permanent I block is marked in the array as 12, temporary (falling) blocks
        // are marked by 20+their block number)
        isFalling = false;
        score += 100;
        yourScoreName = "score: " + score;//update score
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > 20) {
                    grid[i][j] -= 10;
                }
            }
            currentBlock.setGrid(grid);
        }
        isFalling = false;
        newBlock();
    }

    void checkFullLines() {//Check if any lines are full and call the method to clear them if full lines are found
        for (int i = 1; i < grid.length - 1; i++) {
            int j = 1;
            for (; j < grid[i].length - 1; j++) {
                if (grid[i][j] == 0 || grid[i][j] > 20) {
                    break;
                }

                if (j == grid[i].length - 2) clearLine(i);

            }
        }
    }

    void clearLine(int row) {//Clear a given line (populate with zeros, ones at the boundries
        for (int i = row; i < grid.length - 1; i++) {
            grid[i] = grid[i + 1];

        }
        grid[grid.length - 1] = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
    }


    /**
     * Returns a 2d integer array with the specified number or rows and columns populated with 1's at the boundries and 0's elsewhere
     *
     * @param rows the number of rows
     * @param cols the number of columns
     * @return grid the populated array
     */
    // col = x;
// row = y;
    public int[][] makeGrid(int rows, int cols) {
        int[][] grid = new int[rows][cols];


        for (int i = 0; i < grid.length; i++) {
            int[] is = grid[i];
            for (int j = 0; j < is.length; j++) {
                if (j == 0 || j == is.length - 1 || i == 0 || i == grid.length - 1) is[j] = 1;
                else is[j] = 0;

            }
        }

        return grid;

    }
}

