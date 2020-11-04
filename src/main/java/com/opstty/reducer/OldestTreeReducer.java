package com.opstty.reducer;

import com.opstty.WritableOldestTree;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class OldestTreeReducer extends Reducer<WritableOldestTree, NullWritable, WritableOldestTree, NullWritable> {
    public void reduce(WritableOldestTree key, Iterable<NullWritable> values, Context context)
            throws IOException, InterruptedException {

        int keyYear = Integer.parseInt(key.getYear().toString());
        int oldestYear = WritableOldestTree.getOldestYear();

        if (keyYear < oldestYear && keyYear!=0) {
            int id = Integer.parseInt(key.getId().toString());
            String district = key.getDistrict().toString();

            WritableOldestTree.update(id, district, keyYear);
            context.write(key, NullWritable.get());
        }
    }
}
