package test;

import fr.gimlbl.aa.adt.Compare;
import fr.gimlbl.aa.adt.list.LinkedList;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.adt.list.ReadonlyList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class ReadOnlyListTest {



    @Test
    public void testAddToTail() {

        List<Integer> integerList = new ReadonlyList<>(new LinkedList<>());
        Assert.assertThrows(UnsupportedOperationException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                integerList.addToTail(5);
            }
        });
    }

    @Test
    public void testAddToHead() {
        List<Integer> integerList = new ReadonlyList<>(new LinkedList<>());
        Assert.assertThrows(UnsupportedOperationException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                integerList.addToHead(5);
            }
        });

    }

    @Test
    public void testAddElementByPosition() {

        List<Integer> integerList = new ReadonlyList<>(new LinkedList<>());
        Assert.assertThrows(UnsupportedOperationException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                integerList.addElementByPosition(5, 1);
            }
        });

    }

    @Test
    public void testAddElement() {

        Compare<Integer, Integer> compare = new Compare<Integer, Integer>() {
            @Override
            public boolean compare(Integer a, Integer b) {
                return a>b;
            }
        };

        List<Integer> integerList = new ReadonlyList<>(new LinkedList<>());
        Assert.assertThrows(UnsupportedOperationException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                integerList.addElement(5, compare);
            }
        });

    }

    @Test
    public void testUpdateElementByPosition() {

        List<Integer> integerList = new ReadonlyList<>(new LinkedList<>());
        Assert.assertThrows(UnsupportedOperationException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                integerList.updateElementByPosition(5, 5);
            }
        });

    }

}
