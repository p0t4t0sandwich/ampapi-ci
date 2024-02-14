package dev.neuralnexus.taterlibci;

import dev.neuralnexus.ampapi.modules.CommonAPI;
import dev.neuralnexus.ampapi.types.FileDirectory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

/** A utility class for interacting with the AMP API. */
public class AMPUtils {
    /**
     * Clears the file manager of all files and directories.
     *
     * @param API The API to use.
     * @param emptyTrash Whether to empty the trash after trashing all files and directories.
     */
    public static void clearFileManager(CommonAPI API, boolean emptyTrash) {
        List<FileDirectory> dirListing = API.FileManagerPlugin.GetDirectoryListing("");
        dirListing.forEach(
                (file) -> {
                    if (file.IsDirectory) {
                        API.FileManagerPlugin.TrashDirectory(file.Filename);
                    } else {
                        API.FileManagerPlugin.TrashFile(file.Filename);
                    }
                });
        if (emptyTrash) {
            API.FileManagerPlugin.EmptyTrash(".trash");
        }
    }

    /**
     * Clears the file manager of all files and directories.
     *
     * @param API The API to use.
     */
    public static void clearFileManager(CommonAPI API) {
        clearFileManager(API, false);
    }

    /**
     * Reads a text file from the file manager.
     *
     * @param API The API to use.
     * @param filePath The path to the file to read.
     * @return The contents of the file, or an empty optional if the file does not exist.
     */
    public static Optional<String> readTextFile(CommonAPI API, String filePath) {
        if (filePath.startsWith("/")) {
            filePath = filePath.substring(1);
        } else if (filePath.startsWith("./")) {
            filePath = filePath.substring(2);
        }
        int lastSlash = filePath.lastIndexOf("/");
        String directory = "";
        if (lastSlash != -1) {
            directory = filePath.substring(0, lastSlash);
        }
        String fileName = filePath.substring(lastSlash + 1);
        Optional<FileDirectory> file =
                API.FileManagerPlugin.GetDirectoryListing(directory).stream()
                        .filter((f) -> f.Filename.equals(fileName))
                        .findFirst();
        if (file.isEmpty()) {
            return Optional.empty();
        }
        String base64 =
                API.FileManagerPlugin.ReadFileChunk(filePath, 0, file.get().SizeBytes.intValue())
                        .Result;
        return Optional.of(new String(Base64.getDecoder().decode(base64), StandardCharsets.UTF_8));
    }
}
