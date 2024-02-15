package dev.neuralnexus.taterlibci.utils;

import dev.neuralnexus.ampapi.modules.CommonAPI;
import dev.neuralnexus.ampapi.types.Updates;

import java.util.Arrays;

/** A class for getting updated stats from the AMP API. */
public class GetUpdatesHandler {
    /**
     * Gets the updated stats from the AMP API.
     *
     * @param API The API to use.
     * @return The updated stats.
     */
    public static Updates getUpdates(CommonAPI API) {
        return API.Core.GetUpdates();
    }

    /**
     * Check the updates object for "Test Complete"
     *
     * @param API The API to use.
     * @return The updated stats.
     */
    public static boolean checkForTestComplete(CommonAPI API) {
        return Arrays.stream(API.Core.GetUpdates().ConsoleEntries)
                .anyMatch(entry -> entry.Contents.toLowerCase().contains("test complete"));
    }
}
