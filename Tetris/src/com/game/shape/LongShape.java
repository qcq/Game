package com.game.shape;

import java.awt.Point;
import java.util.List;

import com.game.state.ShapeInterface.LONG;

public class LongShape extends Shape {
    private LONG state;

    public LongShape(List<Point> data, int row, int column) {
        super(data, row, column);
        state = LONG.HORIZONTAL;
    }

    @Override
    public boolean changeShape() throws Exception {
        Point point = data.get(2);
        switch (state) {
        case HORIZONTAL:
            data.get(0).setLocation(data.get(0).x - 2, point.y);
            data.get(1).setLocation(data.get(1).x - 1, point.y);
            data.get(3).setLocation(data.get(3).x + 1, point.y);
            state = LONG.VERTICAL;
            break;
        case VERTICAL:
            data.get(0).setLocation(point.x, data.get(0).y - 2);
            data.get(1).setLocation(point.x, data.get(1).y - 1);
            data.get(3).setLocation(point.x, data.get(3).y + 1);
            state = LONG.HORIZONTAL;
            break;
        default:
            throw new Exception("sth wrong here");
        }
        initialLimits();
        getLimits();
        return true;
    }

    @Override
    public boolean unChangeShape() throws Exception {
        try {
            changeShape();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
