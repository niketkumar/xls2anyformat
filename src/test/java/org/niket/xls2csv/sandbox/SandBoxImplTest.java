package org.niket.xls2csv.sandbox;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.niket.xls2csv.core.CoreInjector;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class SandBoxImplTest {

    private Injector injector;

    @Before
    public void setUp() throws Exception {
        injector = Guice.createInjector(new SandBoxInjector(), new CoreInjector());
    }

    @After
    public void tearDown() throws Exception {
        injector = null;
    }

    @Test
    public void testExecuteWithoutError() throws Exception {
        SandBox sandBox = injector.getInstance(SandBox.class);
        sandBox.execute(() -> true);
    }

    @Test(expected = TimeoutException.class)
    public void testExecuteWithTimeout() throws Exception {
        SandBox sandBox = injector.getInstance(SandBox.class);
        sandBox.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return true;
        });
    }

    @Test(expected = ExecutionException.class)
    public void testExecuteWithExecutionException() throws Exception {
        SandBox sandBox = injector.getInstance(SandBox.class);
        sandBox.execute(() -> {
            throw new RuntimeException("test execution failed");
        });
    }
}