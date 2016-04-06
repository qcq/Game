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
        Point point = data.get(2);
        switch (state) {
        case LEFT:
            data.get(0).setLocation(point.x - 1, point.y + 1);
            data.get(1).setLocation(point.x, point.y + 1);
            data.get(3).setLocation(point.x, point.y - 1);
            state = SEVEN.TOP;
            break;
        case TOP:
            data.get(0).setLocation(point.x + 1, point.y + 1);
            data.get(1).setLocation(point.x + 1, point.y);
            data.get(3).setLocation(point.x - 1, point.y);
            state = SEVEN.RIGHT;
            break;
        case RIGHT:
            data.get(0).setLocation(point.x + 1, point.y - 1);
            data.get(1).setLocation(point.x, point.y - 1);
            data.get(3).setLocation(point.x, point.y + 1);
            state = SEVEN.DOWN;
            break;
        case DOWN:
            data.get(0).setLocation(point.x - 1, point.y - 1);
            data.get(1).setLocation(point.x - 1, point.y);
            data.get(3).setLocation(point.x + 1, point.y);
            state = SEVEN.LEFT;
            break;
        default:
            throw new Exception("sth wrong here SevenShape");
        }
        initialLimits();
        getLimits();
        return true;
    }

    @Override
    public boolean unChangeShape() throws Exception {
        Point point = data.get(2);
        switch (state) {
        case LEFT:
            data.get(0).setLocation(point.x + 1, point.y - 1);
            data.get(1).setLocation(point.x, point.y - 1);
            data.get(3).setLocation(point.x, point.y + 1);
            state = SEVEN.DOWN;
            break;
        case TOP:
            data.get(0).setLocation(point.x - 1, point.y - 1);
            data.get(1).setLocation(point.x - 1, point.y);
            data.get(3).setLocation(point.x + 1, point.y);
            state = SEVEN.LEFT;
            break;
        case RIGHT:
            data.get(0).setLocation(point.x - 1, point.y + 1);
            data.get(1).setLocation(point.x, point.y + 1);
            data.get(3).setLocation(point.x, point.y - 1);
            state = SEVEN.TOP;
            break;
        case DOWN:
            data.get(0).setLocation(point.x + 1, point.y + 1);
            data.get(1).setLocation(point.x + 1, point.y);
            data.get(3).setLocation(point.x - 1, point.y);
            state = SEVEN.RIGHT;
            break;
        default:
            throw new Exception("sth wrong here SevenShape");
        }
        initialLimits();
        getLimits();
        return true;
    }

}
