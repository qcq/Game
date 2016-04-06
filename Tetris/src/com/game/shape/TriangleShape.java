package com.game.shape;

import java.awt.Point;
import java.util.List;

import com.game.state.ShapeInterface.TRIANGLE;

public class TriangleShape extends Shape {
    private TRIANGLE state;

    public TriangleShape(List<Point> data, int row, int column) {
        super(data, row, column);
        state = TRIANGLE.TOP;
    }

    @Override
    public boolean changeShape() throws Exception {
        switch (state) {
        case TOP:
            state = TRIANGLE.RIGHT;
            break;
        case RIGHT:
            state = TRIANGLE.DOWN;
            break;
        case DOWN:
            state = TRIANGLE.LEFT;
            break;
        case LEFT:
            state = TRIANGLE.TOP;
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
        return false;
    }

}
