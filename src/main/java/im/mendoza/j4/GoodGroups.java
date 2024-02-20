package im.mendoza.j4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

public class GoodGroups {
    public Map<String, Set<String>> constraintsFrom(String constraintsString) throws IOException {
        Map<String, Set<String>> constraints = new HashMap<>();

        try (StringReader stringReader = new StringReader(constraintsString); BufferedReader reader = new BufferedReader(stringReader)) {

            int numConstraints = Integer.parseInt(reader.readLine().trim());

            for (int constraintNum = 0; constraintNum < numConstraints; constraintNum++) {
                String[] names = reader.readLine().split("\\s+");
                Set<String> name0Constraint = constraints.containsKey(names[0]) ? constraints.get(names[0]) : new HashSet<>();
                Set<String> name1Constraint = constraints.containsKey(names[1]) ? constraints.get(names[1]) : new HashSet<>();

                name0Constraint.add(names[1]);
                name1Constraint.add(names[0]);
                constraints.put(names[0], name0Constraint);
                constraints.put(names[1], name1Constraint);
            }

        }


        return constraints;
    }



}
