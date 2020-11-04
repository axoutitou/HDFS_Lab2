package com.opstty;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class WritableMostTrees implements Writable {
    private Text district;
    private IntWritable numberOfTrees;
    private static String bestDistrict = "None";
    private static int bestNumbersOfTrees = 0;


    public WritableMostTrees(){
        district = new Text("Initialize");
        numberOfTrees = new IntWritable(0);
    }
    public WritableMostTrees(Text distr, IntWritable nb){
        district = distr;
        numberOfTrees = nb;
    }
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        district.write(dataOutput);
        numberOfTrees.write(dataOutput);
    }
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        district.readFields(dataInput);
        numberOfTrees.readFields(dataInput);
    }
    public String toString(){
        return "##District->"+this.district.toString()+"\n"
                +"NumberOfTrees->"+this.numberOfTrees.toString()+"\n"
                +"bestDistrict->"+bestDistrict+"\n"
                +"bestNumberOfTrees->"+bestNumbersOfTrees+"##\n";
    }
    public static void update(String distr, int nb){
        bestDistrict = distr;
        bestNumbersOfTrees = nb;
    }
    public IntWritable getNumberOfTrees() {
        return numberOfTrees;
    }
    public void setNumberOfTrees(IntWritable numberOfTrees) {
        this.numberOfTrees = numberOfTrees;
    }
    public Text getDistrict() {
        return district;
    }
    public void setDistrict(Text district) {
        this.district = district;
    }
    public static String getBestDistrict() {
        return bestDistrict;
    }
    public static void setBestDistrict(String bestDistrict) {
        bestDistrict = bestDistrict;
    }
    public static int getBestNumbersOfTrees() {
        return bestNumbersOfTrees;
    }
    public static void setBestNumbersOfTrees(int bestNumbersOfTrees) {
        bestNumbersOfTrees = bestNumbersOfTrees;
    }
}