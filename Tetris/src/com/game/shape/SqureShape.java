package com.game.shape;

import java.util.List;

import com.game.util.Point;

public class SqureShape extends Shape {

    /*
     * The shape is below: ** **
     */
    public SqureShape(List<Point> data, int row, int column) {
        super(data, row, column);
    }

    @Override
    public boolean changeShape() {
        return true;
    }

    @Override
    public boolean unChangeShape() {
        return true;
    }

}
