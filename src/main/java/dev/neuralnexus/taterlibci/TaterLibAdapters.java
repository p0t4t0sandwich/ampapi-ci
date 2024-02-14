package dev.neuralnexus.taterlibci;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.neuralnexus.ampapi.modules.CommonAPI;
import dev.neuralnexus.taterlib.config.dump.DumpInfo;

import java.util.Optional;

/** A class for TaterLib adapters. */
public class TaterLibAdapters {
    /**
     * Parses the TaterLib dump.
     *
     * @param API The API to use.
     * @return The dump info.
     */
    public Optional<DumpInfo> parseTaterLibDump(CommonAPI API) {
        Optional<String> dumpString = AMPUtils.readTextFile(API, "logs/taterlib-dump.json");
        if (dumpString.isEmpty()) {
            return Optional.empty();
        }
        Gson gson = new GsonBuilder().create();
        return Optional.of(gson.fromJson(dumpString.get(), DumpInfo.class));
    }
}
