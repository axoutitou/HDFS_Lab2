package com.opstty;

import com.opstty.job.DistrictMostTrees;
import com.opstty.job.OldestTree;
import org.apache.hadoop.util.ProgramDriver;

public class AppDriver {
    public static void main(String argv[]) {
        int exitCode = -1;
        ProgramDriver programDriver = new ProgramDriver();

        switch(argv[0]){
            case "oldestTree":
                try {
                    programDriver.addClass("oldestTree", OldestTree.class,
                            "A map/reduce program concerning districts of Paris.");

                    exitCode = programDriver.run(argv);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            case "districtMostTrees":
                try {
                    programDriver.addClass("districtMostTrees", DistrictMostTrees.class,
                            "A map/reduce program concerning districts of Paris.");

                    exitCode = programDriver.run(argv);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            default:
                System.out.println("the program deos not exist. Application will quit.");
        }
        System.exit(exitCode);
    }

}
