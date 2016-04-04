package com.game.shape;

import java.awt.Point;
import java.util.List;

public class Squre extends Shape {

    /*
     * The shape is below: ** **
     */
    public Squre(List<Point> data, int row, int column) {
        super(data, row, column);
    }

    @Override
    public boolean changeShape() {
        return true;
    }

}
