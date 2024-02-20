package im.mendoza.j4;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GoodGroupsTest {
    @Test
    public void fromConstraints_basic() throws IOException {
        GoodGroups goodGroups = new GoodGroups();

        Map<String, Set<String>> constraints = goodGroups.constraintsFrom("1\n" +
                "ELODIE CHI");

        assertTrue(constraints.containsKey("ELODIE"));
        assertTrue(constraints.get("ELODIE").contains("CHI"));
        assertTrue(constraints.containsKey("CHI"));
        assertTrue(constraints.get("CHI").contains("ELODIE"));

    }

    @Test
    public void fromConstraints_multipleLines() throws IOException {
        GoodGroups goodGroups = new GoodGroups();

        Map<String, Set<String>> constraints = goodGroups.constraintsFrom("""
                3
                A B
                G L
                J K""");

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
    public void fromConstraints_repeatName() throws IOException {
        GoodGroups goodGroups = new GoodGroups();

        Map<String, Set<String>> constraints = goodGroups.constraintsFrom("""
                3
                A B
                G L
                A K""");

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