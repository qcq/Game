package com.game.util;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.game.shape.LongShape;
import com.game.shape.ReverseSevenShape;
import com.game.shape.SevenShape;
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
        case SEVEN:
            return new SevenShape(ShapesData.makeShapeData(shape), row, column);
        case REVERSESEVEN:
            return new ReverseSevenShape(ShapesData.makeShapeData(shape), row,
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
}
