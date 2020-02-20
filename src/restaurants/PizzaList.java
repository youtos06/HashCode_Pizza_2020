package restaurants;

import java.util.HashMap;


// pizza list we can create a pizza object for a better conception but we are also trying to get a faster acces
public class PizzaList {


    private HashMap<Integer, Integer> twenyPizzaList = new HashMap<Integer, Integer>();


    public PizzaList(HashMap<Integer, Integer> twenyPizzaList) {
        this.twenyPizzaList = twenyPizzaList;
    }

    public PizzaList(){
    }


    //add a new pizza to list while pizza's included number is less than 20
    public boolean addNewPizza(Integer key, Integer slices) {
        if (this.twenyPizzaList.size() < 20) {
            this.twenyPizzaList.put(key, slices);
            return true;
        }
        return false;
    }


    public boolean removePizza(Integer key) {
        if (this.twenyPizzaList.containsKey(key)) {
            this.twenyPizzaList.remove(key);
            return true;
        }
        return false;
    }


    // return max of slcies inside a list of pizza
    public Integer getMaxSlicesOfPizzaList() {
        int lengthOfPizzaList = this.twenyPizzaList.size();
        if (lengthOfPizzaList > 0) {
            return this.twenyPizzaList.get(lengthOfPizzaList - 1);
        }
        return -1;
    }

    //return key of min clises inside a list of pizza
    public Integer getMinSlicesOfPizzaList() {
        if (this.twenyPizzaList.size() > 0) {
            return this.twenyPizzaList.get(0);
        }
        return -1;
    }

    //method accessed if value of slices between max and min slices in list and return key
    public Integer getPizzaWithCloserSlices(Integer searchedSlices) {
        Integer lengthOfPizzaList = this.twenyPizzaList.size();
        Integer bigger = -1;
        Integer smaller = -1;
        boolean foundTheSlicesKey = false;
        while (0 < lengthOfPizzaList) {
            Integer slicesOfIndexedPizza = this.twenyPizzaList.get(lengthOfPizzaList);
            if (slicesOfIndexedPizza.equals(searchedSlices)) {
                foundTheSlicesKey = true;
                break;
            } else if (slicesOfIndexedPizza > searchedSlices) {
                bigger = lengthOfPizzaList;
            } else if (slicesOfIndexedPizza < searchedSlices) {
                smaller = lengthOfPizzaList;
                break;
            }
            lengthOfPizzaList--;
        }
        if (foundTheSlicesKey) {
            return lengthOfPizzaList;
        }
        if ((searchedSlices - this.twenyPizzaList.get(smaller)) > (this.twenyPizzaList.get(bigger) - searchedSlices)) {
            return bigger;
        }
        return smaller;
    }

}
