package com.game.util;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ShapesData {
    public static List<Point> makeSqureData() {
        List<Point> data = new ArrayList<Point>();
        data.add(new Point(-2, 0));
        data.add(new Point(-2, 1));
        data.add(new Point(-1, 0));
        data.add(new Point(-1, 1));
        return data;
    }

    public static List<Point> makeLongData() {
        List<Point> data = new ArrayList<Point>();
        data.add(new Point(-1, 0));
        data.add(new Point(-1, 1));
        data.add(new Point(-1, 2));
        data.add(new Point(-1, 3));
        return data;
    }
}
