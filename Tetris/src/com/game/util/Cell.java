package com.game.util;

import java.awt.Color;

public class Cell extends java.awt.Point {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public Color color;

    public Cell(int x, int y) {
        super(x, y);
        color = Color.green;
    }

    public Cell(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }
}
