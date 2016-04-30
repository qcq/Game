package com.game.shape;

import java.util.List;

import com.game.util.Cell;

public abstract class Shape {
    protected List<Cell> data;
    private int limitLeft;
    private int limitRight;
    private int limitBelow;
    private int limitTop;
    private int row;
    private int column;
    private int centerOfShape;

    public Shape(List<Cell> data, int row, int column) {
        this.data = data;
        this.row = row;
        this.column = column;
        initialLimits();
        getLimits();
    }

    public void initialLimits() {
        limitLeft = column;
        limitRight = -1;
        limitBelow = -1;
        limitTop = row;
    }

    public void getLimits() {
        int sum = 0;
        for (Cell item : data) {
            limitLeft = Math.min(limitLeft, item.y);
            limitRight = Math.max(limitRight, item.y);
            limitTop = Math.min(limitTop, item.x);
            limitBelow = Math.max(limitBelow, item.x);
            sum += item.y;
        }
        centerOfShape = sum / data.size();
    }

    public boolean ShiftLeft() {
        if (0 == limitLeft) {
            return false;
        }
        limitLeft--;
        limitRight--;
        for (Cell item : data) {
            item.y = item.y - 1;
        }
        return true;
    }

    public boolean ShiftRight() {
        if (column - 1 == limitRight) {
            return false;
        }
        limitLeft++;
        limitRight++;
        for (Cell item : data) {
            item.y = item.y + 1;
        }
        return true;
    }

    public boolean ShiftDown() {
        if (row - 1 == limitBelow) {
            return false;
        }
        limitTop++;
        limitBelow++;
        for (Cell item : data) {
            item.x = item.x + 1;
        }
        return true;
    }

    public boolean ShiftUp() {
        limitTop--;
        limitBelow--;
        for (Cell item : data) {
            item.x = item.x - 1;
        }
        return true;
    }

    public List<Cell> getData() {
        return data;
    }

    public void setData(List<Cell> data) {
        this.data = data;
    }

    public int getCenterOfShape() {
        return centerOfShape;
    }

    abstract public boolean changeShape() throws Exception;

    abstract public boolean unChangeShape() throws Exception;
}
