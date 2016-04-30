package com.game.shape;

import java.util.List;

import com.game.Teris;
import com.game.state.ShapeInterface.TRIANGLE;
import com.game.util.Point;

public class TriangleShape extends Shape {
    private TRIANGLE state;

    public TriangleShape(List<Point> data, int row, int column) {
        super(data, row, column);
        state = TRIANGLE.TOP;
        Teris.logger.info("TriangleShape created with " + state);
    }

    @Override
    public boolean changeShape() throws Exception {
        Point point = data.get(2);
        switch (state) {
        case TOP:
            data.get(0).setLocation(point.x, point.y + 1);
            data.get(1).setLocation(point.x - 1, point.y);
            data.get(3).setLocation(point.x + 1, point.y);
            state = TRIANGLE.RIGHT;
            break;
        case RIGHT:
            data.get(0).setLocation(point.x + 1, point.y);
            data.get(1).setLocation(point.x, point.y + 1);
            data.get(3).setLocation(point.x, point.y - 1);
            state = TRIANGLE.DOWN;
            break;
        case DOWN:
            data.get(0).setLocation(point.x, point.y - 1);
            data.get(1).setLocation(point.x + 1, point.y);
            data.get(3).setLocation(point.x - 1, point.y);
            state = TRIANGLE.LEFT;
            break;
        case LEFT:
            data.get(0).setLocation(point.x - 1, point.y);
            data.get(1).setLocation(point.x, point.y - 1);
            data.get(3).setLocation(point.x, point.y + 1);
            state = TRIANGLE.TOP;
            break;
        default:
            throw new Exception("sth wrong here : TriangleShape");
        }
        initialLimits();
        getLimits();
        Teris.logger.info("ChairShape chaged to with " + state);
        return true;
    }

    @Override
    public boolean unChangeShape() throws Exception {
        Point point = data.get(2);
        switch (state) {
        case TOP:
            data.get(0).setLocation(point.x, point.y - 1);
            data.get(1).setLocation(point.x + 1, point.y);
            data.get(3).setLocation(point.x - 1, point.y);
            state = TRIANGLE.LEFT;
            break;
        case LEFT:
            data.get(0).setLocation(point.x + 1, point.y);
            data.get(1).setLocation(point.x, point.y + 1);
            data.get(3).setLocation(point.x, point.y - 1);
            state = TRIANGLE.DOWN;
            break;
        case DOWN:
            data.get(0).setLocation(point.x, point.y + 1);
            data.get(1).setLocation(point.x - 1, point.y);
            data.get(3).setLocation(point.x + 1, point.y);
            state = TRIANGLE.RIGHT;
            break;
        case RIGHT:
            data.get(0).setLocation(point.x - 1, point.y);
            data.get(1).setLocation(point.x, point.y - 1);
            data.get(3).setLocation(point.x, point.y + 1);
            state = TRIANGLE.TOP;
            break;
        default:
            throw new Exception("sth wrong here : TriangleShape");
        }
        initialLimits();
        getLimits();
        Teris.logger.info("ChairShape chaged backed to with " + state);
        return true;
    }

}
