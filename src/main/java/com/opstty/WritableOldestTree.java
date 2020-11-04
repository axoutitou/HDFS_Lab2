package com.opstty;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class WritableOldestTree implements Writable, WritableComparable<WritableOldestTree> {
    private IntWritable id;
    private Text district;
    private IntWritable year;
    private static int oldestId = 0;
    private static String oldestDistrict = "None";
    private static int oldestYear = 4000;

    public WritableOldestTree(){
        id = new IntWritable(0);
        district = new Text("Initialize");
        year = new IntWritable(4000);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        id.write(dataOutput);
        district.write(dataOutput);
        year.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        id.readFields(dataInput);
        district.readFields(dataInput);
        year.readFields(dataInput);
    }

    public String toString(){
        return "##Id->"+this.id.toString()+"\n"
                +"District->"+this.district.toString()+"\n"
                +"Year->"+this.year.toString()+"\n"
                +"OldestId->"+oldestId+"\n"
                +"OldestDistrict->"+oldestDistrict+"\n"
                +"Oldestyear->"+oldestYear+"##\n";
    }

    public Text getDistrict() {
        return district;
    }

    public IntWritable getYear() {
        return year;
    }

    public IntWritable getId() {
        return id;
    }

    public static int getOldestYear(){
        return oldestYear;
    }

    public void setValues(IntWritable geoId, Text district, IntWritable year){
        this.id = geoId;
        this.district = district;
        this.year = year;
    }
    public static void update(int geoID, String district, int year){
        oldestId = geoID;
        oldestDistrict = district;
        oldestYear = year;
    }
    @Override
    public int compareTo(WritableOldestTree o) {
        if(this.id.compareTo(o.getId()) == 0) return 0;
        else return 1;
    }
}

