package org.niket.xls2csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by niket on 2/10/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(args.length);
        if (args.length != 4) {
            System.err.println("Usage: java -jar xls2csv.jar <userId> <problemId> <input xls file path> <output csv root folder>");
            System.exit(-1);
        }

        String userId = args[0];
        String problemId = args[1];
        String sourceFilePath = args[2];
        String rootFolder = args[3];
        Application<FileOutputStream> application = new Application<>();
        try {
            try (FileInputStream sourceInputStream = new FileInputStream(new File(sourceFilePath))) {
                try (FileSystemOutputStreams outputStreams = new FileSystemOutputStreams(rootFolder, userId, problemId).init()) {
                    application.convert(sourceInputStream, outputStreams);
                }
            }
        } finally {
            application.shutdown();
        }

    }
}
