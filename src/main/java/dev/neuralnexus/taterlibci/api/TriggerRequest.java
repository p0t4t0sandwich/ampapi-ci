package dev.neuralnexus.taterlibci.api;

import dev.neuralnexus.taterlibci.test.TestSettings;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/** A class for handling trigger requests. */
public class TriggerRequest {
    public UUID testId = UUID.randomUUID();
    public String job;
    public Set<String> versions = Set.of();
    public Set<String> serverTypes = Set.of();
    public Set<String> dependencies = Set.of();
    public Set<String> wipeFiles = Set.of();

    /**
     * Generates all possible permutations of the test settings.
     *
     * @return The set of all possible permutations.
     */
    public Set<TestSettings> generatePermutations() {
        Set<TestSettings> settings = new HashSet<>();
        for (String version : versions) {
            for (String serverType : serverTypes) {
                settings.add(
                        new TestSettings(
                                testId, job, version, serverType, dependencies, wipeFiles));
            }
        }
        return settings;
    }

    @Override
    public String toString() {
        return "TriggerRequest{"
                + "testId="
                + testId
                + ", job="
                + job
                + ", versions="
                + versions
                + ", serverTypes="
                + serverTypes
                + ", dependencies="
                + dependencies
                + ", wipeFiles="
                + wipeFiles
                + '}';
    }
}
