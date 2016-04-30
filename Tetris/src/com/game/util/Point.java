package com.game.util;

import java.awt.Color;

public class Point extends java.awt.Point {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public Color color;

    public Point(int x, int y) {
        super(x, y);
        color = Color.green;
    }

    public Point(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }
}
