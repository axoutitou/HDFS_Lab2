package com.opstty.mapper;

import org.apache.hadoop.mapreduce.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OldestTreeMapperTest {
    @Mock
    private Mapper.Context context;
    private OldestTreeMapper tokenizerMapper;

    @Before
    public void setup() {
        this.tokenizerMapper = new OldestTreeMapper();
    }

    @Test
    public void testMap() throws IOException, InterruptedException {

    }
}