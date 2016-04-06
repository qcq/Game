package com.game.shape;

import java.awt.Point;
import java.util.List;

import com.game.state.ShapeInterface.SEVEN;

public class SevenShape extends Shape {
    private SEVEN state;

    public SevenShape(List<Point> data, int row, int column) {
        super(data, row, column);
        state = SEVEN.LEFT;
    }

    @Override
    public boolean changeShape() throws Exception {
        return false;
    }

    @Override
    public boolean unChangeShape() throws Exception {
        return false;
    }

}
