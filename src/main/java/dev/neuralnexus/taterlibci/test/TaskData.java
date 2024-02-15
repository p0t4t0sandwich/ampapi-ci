package dev.neuralnexus.taterlibci.test;

import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;

import java.util.Set;
import java.util.UUID;

/** A class for handling test settings. */
public class TaskData {
    public UUID testId;
    public String jobArtifactURL;
    public MinecraftVersion version;
    public ServerType serverType;
    public Set<String> dependencies;
    public Set<String> wipeFiles;
    public TaskState state = TaskState.QUEUED;

    public TaskData(
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

    /**
     * Updates the state of the task.
     *
     * @param state The new state.
     */
    public void setState(TaskState state) {
        this.state = state;
    }

    public enum TaskState {
        QUEUED,
        RUNNING,
        COMPLETE,
        FAILED
    }
}
