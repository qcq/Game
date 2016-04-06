package com.game.shape;

import java.awt.Point;
import java.util.List;

import com.game.state.ShapeInterface.REVERSESEVEN;

public class ReverseSevenShape extends Shape {
    private REVERSESEVEN state;

    public ReverseSevenShape(List<Point> data, int row, int column) {
        super(data, row, column);
        state = REVERSESEVEN.LEFT;
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
