package com.game.shape;

import java.util.List;

import com.game.Teris;
import com.game.state.ShapeInterface.REVERSECHAIR;
import com.game.util.Cell;

public class ReverseChairShpae extends Shape {
    private REVERSECHAIR state;

    public ReverseChairShpae(List<Cell> data, int row, int column) {
        super(data, row, column);
        state = REVERSECHAIR.HORIZONTAL;
        Teris.logger.info("ReverseChairShpae created with " + state);
    }

    @Override
    public boolean changeShape() throws Exception {
        Cell point = data.get(2);
        switch (state) {
        case HORIZONTAL:
            data.get(0).setLocation(point.x, point.y + 1);
            data.get(1).setLocation(point.x - 1, point.y);
            data.get(3).setLocation(point.x - 1, point.y - 1);
            state = REVERSECHAIR.VERTICAL;
            break;
        case VERTICAL:
            data.get(0).setLocation(point.x - 1, point.y);
            data.get(1).setLocation(point.x, point.y - 1);
            data.get(3).setLocation(point.x + 1, point.y - 1);
            state = REVERSECHAIR.HORIZONTAL;
            break;
        default:
            throw new Exception("sth wrong here : ReverseChairShpae");
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
