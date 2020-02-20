import restaurants.Restaurant;

import java.io.FileWriter;
import java.io.IOException;

public class main {


    public static void main(String args[]) throws Exception {
        responseBuild("a_example.in");
        responseBuild("b_small.in");
        responseBuild("c_medium.in");
        responseBuild("d_quite_big.in");
        responseBuild("e_also_big.in");
    }






    public static void responseBuild(String fileName) throws Exception{
        Restaurant restaurant = new Restaurant(fileName);
        restaurant.pizzaMenu.allPizzaWereAdded();
        restaurant.buildSolution();
        //restaurant.toString();
        restaurant.buildOutSolution(fileName);
    }

}
