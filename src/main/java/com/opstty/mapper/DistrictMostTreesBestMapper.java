package com.opstty.mapper;

import com.opstty.WritableMostTrees;
import com.opstty.WritableOldestTree;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class DistrictMostTreesBestMapper extends Mapper<Object, Text, NullWritable, WritableMostTrees> {

    private Text lineText = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        StringTokenizer line = new StringTokenizer(value.toString(),"\r\n");

        while (line.hasMoreTokens()) {

            lineText.set(line.nextToken());
            String[] tmp = lineText.toString().split("\t",-1);
            Text district = new Text(tmp[0]);
            IntWritable number = new IntWritable(Integer.parseInt(tmp[1]));

            context.write(NullWritable.get(), new WritableMostTrees(district, number));
        }
    }
}