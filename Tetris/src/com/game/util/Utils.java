package com.game.util;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.game.shape.LongShape;
import com.game.shape.Shape;
import com.game.shape.ShapeEnum;
import com.game.shape.ShapesData;
import com.game.shape.Squre;

public class Utils {

    public static Shape makeShape(ShapeEnum shape, int row, int column) {
        switch (shape) {
        case SQURE:
            return new Squre(ShapesData.makeSqureData(), row, column);
        case LONG:
            return new LongShape(ShapesData.makeLongData(), row, column);
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
        default:
            return ShapeEnum.SQURE;
        }
    }

}
