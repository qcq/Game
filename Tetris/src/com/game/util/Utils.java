package com.game.util;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.game.shape.LongShape;
import com.game.shape.Shape;
import com.game.shape.SqureShape;
import com.game.shape.TriangleShape;
import com.game.state.ShapeEnum;

public class Utils {

    public static Shape makeShape(ShapeEnum shape, int row, int column) {
        switch (shape) {
        case SQURE:
            return new SqureShape(ShapesData.makeShapeData(shape), row, column);
        case LONG:
            return new LongShape(ShapesData.makeShapeData(shape), row, column);
        case TRIANGLE:
            return new TriangleShape(ShapesData.makeShapeData(shape), row,
                    column);
        default:
            return null;
        }
    }

    public static boolean hasSamePoint(List<Point> data) {
        Set<Point> set = new HashSet<Point>();
        set.addAll(data);
        return set.size() != data.size();
    }

    public static ShapeEnum conertRandToEnum(int rand) {
        switch (rand) {
        case 0:
            return ShapeEnum.SQURE;
        case 1:
            return ShapeEnum.LONG;
        case 2:
            return ShapeEnum.TRIANGLE;
        default:
            return ShapeEnum.SQURE;
        }
    }

}
