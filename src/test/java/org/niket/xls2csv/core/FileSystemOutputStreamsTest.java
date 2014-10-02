package org.niket.xls2csv.core;

import org.junit.Test;
import org.niket.xls2csv.FileSystemOutputStreams;

public class FileSystemOutputStreamsTest {
    @Test
    public void testInit() {
        new FileSystemOutputStreams("target", "user1", "problem1").init();
    }

    @Test(expected = RuntimeException.class)
    public void testInitPathIsAFile() {
        new FileSystemOutputStreams("target/classes/xls2csv.properties", "user1", "problem1").init();
    }
}