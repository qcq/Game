package com.game.state;

public interface ShapeInterface {
    enum SQURE implements ShapeInterface {
        SQURE
    }

    enum LONG implements ShapeInterface {
        HORIZONTAL, VERTICAL
    }

    enum TRIANGLE implements ShapeInterface {
        TOP, RIGHT, DOWN, LEFT
    }
}
