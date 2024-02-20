package im.mendoza.j4;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GoodGroupsTest {
    @Test
    public void fromConstraintsBasic() throws IOException {
        GoodGroups goodGroups = new GoodGroups();

        Map<String, Set<String>> constraints = goodGroups.constraintsFrom("1\n" +
                "ELODIE CHI");

        assertTrue(constraints.containsKey("ELODIE"));
        assertTrue(constraints.get("ELODIE").contains("CHI"));
        assertTrue(constraints.containsKey("CHI"));
        assertTrue(constraints.get("CHI").contains("ELODIE"));


    }


}