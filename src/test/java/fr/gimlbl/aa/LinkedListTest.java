package fr.gimlbl.aa;

import fr.gimlbl.aa.adt.Compare;
import fr.gimlbl.aa.adt.list.LinkedList;
import fr.gimlbl.aa.adt.list.List;
import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest {

    @Test
    public void testSize() {

        List<Integer> integerList = new LinkedList<>();
        integerList.addToHead(5);
        integerList.addToHead(3);
        integerList.addToHead(1);
        Assert.assertEquals(3, integerList.size());

        integerList.removeElementByPosition(3);
        Assert.assertEquals(2, integerList.size());

    }

    @Test
    public void testGetElementByPosition() {

        List<Integer> integerList = new LinkedList<>();
        integerList.addToTail(5);
        integerList.addToTail(3);
        integerList.addToTail(1);
        Assert.assertEquals((Integer) 5, integerList.getElementByPosition(1));
        Assert.assertEquals((Integer) 3, integerList.getElementByPosition(2));
        Assert.assertEquals((Integer) 1, integerList.getElementByPosition(3));

    }

    @Test
    public void testAddToTail() {

        List<Integer> integerList = new LinkedList<>();
        integerList.addToTail(5);
        integerList.addToTail(3);
        integerList.addToTail(1);
        Assert.assertEquals((Integer) 5, integerList.getElementByPosition(1));
        Assert.assertEquals((Integer) 3, integerList.getElementByPosition(2));
        Assert.assertEquals((Integer) 1, integerList.getElementByPosition(3));

    }

    @Test
    public void testAddToHead() {

        List<Integer> integerList = new LinkedList<>();
        integerList.addToHead(5);
        integerList.addToHead(3);
        integerList.addToHead(1);
        Assert.assertEquals((Integer) 1, integerList.getElementByPosition(1));
        Assert.assertEquals((Integer) 3, integerList.getElementByPosition(2));
        Assert.assertEquals((Integer) 5, integerList.getElementByPosition(3));

    }

    @Test
    public void testAddElementByPosition() {

        List<Integer> integerList = new LinkedList<>();
        integerList.addToTail(5);
        integerList.addToTail(3);
        integerList.addToTail(1);

        integerList.addElementByPosition(2, 1);
        Assert.assertEquals((Integer) 2, integerList.getElementByPosition(1));
        Assert.assertEquals((Integer) 5, integerList.getElementByPosition(2));

        integerList.addElementByPosition(4, integerList.size());
        Assert.assertEquals((Integer) 4, integerList.getElementByPosition(integerList.size()));

    }

    @Test
    public void testAddElement() {

        Compare<Integer, Integer> compare = new Compare<Integer, Integer>() {
            @Override
            public boolean compare(Integer a, Integer b) {
                return a>b;
            }
        };

        List<Integer> integerList = new LinkedList<>();
        integerList.addElement(3, compare);
        integerList.addElement(5, compare);
        integerList.addElement(1, compare);
        integerList.addElement(4, compare);
        integerList.addElement(2, compare);

        for(int i = 1; i <= integerList.size(); i++) {
            Assert.assertEquals((Integer) i, integerList.getElementByPosition(i));
        }

    }

    @Test
    public void testUpdateElementByPosition() {

        List<Integer> integerList = new LinkedList<>();
        integerList.addToTail(5);
        integerList.addToTail(3);
        integerList.addToTail(1);

        integerList.updateElementByPosition(7, 2);

        Assert.assertEquals((Integer) 7, integerList.getElementByPosition(2));

    }

    @Test
    public void testHasValue() {

        List<Integer> integerList = new LinkedList<>();
        integerList.addToTail(5);
        integerList.addToTail(3);
        integerList.addToTail(1);

        integerList.updateElementByPosition(7, 2);

        Assert.assertTrue(integerList.hasValue(7));
        Assert.assertTrue(integerList.hasValue(5));
        Assert.assertTrue(integerList.hasValue(1));
        Assert.assertFalse(integerList.hasValue(3));

    }

    @Test
    public void testRemoveElement() {

        List<Integer> integerList = new LinkedList<>();
        integerList.addToTail(5);
        integerList.addToTail(3);
        integerList.addToTail(1);

        integerList.removeElement(3);

        Assert.assertTrue(integerList.hasValue(5));
        Assert.assertTrue(integerList.hasValue(1));
        Assert.assertFalse(integerList.hasValue(3));

    }

    @Test
    public void testRemoveElementByPosition() {

        List<Integer> integerList = new LinkedList<>();
        integerList.addToTail(5);
        integerList.addToTail(3);
        integerList.addToTail(1);

        integerList.removeElementByPosition(2);

        Assert.assertTrue(integerList.hasValue(5));
        Assert.assertTrue(integerList.hasValue(1));
        Assert.assertFalse(integerList.hasValue(3));

    }

}
