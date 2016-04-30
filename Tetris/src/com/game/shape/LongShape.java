package com.game.shape;

import java.util.List;

import com.game.Teris;
import com.game.state.ShapeInterface.LONG;
import com.game.util.Point;

public class LongShape extends Shape {
    private LONG state;

    public LongShape(List<Point> data, int row, int column) {
        super(data, row, column);
        state = LONG.HORIZONTAL;
        Teris.logger.info("LongShape created with " + state);
    }

    @Override
    public boolean changeShape() throws Exception {
        Point point = data.get(2);
        switch (state) {
        case HORIZONTAL:
            data.get(0).setLocation(point.x - 2, point.y);
            data.get(1).setLocation(point.x - 1, point.y);
            data.get(3).setLocation(point.x + 1, point.y);
            state = LONG.VERTICAL;
            break;
        case VERTICAL:
            data.get(0).setLocation(point.x, point.y - 2);
            data.get(1).setLocation(point.x, point.y - 1);
            data.get(3).setLocation(point.x, point.y + 1);
            state = LONG.HORIZONTAL;
            break;
        default:
            throw new Exception("sth wrong here LongShape");
        }
        initialLimits();
        getLimits();
        Teris.logger.info("ChairShape chaged to with " + state);
        return true;
    }

    @Override
    public boolean unChangeShape() throws Exception {
        try {
            changeShape();
            Teris.logger.info("ChairShape chaged backed to with " + state);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
