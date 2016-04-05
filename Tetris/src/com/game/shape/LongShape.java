package com.game.shape;

import java.awt.Point;
import java.util.List;

public class LongShape extends Shape {

    public LongShape(List<Point> data, int row, int column) {
        super(data, row, column);
    }

    @Override
    public boolean changeShape() {
        Point point = data.get(2);
        data.get(0).setLocation(data.get(0).x - 2, point.y);
        data.get(1).setLocation(data.get(1).x - 1, point.y);
        data.get(3).setLocation(data.get(3).x + 1, point.y);
        return true;
    }

    @Override
    public boolean unChangeShape() {
        return false;
    }

}
