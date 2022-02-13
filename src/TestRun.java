import restaurants.Restaurant;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestRun {

    public static void main(String args[]) throws FileNotFoundException {
        List<String> fileNames = new ArrayList<>();
        fileNames.add("a_example");
        fileNames.add("b_small");
        fileNames.add("c_medium");
        fileNames.add("d_quite_big");
        fileNames.add("e_also_big");
        fileNames.forEach(TestRun::testCase);
    }

    public static void testCase(String filename) {
        String pathIn = "src/in/" + filename+".in";
        File fileIn = new File(pathIn);
        long start1 = System.currentTimeMillis();
        try {
            callForSlices(fileIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end1 = System.currentTimeMillis();
        long time = (end1-start1);
        System.out.println("Elapsed Time in mili seconds for file +'"+filename+"' : "+ (end1-start1));
        Scanner scannerIn = null;
        try {
            scannerIn = new Scanner(fileIn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long people = scannerIn.nextLong();
        final int pizzas = scannerIn.nextInt();
        long[] pizzaTypes = new long[pizzas];
        int pointer = 0;
        while (scannerIn.hasNextLong()){
            pizzaTypes[pointer] = scannerIn.nextLong();
            pointer++;
        }

        String pathOut = "src/out/" + filename+".out";
        File fileOut = new File(pathOut);
        Scanner scannerOut = null;
        try {
            scannerOut = new Scanner(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long pizzasCalled = scannerOut.nextInt();
        long sum = 0;
        for (int i = 0 ; i < pizzasCalled ; i++){
            sum  = sum + pizzaTypes[scannerOut.nextInt()];
        }
        System.out.println("For File Name : "+filename+" You requested : "+sum+" for "+people+" people");
        if (sum < people) {
            System.out.println("INVALID FOR NOT ENOUGH SLICES");
        }

    }

    public static void callForSlices(File file) throws Exception {
        Restaurant restaurant = new Restaurant(file);
        restaurant.pizzaMenu.allPizzaWereAdded();
        restaurant.buildSolution();
        //restaurant.toString();
        restaurant.buildOutSolution(file.getName());
    }
}
