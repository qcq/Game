package com.game.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.game.state.ShapeEnum;

public class ShapesData {
    private static List<Point> data;

    public static List<Point> makeShapeData(ShapeEnum shape) {
        switch (shape) {
        case SQURE:
            data = new ArrayList<Point>();
            data.add(new Point(-2, 0, Color.black));
            data.add(new Point(-2, 1, Color.cyan));
            data.add(new Point(-1, 0, Color.green));
            data.add(new Point(-1, 1, Color.pink));
            break;
        case LONG:
            data = new ArrayList<Point>();
            data.add(new Point(-1, 0, Color.black));
            data.add(new Point(-1, 1, Color.cyan));
            data.add(new Point(-1, 2, Color.green));
            data.add(new Point(-1, 3, Color.pink));
            break;
        case TRIANGLE:
            data = new ArrayList<Point>();
            data.add(new Point(-2, 1, Color.black));
            data.add(new Point(-1, 0, Color.cyan));
            data.add(new Point(-1, 1, Color.green));
            data.add(new Point(-1, 2, Color.pink));
            break;
        case SEVEN:
            data = new ArrayList<Point>();
            data.add(new Point(-3, 0, Color.black));
            data.add(new Point(-3, 1, Color.cyan));
            data.add(new Point(-2, 1, Color.green));
            data.add(new Point(-1, 1, Color.pink));
            break;
        case REVERSESEVEN:
            data = new ArrayList<Point>();
            data.add(new Point(-3, 0, Color.black));
            data.add(new Point(-3, 1, Color.cyan));
            data.add(new Point(-2, 0, Color.green));
            data.add(new Point(-1, 0, Color.pink));
            break;
        case CHAIR:
            data = new ArrayList<Point>();
            data.add(new Point(-3, 0, Color.black));
            data.add(new Point(-2, 0, Color.cyan));
            data.add(new Point(-2, 1, Color.green));
            data.add(new Point(-1, 1, Color.pink));
            break;
        case REVERSECHAIR:
            data = new ArrayList<Point>();
            data.add(new Point(-3, 1, Color.black));
            data.add(new Point(-2, 0, Color.cyan));
            data.add(new Point(-2, 1, Color.green));
            data.add(new Point(-1, 0, Color.pink));
            break;
        default:
            return null;
        }
        return data;
    }
}
