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
        Point point = data.get(2);
        switch (state) {
        case RIGHT:
            data.get(0).setLocation(point.x, point.y + 1);
            data.get(1).setLocation(point.x + 1, point.y + 1);
            data.get(3).setLocation(point.x, point.y - 1);
            state = REVERSESEVEN.DOWN;
            break;
        case DOWN:
            data.get(0).setLocation(point.x + 1, point.y);
            data.get(1).setLocation(point.x + 1, point.y - 1);
            data.get(3).setLocation(point.x - 1, point.y);
            state = REVERSESEVEN.LEFT;
            break;
        case LEFT:
            data.get(0).setLocation(point.x, point.y - 1);
            data.get(1).setLocation(point.x - 1, point.y - 1);
            data.get(3).setLocation(point.x, point.y + 1);
            state = REVERSESEVEN.TOP;
            break;
        case TOP:
            data.get(0).setLocation(point.x - 1, point.y);
            data.get(1).setLocation(point.x - 1, point.y + 1);
            data.get(3).setLocation(point.x + 1, point.y);
            state = REVERSESEVEN.RIGHT;
            break;
        default:
            throw new Exception("sth wrong here ReverseSevenShape");
        }
        initialLimits();
        getLimits();
        return true;
    }

    @Override
    public boolean unChangeShape() throws Exception {
        Point point = data.get(2);
        switch (state) {
        case RIGHT:
            data.get(0).setLocation(point.x, point.y - 1);
            data.get(1).setLocation(point.x - 1, point.y - 1);
            data.get(3).setLocation(point.x, point.y + 1);
            state = REVERSESEVEN.TOP;
            break;
        case DOWN:
            data.get(0).setLocation(point.x - 1, point.y);
            data.get(1).setLocation(point.x - 1, point.y + 1);
            data.get(3).setLocation(point.x + 1, point.y);
            state = REVERSESEVEN.RIGHT;
            break;
        case LEFT:
            data.get(0).setLocation(point.x, point.y + 1);
            data.get(1).setLocation(point.x + 1, point.y + 1);
            data.get(3).setLocation(point.x, point.y - 1);
            state = REVERSESEVEN.DOWN;
            break;
        case TOP:
            data.get(0).setLocation(point.x + 1, point.y);
            data.get(1).setLocation(point.x + 1, point.y - 1);
            data.get(3).setLocation(point.x - 1, point.y);
            state = REVERSESEVEN.LEFT;
            break;
        default:
            throw new Exception("sth wrong here ReverseSevenShape");
        }
        initialLimits();
        getLimits();
        return true;
    }

}
