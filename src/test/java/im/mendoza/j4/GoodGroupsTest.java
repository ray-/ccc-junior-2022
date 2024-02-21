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
        assertEquals(goodGroups.getViolations().size(), 0);
    }

    @Test
    public void fromConstraints_basic() {
        GoodGroups goodGroups = new GoodGroups("""
                1
                ELODIE CHI
                0
                2
                DWAYNE BEN ANJALI
                CHI FRANCOIS ELODIE""");

        Map<String, Set<String>> constraints = goodGroups.getTogetherConstraints();

        assertTrue(constraints.containsKey("ELODIE"));
        assertTrue(constraints.get("ELODIE").contains("CHI"));
        assertTrue(constraints.containsKey("CHI"));
        assertTrue(constraints.get("CHI").contains("ELODIE"));

    }

    @Test
    public void fromConstraints_multipleLines() {
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

    }


    @Test
    public void fromConstraints_repeatName() {
        GoodGroups goodGroups = new GoodGroups("""
                3
                A B
                G L
                A K
                0""");

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

    }


}