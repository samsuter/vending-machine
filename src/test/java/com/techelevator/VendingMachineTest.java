package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class VendingMachineTest {

    private VendingMachine vendingMachingTest = new VendingMachine();

    @Test
    public void feedMoney_returnsAddedMoney() {
        BigDecimal expectedResult = new BigDecimal("10");
        vendingMachingTest.setCurMoney(BigDecimal.ZERO);
        vendingMachingTest.setUserFeedMoney(BigDecimal.TEN);

        BigDecimal result = vendingMachingTest.feedMoney();

        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void feedMoney_inputIsNotWholeNumber() {
        BigDecimal expectedResult = new BigDecimal("0");
        vendingMachingTest.setCurMoney(BigDecimal.ZERO);
        vendingMachingTest.setUserFeedMoney(BigDecimal.valueOf(4.2));

        BigDecimal result = vendingMachingTest.feedMoney();

        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void feedMoney_inputIsNegative() {
        BigDecimal expectedResult = new BigDecimal("0");
        vendingMachingTest.setCurMoney(BigDecimal.ZERO);
        vendingMachingTest.setUserFeedMoney(BigDecimal.valueOf(-4));

        BigDecimal result = vendingMachingTest.feedMoney();

        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void feedMoney_inputIsZero() {
        BigDecimal expectedResult = new BigDecimal("0");
        vendingMachingTest.setCurMoney(BigDecimal.ZERO);
        vendingMachingTest.setUserFeedMoney(BigDecimal.valueOf(0));

        BigDecimal result = vendingMachingTest.feedMoney();

        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void purchase_happyPath() {
        BigDecimal expectedCurMoney = new BigDecimal("4.15");
        int expectedItemStock = 4;
        vendingMachingTest.setCurMoney(BigDecimal.valueOf(5));
        vendingMachingTest.setItemSelection("D1");

        vendingMachingTest.purchaseItem();

        Assert.assertEquals(expectedCurMoney, vendingMachingTest.getCurMoney());
        Assert.assertEquals(expectedItemStock, vendingMachingTest.getItemMap().get("D1").getItemStock());
    }

    @Test
    public void purchase_invalidSelection() {
        BigDecimal expectedCurMoney = new BigDecimal("5");
        vendingMachingTest.setCurMoney(BigDecimal.valueOf(5));
        vendingMachingTest.setItemSelection("R");

        vendingMachingTest.purchaseItem();

        Assert.assertEquals(expectedCurMoney, vendingMachingTest.getCurMoney());
    }

    @Test
    public void purchase_notEnoughMoney() {
        BigDecimal expectedCurMoney = new BigDecimal(".01");
        int expectedItemStock = 5;
        vendingMachingTest.setCurMoney(BigDecimal.valueOf(.01));
        vendingMachingTest.setItemSelection("D1");

        vendingMachingTest.purchaseItem();

        Assert.assertEquals(expectedCurMoney, vendingMachingTest.getCurMoney());
        Assert.assertEquals(expectedItemStock, vendingMachingTest.getItemMap().get("D1").getItemStock());
    }

    @Test
    public void makeChange_happyPath() {
        BigDecimal expectedCurMoney = new BigDecimal("0.00");
        int[] expectedChange = {9, 1, 0, 1};
        vendingMachingTest.setCurMoney(BigDecimal.valueOf(2.36));

        int[] result = vendingMachingTest.getChange();

        Assert.assertEquals(expectedCurMoney, vendingMachingTest.getCurMoney());
        Assert.assertArrayEquals(expectedChange, result);
    }

    @Test
    public void makeChange_onlyQuarters() {
        BigDecimal expectedCurMoney = new BigDecimal("0.00");
        int[] expectedChange = {8, 0, 0, 0};
        vendingMachingTest.setCurMoney(BigDecimal.valueOf(2));

        int[] result = vendingMachingTest.getChange();

        Assert.assertEquals(expectedCurMoney, vendingMachingTest.getCurMoney());
        Assert.assertArrayEquals(expectedChange, result);
    }

    @Test
    public void makeChange_noMoney() {
        BigDecimal expectedCurMoney = new BigDecimal("0.0");
        int[] expectedChange = {0, 0, 0, 0};
        vendingMachingTest.setCurMoney(BigDecimal.valueOf(0.00));

        int[] result = vendingMachingTest.getChange();

        Assert.assertEquals(expectedCurMoney, vendingMachingTest.getCurMoney());
        Assert.assertArrayEquals(expectedChange, result);
    }

}
