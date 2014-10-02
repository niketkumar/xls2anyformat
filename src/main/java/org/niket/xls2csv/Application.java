package org.niket.xls2csv;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.niket.xls2csv.core.CoreConfig;
import org.niket.xls2csv.core.CoreInjector;
import org.niket.xls2csv.core.OutputStreams;
import org.niket.xls2csv.core.XlsToXFormat;
import org.niket.xls2csv.core.csv.XlsRowToCsv;
import org.niket.xls2csv.sandbox.SandBox;
import org.niket.xls2csv.sandbox.SandBoxInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by niket on 2/10/14.
 */
public class Application<S extends OutputStream> {
    private static final Logger Log = LoggerFactory.getLogger(Application.class);

    private final Injector injector;
    private final SandBox sandBox;

    Application() {
        injector = Guice.createInjector(new SandBoxInjector(), new CoreInjector());
        sandBox = injector.getInstance(SandBox.class);
    }

    public void convert(InputStream sourceInputStream, OutputStreams<S> outputStreams)
            throws InterruptedException, ExecutionException, TimeoutException {
        Log.info("Received new Request for User:{}, Problem:{}", outputStreams.getUserId(), outputStreams.getProblemId());
        sandBox.execute(new XlsToXFormat<>(sourceInputStream,
                outputStreams,
                injector.getInstance(CoreConfig.class),
                injector.getInstance(XlsRowToCsv.class)));
        Log.info("Finished converting for User:{}, Problem:{}", outputStreams.getUserId(), outputStreams.getProblemId());
    }

    public void shutdown() {
        sandBox.shutdown();
    }
}
