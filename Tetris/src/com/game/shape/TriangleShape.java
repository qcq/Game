package com.game.shape;

import java.awt.Point;
import java.util.List;

import com.game.state.TriangleShapeEnum;

public class TriangleShape extends Shape {
    private TriangleShapeEnum state;

    public TriangleShape(List<Point> data, int row, int column) {
        super(data, row, column);
        state = TriangleShapeEnum.TOP;
    }

    @Override
    public boolean changeShape() throws Exception {
        switch (state) {
        case TOP:
            break;
        case RIGHT:
        case DOWN:
        case LEFT:
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
