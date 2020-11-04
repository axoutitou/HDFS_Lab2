package com.opstty.reducer;

import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OldestTreeReducerTest {
    @Mock
    private Reducer.Context context;
    private OldestTreeReducer oldestTreeReducer;

    @Before
    public void setup() {
        this.oldestTreeReducer = new OldestTreeReducer();
    }

    @Test
    public void testReduce() throws IOException, InterruptedException {

    }
}
