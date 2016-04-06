package com.game.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.game.shape.SqureShape;
import com.game.state.ShapeEnum;
import com.game.util.ShapesData;

public class TestSqure {
    private SqureShape squre;

    @Before
    public void setUp() throws Exception {
        squre = new SqureShape(ShapesData.makeShapeData(ShapeEnum.SQURE), 20,
                10);
        Assert.assertNotNull(squre);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testchangeShape() {
        Assert.assertTrue(squre.changeShape());
    }

    @Test
    public void testunChangeShape() {
        Assert.assertTrue(squre.unChangeShape());
    }

}
