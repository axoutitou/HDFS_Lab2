package com.opstty.reducer;

import com.opstty.WritableMostTrees;
import com.opstty.WritableOldestTree;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DistrictMostTreesBestReducer extends Reducer<NullWritable, WritableMostTrees, NullWritable, WritableMostTrees> {
    public void reduce(NullWritable key, Iterable<WritableMostTrees> values, Context context) throws IOException, InterruptedException {

        for(WritableMostTrees val : values){
            int resultTress = WritableMostTrees.getBestNumbersOfTrees();
            int currentTrees = Integer.parseInt(val.getNumberOfTrees().toString());

            if(currentTrees > resultTress){
                WritableMostTrees.update(val.getDistrict().toString(), currentTrees);
                context.write(NullWritable.get(), val);
            }
        }
    }
}