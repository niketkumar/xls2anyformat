xls2anyformat
=============

Converts XLS file data to any target format.
Target format support begins with CSV.
The converter runs within a timeout constraint.

Uses JDK 8

Build:
mvn clean install

Run from commandline:
java -jar target/xls2csv-0.0.1.jar <userId> <problemId> <input xls file path> <output csv root folder>

The above approach is meant to run the application when:

 input file is stored on hard disc.

 CSV files(one per sheet) are expected to be created in output-csv-root-folder/userId/problemId/ folder.

See the class org.niket.xls2csv.Main. It is the only class which is aware of filesystem.


However, the core application can run without accessing filesystem. Apart from the above mentioned running approach,
it is designed to accept input from java.io.InputStream and writes output to one or more java.io.OutputStream.

See the class org.niket.xls2csv.Application. It is the main entry point to the core solution.

If a conversion task is taking long time (configurable timeout values) then the task is stopped.

See src/main/resources/xls2csv.properties for configurable properties.

