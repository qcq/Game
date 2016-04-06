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

    enum SEVEN implements ShapeInterface {
        LEFT, TOP, RIGHT, DOWN
    }

    enum REVERSESEVEN implements ShapeInterface {
        RIGHT, DOWN, LEFT, TOP
    }

    enum CHAIR implements ShapeInterface {
        HORIZONTAL, VERTICAL
    }

    enum REVERSECHAIR implements ShapeInterface {
        HORIZONTAL, VERTICAL
    }
}
