package im.mendoza.j4;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GoodGroupsTest {

    @Test
    public void violationsOf_basic() {
        String input = """
                1
                ELODIE CHI
                0
                2
                DWAYNE BEN ANJALI
                CHI FRANCOIS ELODIE
                """;

        GoodGroups goodGroups = new GoodGroups(input);
        assertEquals(goodGroups.getTogetherConstraints().size(), 2);
        assertEquals(goodGroups.getApartConstraints().size(), 0);
        assertEquals(2, goodGroups.getGroups().size());
        assertEquals(goodGroups.getViolations().size(), 0);
    }

    @Test
    public void violationsOf_multipleLines() {
        GoodGroups goodGroups = new GoodGroups("""
                3
                A B
                G L
                J K
                2
                D F
                D G
                4
                A C G
                B D F
                E H I
                J K L""");

        Map<String, Set<String>> constraints = goodGroups.getTogetherConstraints();

        assertTrue(constraints.containsKey("A"));
        assertTrue(constraints.get("A").contains("B"));
        assertTrue(constraints.containsKey("B"));
        assertTrue(constraints.get("B").contains("A"));
        assertTrue(constraints.containsKey("G"));
        assertTrue(constraints.get("G").contains("L"));
        assertTrue(constraints.containsKey("L"));
        assertTrue(constraints.get("L").contains("G"));
        assertTrue(constraints.containsKey("J"));
        assertTrue(constraints.get("J").contains("K"));
        assertTrue(constraints.containsKey("K"));
        assertTrue(constraints.get("K").contains("J"));
        assertEquals(4, goodGroups.getGroups().size());
        assertEquals(3, goodGroups.getViolations().size());

    }


    @Test
    public void violationsOf_repeatName() {
        GoodGroups goodGroups = new GoodGroups("""
                3
                A B
                G L
                A K
                0
                1
                A B G""");


        Map<String, Set<String>> constraints = goodGroups.getTogetherConstraints();

        assertTrue(constraints.containsKey("A"));
        assertTrue(constraints.get("A").contains("B"));
        assertTrue(constraints.containsKey("B"));
        assertTrue(constraints.get("B").contains("A"));
        assertTrue(constraints.containsKey("G"));
        assertTrue(constraints.get("G").contains("L"));
        assertTrue(constraints.containsKey("L"));
        assertTrue(constraints.get("L").contains("G"));
        assertTrue(constraints.containsKey("A"));
        assertTrue(constraints.get("A").contains("K"));
        assertTrue(constraints.containsKey("K"));
        assertTrue(constraints.get("K").contains("A"));
        assertEquals(1, goodGroups.getGroups().size());
        assertEquals(2, goodGroups.getViolations().size());

    }


}