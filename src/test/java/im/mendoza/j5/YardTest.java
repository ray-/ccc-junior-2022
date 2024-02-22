package im.mendoza.j5;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class YardTest {

    @Test
    void fitPoolOfSize_noTrees() {
        final Yard yard = new Yard(5, 5);
        final Point poolLocation = yard.fitPoolOfSize(2, 2);
        assertNotNull(poolLocation);
        assertTrue(poolLocation.getX() >= 0);
        assertTrue(poolLocation.getY() >= 0);
    }

    @Test
    void fitPoolOfSize_oneTree() {
        final Yard yard = new Yard(5, 5, new Point(1,1));
        final Point poolLocation = yard.fitPoolOfSize(2, 2);
        assertNotNull(poolLocation);
        assertTrue(poolLocation.getX() > 0, "Tree is in (1,1) so pool can't be: "  + poolLocation);
        assertTrue(poolLocation.getY() > 0, "Tree is in (1,1) so pool can't be" + poolLocation);
    }

    @Test
    void fitPoolOfSize_sampleInput1() {
        final Yard yard = new Yard(5, 5, new Point(2,4));
        final Point poolLocation = yard.fitPoolOfSize(3, 3);
        assertNull(yard.fitPoolOfSize(5, 5));
        assertNull(yard.fitPoolOfSize(4, 4));
        assertNotNull(yard.fitPoolOfSize(3, 3));
        assertTrue(poolLocation.getX() >= 0);
        assertTrue(poolLocation.getY() >= 0);
    }

    @Test
    void fitPoolOfSize_sampleInput2() {
        final Yard yard = new Yard(15, 15, new Point(4,7), new Point(4,1), new Point(14,11), new Point(10,6), new Point(13,4), new Point(4,10), new Point(10,3), new Point(9,14));

        assertNull(yard.fitPoolOfSize(9, 9));
        assertNull(yard.fitPoolOfSize(8, 8));
        assertNotNull(yard.fitPoolOfSize(7, 7));
        assertNotNull(yard.fitPoolOfSize(6, 6));
        final Point poolLocation = yard.fitPoolOfSize(7, 7);
        assertTrue(poolLocation.getX() >= 0);
        assertTrue(poolLocation.getY() >= 0);
    }
}