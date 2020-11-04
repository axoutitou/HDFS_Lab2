package com.opstty.job;

import com.opstty.WritableMostTrees;
import com.opstty.mapper.DistrictMostTreesBestMapper;
import com.opstty.mapper.DistrictMostTreesSumMapper;
import com.opstty.reducer.DistrictMostTreesBestReducer;
import com.opstty.reducer.DistrictMostTreesSumReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class DistrictMostTrees {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: districtsTrees <in> [<in>...] <out>");
            System.exit(2);
        }
        Job job = Job.getInstance(conf, "districtsTrees");
        job.setJarByClass(DistrictMostTrees.class);
        job.setMapperClass(DistrictMostTreesSumMapper.class);
        job.setCombinerClass(DistrictMostTreesSumReducer.class);
        job.setReducerClass(DistrictMostTreesSumReducer.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.waitForCompletion(true);

        Job job2 = Job.getInstance(conf, "districtsTreesSecond");
        job2.setJarByClass(DistrictMostTrees.class);
        job2.setMapperClass(DistrictMostTreesBestMapper.class);
        job2.setCombinerClass(DistrictMostTreesBestReducer.class);
        job2.setReducerClass(DistrictMostTreesBestReducer.class);
        job2.setOutputKeyClass(NullWritable.class);
        job2.setOutputValueClass(WritableMostTrees.class);
        FileInputFormat.addInputPath(job2, new Path(args[1]));
        FileOutputFormat.setOutputPath(job2, new Path(args[2]));

        System.exit(job2.waitForCompletion(true ) ? 0 : 1);
    }
}
