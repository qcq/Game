package com.game.shape;

import java.util.List;

import com.game.Teris;
import com.game.state.ShapeInterface.REVERSESEVEN;
import com.game.util.Cell;

public class ReverseSevenShape extends Shape {
    private REVERSESEVEN state;

    public ReverseSevenShape(List<Cell> data, int row, int column) {
        super(data, row, column);
        state = REVERSESEVEN.RIGHT;
        Teris.logger.info("ReverseSevenShape created with " + state);
    }

    @Override
    public boolean changeShape() throws Exception {
        Cell point = data.get(2);
        switch (state) {
        case RIGHT:
            data.get(0).setLocation(point.x, point.y + 1);
            data.get(1).setLocation(point.x + 1, point.y + 1);
            data.get(3).setLocation(point.x, point.y - 1);
            state = REVERSESEVEN.DOWN;
            break;
        case DOWN:
            data.get(0).setLocation(point.x + 1, point.y);
            data.get(1).setLocation(point.x + 1, point.y - 1);
            data.get(3).setLocation(point.x - 1, point.y);
            state = REVERSESEVEN.LEFT;
            break;
        case LEFT:
            data.get(0).setLocation(point.x, point.y - 1);
            data.get(1).setLocation(point.x - 1, point.y - 1);
            data.get(3).setLocation(point.x, point.y + 1);
            state = REVERSESEVEN.TOP;
            break;
        case TOP:
            data.get(0).setLocation(point.x - 1, point.y);
            data.get(1).setLocation(point.x - 1, point.y + 1);
            data.get(3).setLocation(point.x + 1, point.y);
            state = REVERSESEVEN.RIGHT;
            break;
        default:
            throw new Exception("sth wrong here ReverseSevenShape");
        }
        initialLimits();
        getLimits();
        Teris.logger.info("ChairShape chaged to with " + state);
        return true;
    }

    @Override
    public boolean unChangeShape() throws Exception {
        Cell point = data.get(2);
        switch (state) {
        case RIGHT:
            data.get(0).setLocation(point.x, point.y - 1);
            data.get(1).setLocation(point.x - 1, point.y - 1);
            data.get(3).setLocation(point.x, point.y + 1);
            state = REVERSESEVEN.TOP;
            break;
        case TOP:
            data.get(0).setLocation(point.x + 1, point.y);
            data.get(1).setLocation(point.x + 1, point.y - 1);
            data.get(3).setLocation(point.x - 1, point.y);
            state = REVERSESEVEN.LEFT;
            break;
        case LEFT:
            data.get(0).setLocation(point.x, point.y + 1);
            data.get(1).setLocation(point.x + 1, point.y + 1);
            data.get(3).setLocation(point.x, point.y - 1);
            state = REVERSESEVEN.DOWN;
            break;
        case DOWN:
            data.get(0).setLocation(point.x - 1, point.y);
            data.get(1).setLocation(point.x - 1, point.y + 1);
            data.get(3).setLocation(point.x + 1, point.y);
            state = REVERSESEVEN.RIGHT;
            break;
        default:
            throw new Exception("sth wrong here ReverseSevenShape");
        }
        initialLimits();
        getLimits();
        Teris.logger.info("ChairShape chaged backed to with " + state);
        return true;
    }

}
