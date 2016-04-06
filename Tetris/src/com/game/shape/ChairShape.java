package com.game.shape;

import java.awt.Point;
import java.util.List;

import com.game.state.ShapeInterface.CHAIR;

public class ChairShape extends Shape {
    private CHAIR state;

    public ChairShape(List<Point> data, int row, int column) {
        super(data, row, column);
        state = CHAIR.HORIZONTAL;
    }

    @Override
    public boolean changeShape() throws Exception {
        Point point = data.get(2);
        switch (state) {
        case HORIZONTAL:
            data.get(0).setLocation(point.x - 1, point.y + 1);
            data.get(1).setLocation(point.x - 1, point.y);
            data.get(3).setLocation(point.x, point.y - 1);
            state = CHAIR.VERTICAL;
            break;
        case VERTICAL:
            data.get(0).setLocation(point.x - 1, point.y - 1);
            data.get(1).setLocation(point.x, point.y - 1);
            data.get(3).setLocation(point.x + 1, point.y);
            state = CHAIR.HORIZONTAL;
            break;
        default:
            throw new Exception("sth wrong here : TriangleShape");
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