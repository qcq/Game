package com.game.util;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.game.state.ShapeEnum;

public class ShapesData {
    private static List<Point> data;

    public static List<Point> makeShapeData(ShapeEnum shape) {
        switch (shape) {
        case SQURE:
            data = new ArrayList<Point>();
            data.add(new Point(-2, 0));
            data.add(new Point(-2, 1));
            data.add(new Point(-1, 0));
            data.add(new Point(-1, 1));
            break;
        case LONG:
            data = new ArrayList<Point>();
            data.add(new Point(-1, 0));
            data.add(new Point(-1, 1));
            data.add(new Point(-1, 2));
            data.add(new Point(-1, 3));
            break;
        case TRIANGLE:
            data = new ArrayList<Point>();
            data.add(new Point(-2, 1));
            data.add(new Point(-1, 0));
            data.add(new Point(-1, 1));
            data.add(new Point(-1, 2));
            break;
        case SEVEN:
            data = new ArrayList<Point>();
            data.add(new Point(-3, 0));
            data.add(new Point(-3, 1));
            data.add(new Point(-2, 1));
            data.add(new Point(-1, 1));
            break;
        case REVERSESEVEN:
            data = new ArrayList<Point>();
            data.add(new Point(-3, 0));
            data.add(new Point(-3, 1));
            data.add(new Point(-2, 0));
            data.add(new Point(-1, 0));
            break;
        default:
            return null;
        }
        return data;
    }
}
