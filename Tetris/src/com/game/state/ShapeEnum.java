package com.game.state;

public enum ShapeEnum {
    SQURE(ShapeInterface.SQURE.class), LONG(ShapeInterface.LONG.class), TRIANGLE(
            ShapeInterface.TRIANGLE.class);
    ShapeInterface[] values;

    ShapeEnum(Class<? extends ShapeInterface> kind) {
        values = kind.getEnumConstants();
    }
}
