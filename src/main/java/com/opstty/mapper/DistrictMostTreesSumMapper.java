package com.opstty.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class DistrictMostTreesSumMapper extends Mapper<Object, Text, IntWritable, IntWritable> {
    private Text lineText = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        StringTokenizer line = new StringTokenizer(value.toString(),"\r\n");
        IntWritable one = new IntWritable(1);

        while (line.hasMoreTokens()) {

            lineText.set(line.nextToken());
            String[] tmp = lineText.toString().split(";",-1);

            if(!tmp[0].equals("GEOPOINT") && !tmp[1].isEmpty()) {
                IntWritable district = new IntWritable(Integer.parseInt(tmp[1]));
                context.write(district, one);
            }
        }
    }
}
