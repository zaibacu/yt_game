package lt.griaustnis.ytgame.utils;

import lt.griaustinis.ytgame.utils.CircularList;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class CirculatListTest {

    @Test
    public void testDummyScenario(){
        CircularList<Integer> ints = new CircularList<>(Arrays.asList(1, 2, 3));
        assertTrue(ints.iterator().next() == 1);
        assertTrue(ints.iterator().next() == 2);
        assertTrue(ints.iterator().next() == 3);

        assertTrue(ints.iterator().next() == 1);
        assertTrue(ints.iterator().next() == 2);
        assertTrue(ints.iterator().next() == 3);
    }
}
