package com.opstty.mapper;

import com.opstty.WritableOldestTree;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class OldestTreeMapper extends Mapper<Object, Text, WritableOldestTree, NullWritable> {

    private WritableOldestTree writer = new WritableOldestTree();
    private Text lineText = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        StringTokenizer line = new StringTokenizer(value.toString(),"\r\n");

        while (line.hasMoreTokens()) {
            lineText.set(line.nextToken());
            String[] tmp = lineText.toString().split(";",-1);

            if(!tmp[0].equals("GEOPOINT") && !tmp[5].isEmpty() && !tmp[11].isEmpty() && !tmp[1].isEmpty()) {

                IntWritable id = new IntWritable(Integer.parseInt(tmp[11]));
                Text district = new Text(tmp[1]);
                IntWritable year = new IntWritable(Integer.parseInt(tmp[5]));
                writer.setValues(id, district, year);

                context.write(writer, NullWritable.get());
            }
        }
    }

}
