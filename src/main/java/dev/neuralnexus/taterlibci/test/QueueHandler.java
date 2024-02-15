package dev.neuralnexus.taterlibci.test;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/** A class for handling the queue. */
public class QueueHandler {
    public static Map<UUID, Set<TaskData>> queue;
    public static Thread statusThread;

    public static void startStatusThread() {}
}
