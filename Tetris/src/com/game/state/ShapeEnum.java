package com.game.state;

public enum ShapeEnum {
    SQURE(ShapeInterface.SQURE.class), LONG(ShapeInterface.LONG.class), TRIANGLE(ShapeInterface.TRIANGLE.class), SEVEN(
            ShapeInterface.SEVEN.class), REVERSESEVEN(ShapeInterface.REVERSESEVEN.class), CHAIR(
                    ShapeInterface.CHAIR.class), REVERSECHAIR(ShapeInterface.REVERSECHAIR.class);
    ShapeInterface[] values;

    ShapeEnum(Class<? extends ShapeInterface> kind) {
        values = kind.getEnumConstants();
    }
}
