package com.src.test.java;

import org.junit.Assert;
import org.junit.Test;

public class TestAirplane {
    @Test
    public void testArrangeSeatsInPlane(){
        int[][] inputArray = { { 3, 2 }, { 4, 3 }, { 2, 3 }, { 3, 4 } };
        int customerCount = 30;
        Airplane airplane = new Airplane();
        int[][][] result = airplane.arrangeSeatsInPlane(inputArray,customerCount);
        Assert.assertEquals(1, result[0][0][2]);
       Assert.assertEquals(30, result[1][1][1]);
    }
	
	 @Test
    public void testArrangeSeatsInPlaneNegative(){
        int[][] inputArray = { { 3, 2 }, { 4, 3 }, { 2, 3 }, { 3, 4 } };
        int customerCount = 30;
        Airplane airplane = new Airplane();
        int[][][] result = airplane.arrangeSeatsInPlane(inputArray,customerCount);
        Assert.assertNotEquals(2, result[0][0][2]);
        Assert.assertNotEquals(31, result[1][1][1]);
    }
}
