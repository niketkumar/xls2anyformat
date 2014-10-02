package org.niket.xls2csv;

import org.niket.xls2csv.core.AbstractOutputStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;

/**
 * OutputStreams implementation to write output format to filesystem.
 * Created by niket on 2/10/14.
 */
public class FileSystemOutputStreams extends AbstractOutputStreams<FileOutputStream> {
    private static final Logger Log = LoggerFactory.getLogger(FileSystemOutputStreams.class);

    private final String rootFolder;
    private File targetFolder;

    public FileSystemOutputStreams(String rootFolder, String userId, String problemId) {
        super(userId, problemId);
        this.rootFolder = rootFolder;
    }

    @Override
    public FileSystemOutputStreams init() {
        targetFolder = new File(new File(rootFolder, getUserId()), getProblemId());
        if (targetFolder.exists()) {
            if (!targetFolder.isDirectory())
                throw new RuntimeException("Given path is not a targetFolder: " + targetFolder.getAbsolutePath());
            if (!targetFolder.canWrite())
                throw new RuntimeException("Given targetFolder is not writable: " + targetFolder.getAbsolutePath());
        } else {
            if (!targetFolder.mkdirs())
                throw new RuntimeException("Could not create targetFolder: " + targetFolder.getAbsolutePath());
        }
        return this;
    }

    @Override
    protected FileOutputStream createOutputStream(String streamId) throws Exception {
        File file = new File(targetFolder, streamId);
        Log.info("Opening stream to write into File:{}", file.getAbsolutePath());
        return new FileOutputStream(file);
    }

}
