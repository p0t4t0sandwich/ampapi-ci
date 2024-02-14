package dev.neuralnexus.taterlibci.test;

import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;

import java.util.Set;
import java.util.UUID;

/** A class for handling test settings. */
public class TestSettings {
    public UUID testId;
    public String jobArtifactURL;
    public MinecraftVersion version;
    public ServerType serverType;
    public Set<String> dependencies;
    public Set<String> wipeFiles;

    public TestSettings(
            UUID testId,
            String jobArtifactURL,
            String version,
            String serverType,
            Set<String> dependencies,
            Set<String> wipeFiles) {
        this.testId = testId;
        this.jobArtifactURL = jobArtifactURL;
        this.version = MinecraftVersion.from(version);
        this.serverType = ServerType.from(serverType);
        this.dependencies = dependencies;
        this.wipeFiles = wipeFiles;
    }

    @Override
    public String toString() {
        return "TestSettings{"
                + "testId="
                + testId
                + "jobArtifactURL="
                + jobArtifactURL
                + ", version="
                + version
                + ", serverType="
                + serverType
                + ", dependencies="
                + dependencies
                + ", wipeFiles="
                + wipeFiles
                + '}';
    }
}
