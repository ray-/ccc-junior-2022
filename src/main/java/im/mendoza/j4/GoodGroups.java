package im.mendoza.j4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class GoodGroups {
    private final Map<String, Set<String>> togetherConstraints;
    private final Map<String, Set<String>> apartConstraints;

    private final List<Set<String>> groups;

    private final Set<Set<String>> violations;

    public GoodGroups(String input) {
        try (StringReader stringReader = new StringReader(input); BufferedReader reader = new BufferedReader(stringReader)) {
            final int numTogetherConstraints = Integer.parseInt(reader.readLine().trim());
            togetherConstraints = constraintsFrom(reader, numTogetherConstraints);

            final int numApartConstraints = Integer.parseInt(reader.readLine().trim());
            apartConstraints = constraintsFrom(reader, numApartConstraints);

            final int numGroups = Integer.parseInt(reader.readLine().trim());
            groups = groupsFrom(reader, numGroups);
            violations = violationsIn(groups);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Set<Set<String>> violationsIn(List<Set<String>> groups) {
        final Set<Set<String>> violations = new HashSet<>();

        for(Set<String> group : groups) {
            for (String name : group) {
                findTogetherViolations(group, name, violations);
                findApartViolations(group, name, violations);
            }
        }
        return violations;
    }

    private void findApartViolations(Set<String> group, String name, Set<Set<String>> violations) {
        final Set<String> apartConstraintsForName = apartConstraints.get(name);
        if (apartConstraintsForName != null) {
            for (String apartName : apartConstraintsForName) {
                if (group.contains(apartName)) {
                    violations.add(new HashSet<>(Arrays.asList(name, apartName)));
                }
            }
        }
    }

    private void findTogetherViolations(Set<String> group, String name, Set<Set<String>> violations) {
        final Set<String> togetherConstraintsForName = togetherConstraints.get(name);
        if (togetherConstraintsForName != null) {
            for (String togetherName : togetherConstraintsForName) {
                if (!group.contains(togetherName)) {
                    violations.add(new HashSet<>(Arrays.asList(name, togetherName)));
                }
            }
        }
    }

    private List<Set<String>> groupsFrom(BufferedReader reader, int numGroups) throws IOException {
        final List<Set<String>> groups = new ArrayList<>();

        for(int groupNum = 0; groupNum < numGroups; groupNum++) {
            final String[] names = namesFrom(reader);
            final Set<String> group = new HashSet<>(Arrays.asList(names));

            groups.add(group);
        }

        return groups;
    }

    public Map<String, Set<String>> constraintsFrom(BufferedReader reader, int numConstraints) throws IOException {
        Map<String, Set<String>> constraints = new HashMap<>();
        for (int constraintNum = 0; constraintNum < numConstraints; constraintNum++) {
            String[] names = namesFrom(reader);
            Set<String> name0Constraint = constraints.containsKey(names[0]) ? constraints.get(names[0]) : new HashSet<>();
            Set<String> name1Constraint = constraints.containsKey(names[1]) ? constraints.get(names[1]) : new HashSet<>();

            name0Constraint.add(names[1]);
            name1Constraint.add(names[0]);
            constraints.put(names[0], name0Constraint);
            constraints.put(names[1], name1Constraint);
        }

        return constraints;
    }

    private static String[] namesFrom(BufferedReader reader) throws IOException {
        return reader.readLine().split("\\s+");
    }


    public Map<String, Set<String>> getTogetherConstraints() {
        return togetherConstraints;
    }

    public Map<String, Set<String>> getApartConstraints() {
        return apartConstraints;
    }

    public Set<Set<String>> getViolations() {
        return violations;
    }

    public List<Set<String>> getGroups() {
        return groups;
    }
}
