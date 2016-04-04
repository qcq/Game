package com.game.util;

import java.awt.Point;
import java.util.function.Predicate;

public class SameRow implements Predicate<Point> {
    /*
     * This class used for to check whether the Point in same row. predict
     * function.
     */
    private int row;

    public SameRow() {
        this(10);
    }

    public SameRow(int row) {
        this.row = row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public boolean test(Point t) {
        return t.x == row;
    }
}