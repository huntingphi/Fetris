package com.jjm97.game;

import java.util.Arrays;
import java.util.Random;
import java.lang.Math;


/**
 * Created by MLLJET002 on 9/11/17.
 */
//Generate at row 18 col 5
public class Shape {
    enum Tetrominoes {
        Noshape, Zshape, Sshape, IShape, Tshape, Oshape, Lshape, MirroredLShape
    }

    int[] position;
    private Tetrominoes pieceShape;
    int[][] grid;
    private int[][] coordinates;
    private int[][][] coordinatesTable;
    int marker;

    public void setGrid(int[][] grid) {
        this.grid = Arrays.copyOf(grid, grid.length);
    }

    public Shape() {
        coordinates = new int[4][2];
        setShape(Tetrominoes.Noshape);

    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void setShape(Tetrominoes pieceShape) {
        coordinatesTable = new int[][][]{
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}},//0 No shape
                {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},//1 ZShape
                {{0, -1}, {0, 0}, {1, 0}, {1, 1}},//2 SShape
                {{0, -1}, {0, 0}, {0, 1}, {0, 2}},//3 IShape
                {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},//4 TShape
                {{0, 0}, {1, 0}, {0, 1}, {1, 1}},//5 OShape
                {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},//6 LShape
                {{1, -1}, {0, -1}, {0, 0}, {0, 1}}//6 MirroredLShape
        };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; ++j) {
                coordinates[i][j] = coordinatesTable[pieceShape.ordinal()][i][j];
            }
            this.pieceShape = pieceShape;
        }
    }

    public void setX(int blockNo, int x) {
        coordinates[blockNo][0] = x;

    }

    public void setY(int blockNo, int y) {
        coordinates[blockNo][1] = y;

    }

    public int getX(int blockNo) {
        return coordinates[blockNo][0];
    }

    public int getY(int blockNo) {
        return coordinates[blockNo][1];
    }

    public Tetrominoes getShape() {

        return pieceShape;
    }

    public void setRandomShape() {
        Random r = new Random();
        int x = 7;
        while (x == 7) {

            x = Math.abs(r.nextInt()) % 7 + 1;
        }
        Tetrominoes[] values = Tetrominoes.values();
        setShape(values[x]);
    }

    public Shape rotateRight()//rotate by flipping about the y=-x axis
    {
        if (pieceShape == Tetrominoes.Oshape)
            return this;

        Shape result = new Shape();
        result.pieceShape = pieceShape;
        for (int i = 0; i < 4; ++i) {

            result.setX(i, getY(i));//+(18+getY(i)));
            result.setY(i, -getX(i));//+(5-getX(i)));
        }
        return result;
    }

    void addPiece(int[] position) {//adds a piece to the grid
        int xPos = position[0];
        int yPos = position[1];
        marker = 0;
        switch (pieceShape.ordinal()) {
            case 1:
                marker = 21;
                break;
            case 2:
                marker = 22;
                break;
            case 3:
                marker = 23;
                break;
            case 4:
                marker = 24;
                break;
            case 5:
                marker = 25;
                break;
            case 6:
                marker = 26;

        }
        for (int i = 0; i < 4; i++) {
            grid[getY(i) + yPos][getX(i) + xPos] = marker;
        }

    }

    void addPiece() {
        marker = 0;
        switch (pieceShape.ordinal()) {
            case 1:
                marker = 21;
                break;
            case 2:
                marker = 22;
                break;
            case 3:
                marker = 23;
                break;
            case 4:
                marker = 24;
                break;
            case 5:
                marker = 25;
                break;
            case 6:
                marker = 26;

        }
        for (int i = 0; i < 4; i++) {
            grid[getY(i) + 18][getX(i) + 5] = marker;
        }

    }

    int[][] removePiece(int[] position) {//removes a piece from the grid
        int xPos = position[0];
        int yPos = position[1];
        marker = 0;
        for (int i = 0; i < 4; i++) {
            grid[getY(i) + yPos][getX(i) + xPos] = marker;
        }

        return grid;
    }


    boolean canRotate() {//checks if the piece can rotate

        for (int i = 0; i < 4; i++) {
            marker = getShape().ordinal() + 20;
            if (grid[getY(i) + position[1]][getX(i) + position[0]] != marker && grid[getY(i) + position[1]][getX(i) + position[0]] != 0) {

                return false;
            }
        }
        return true;
    }


    boolean canMove(int dir) {//checks if the piece can move in the given direction, dir is an int indicating which
        //direction to move in.

        try {
            switch (dir) {
                case 0://move down
                    for (int i = 0; i < 4; i++) {
                        int nextblock = grid[getY(i) + position[1] - 1][getX(i) + position[0]];
                        if (nextblock != 0 && nextblock != marker) {
                            return false;
                        }
                    }
                    break;
                case 1: //move right
                    for (int i = 0; i < 4; i++) {
                        int nextblock = grid[getY(i) + position[1]][getX(i) + position[0] + 1];
                        if (nextblock != 0 && nextblock != marker) {
                            return false;
                        }
                    }
                    break;
                case 2://move left
                    for (int i = 0; i < 4; i++) {
                        int nextblock = grid[getY(i) + position[1]][getX(i) + position[0] - 1];
                        if (nextblock != 0 && nextblock != marker) {
                            return false;
                        }
                    }
                    break;
                case 3://move up
                    for (int i = 0; i < 4; i++) {
                        int nextblock = grid[getY(i) + position[1] + 1][getX(i) + position[0]];
                        if (nextblock != 0 && nextblock != marker) {
                            return false;
                        }
                    }
                    break;


            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}

