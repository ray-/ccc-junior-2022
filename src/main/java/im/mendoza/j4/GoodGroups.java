package im.mendoza.j4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GoodGroups {
    private final Map<String, Set<String>> togetherConstraints;
    private final Map<String, Set<String>> apartConstraints;

    private final Map<String, String> violations;

    public GoodGroups(String input) {
        try (StringReader stringReader = new StringReader(input); BufferedReader reader = new BufferedReader(stringReader)) {
            final int numTogetherConstraints = Integer.parseInt(reader.readLine().trim());
            togetherConstraints = constraintsFrom(reader, numTogetherConstraints);

            final int numApartConstraints = Integer.parseInt(reader.readLine().trim());
            apartConstraints = constraintsFrom(reader, numApartConstraints);

            violations = new HashMap<>();

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Map<String, Set<String>> constraintsFrom(BufferedReader reader, int numConstraints) throws IOException {
        Map<String, Set<String>> constraints = new HashMap<>();
        for (int constraintNum = 0; constraintNum < numConstraints; constraintNum++) {
            String[] names = reader.readLine().split("\\s+");
            Set<String> name0Constraint = constraints.containsKey(names[0]) ? constraints.get(names[0]) : new HashSet<>();
            Set<String> name1Constraint = constraints.containsKey(names[1]) ? constraints.get(names[1]) : new HashSet<>();

            name0Constraint.add(names[1]);
            name1Constraint.add(names[0]);
            constraints.put(names[0], name0Constraint);
            constraints.put(names[1], name1Constraint);
        }

        return constraints;
    }


    public Map<String, Set<String>> getTogetherConstraints() {
        return togetherConstraints;
    }

    public Map<String, Set<String>> getApartConstraints() {
        return apartConstraints;
    }

    public Map<String, String> getViolations() {
        return violations;
    }
}
