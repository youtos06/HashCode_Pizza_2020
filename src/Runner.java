import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Runner {


    public static void counterFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        long people = scanner.nextLong();
        final int pizzas = scanner.nextInt();
        int pointer = 0;
        long[] pizzaTypes = new long[pizzas];
        while (scanner.hasNextLong()){
            pizzaTypes[pointer] = scanner.nextLong();
            //System.out.println("pointer : "+pointer+ " value : "+pizzaTypes[pointer]);
            pointer++;

        }
        HashMap<Integer,Long> selectedPizza = new HashMap<>();
        fillMap(people,selectedPizza,pizzaTypes,pizzas);
        int sum = 0;
        for (Integer pivot : selectedPizza.keySet()){
            sum += selectedPizza.get(pivot);
            System.out.println("pivot "+pivot+" : "+selectedPizza.get(pivot));

        }
        System.out.println("RESULT  : "+sum);
    }

    public static int getMin( HashMap<Integer,Long> selected ,int pizzas,int min){
        if (min < pizzas-1) min++;
        while (selected.containsKey(min) && min < pizzas){
            min++;
        }
        return min;
    }



    public static int getMax( HashMap<Integer,Long> selected ,int pizzas,int max){
        max = max-1;
        while (selected.containsKey(max)){
            max--;
        }
        return max;
    }

    public static void fillMap(long people , HashMap<Integer,Long> selected ,  long[] pizzaTypes ,int pizzas){
        int pivotMin = getMin(selected,pizzas,-1);
        int pivotMax = getMax(selected,pizzas,pizzas);


        long sum = pizzaTypes[pivotMin] + pizzaTypes[pivotMax];

        long sumCloser = sum;
        int pivotMinOfCloser = pivotMin;
        int pivotMaxOfCloser = pivotMax;

        while (pivotMin < pivotMax && sum != people){
            if ( (people - sum) < (people - sumCloser)){
                sumCloser = sum;
                pivotMinOfCloser = pivotMin;
                pivotMaxOfCloser = pivotMax;
            }
            if (pizzaTypes[pivotMin] == people){
                selected.put(pivotMin,pizzaTypes[pivotMin]);
                return;
            }
            if (pizzaTypes[pivotMax] == people){
                selected.put(pivotMax,pizzaTypes[pivotMax]);
                return;
            }

            if ( sum < people ){
                pivotMin = getMin(selected ,pizzas,pivotMin);
            }
            if (sum > people){
                pivotMax = getMax(selected,pizzas,pivotMax);
            }
            sum = pizzaTypes[pivotMin] + pizzaTypes[pivotMax];

        }

        if (people - pizzaTypes[pivotMinOfCloser]<0){
            selected.put(pivotMinOfCloser,pizzaTypes[pivotMinOfCloser]);
        }else if (people - pizzaTypes[pivotMaxOfCloser]<0){
            selected.put(pivotMaxOfCloser,pizzaTypes[pivotMaxOfCloser]);
        }else {
            selected.put(pivotMinOfCloser,pizzaTypes[pivotMinOfCloser]);
            selected.put(pivotMaxOfCloser,pizzaTypes[pivotMaxOfCloser]);
        }

        if (people - sum > 0){
            fillMap(people - sumCloser,selected,pizzaTypes,pizzas);
        }
    }

    public static void writeSolution(HashMap<Integer,Long> selected){

    }

}
