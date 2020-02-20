package restaurants;

import java.util.LinkedHashMap;

public class PizzaMenu {
    private Integer numberOfPizzaInMenu;
    private LinkedHashMap<Integer, Integer> pizzaInMenu; // key : pizzaId | ob : pizza slices
    private Integer cielOfPizzaNumber;

    //we can create a pizza class but this time for sake of reaching solution i only worked with linked hash map
    //private Integer stacksOfTenOrLessPizza;


    public Integer getPizza(Integer pizzaId) {
        return this.pizzaInMenu.get(pizzaId);
    }

    public PizzaMenu(Integer numberOfPizzaInMenu) {
        this.numberOfPizzaInMenu = numberOfPizzaInMenu;
        this.pizzaInMenu = new LinkedHashMap<>();
        this.cielOfPizzaNumber = 0;
        //this.stacksOfTenOrLessPizza = (this.numberOfPizzaInMenu)/10;
    }

    @Override
    public String toString() {
        return "PizzaMenu{" +
                "numberOfPizzaInMenu=" + numberOfPizzaInMenu +
                ", pizzaInMenu=" + pizzaInMenu.toString() +
                ", cielOfPizzaNumber=" + cielOfPizzaNumber.toString() +
                '}';
    }

    public int size() {
        return this.pizzaInMenu.size();
    }

    public void addPizzaToMenu(Integer pizzaId, Integer pizzaSlices) {
        //this.numberOfPizzaInMenu++;
        this.pizzaInMenu.put(pizzaId, pizzaSlices);
    }

    public void allPizzaWereAdded() {
        // for faster calculation we search by blocks of 10
        double tempCielPizzaNumber = Math.ceil((float) (this.numberOfPizzaInMenu / 10));
        this.cielOfPizzaNumber = (int) tempCielPizzaNumber;
    }

    public void deletePizzaFromMenu(Integer pizzaId) {
        // no need to verify since we will in main
        //this.numberOfPizzaInMenu--; even if pizza is deleted for this prob we don't wanna break linkedHashMapSearch
        this.pizzaInMenu.remove(pizzaId);
    }

    public Integer getSlicesOfBiggestPizza() {
        Integer maxPizzaId = this.numberOfPizzaInMenu - 1;
        while (!this.pizzaInMenu.containsKey(maxPizzaId)) {
            maxPizzaId--;
        }
        return this.pizzaInMenu.get(maxPizzaId);
    }

    public Integer getBiggerPizza() {
        Integer maxPizzaId = this.numberOfPizzaInMenu - 1;
        while (!this.pizzaInMenu.containsKey(maxPizzaId)) {
            maxPizzaId--;
        }
        return maxPizzaId;
    }

    public Integer getSlicesOfSmallestPizza() {
        Integer maxPizzaId = 0;
        while (!this.pizzaInMenu.containsKey(maxPizzaId)) {
            maxPizzaId++;
        }
        return this.pizzaInMenu.get(maxPizzaId);
    }

    public Integer getSmallerPizza() {
        Integer maxPizzaId = 0;
        while (!this.pizzaInMenu.containsKey(maxPizzaId)) {
            maxPizzaId++;
        }
        return maxPizzaId;
    }

    public Integer getSlicesOfPizza(Integer pizzaId) {
        return this.pizzaInMenu.get(pizzaId);
    }

    // in case index of pizza was deleted
    public Integer getIndexOfCloserSmallerPizza(Integer pizzaId) {
        while (!this.pizzaInMenu.containsKey(pizzaId)) {
            pizzaId--;
            if (pizzaId < 0) {
                return -1;
            }
        }
        return pizzaId;
    }

    // in case index of pizza was deleted
    public Integer getIndexOfCloserBiggerPizza(Integer pizzaId) {
        while (!this.pizzaInMenu.containsKey(pizzaId)) {
            //System.out.println(pizzaId);
            pizzaId++;
            if (pizzaId > this.numberOfPizzaInMenu) {
                return -1;
            }
        }
        return pizzaId;
    }


    public boolean includeSlicesInPizzaBlock(Integer minPizzaId, Integer maxPizzaId, Integer slices) {

        //System.out.println(minPizzaId+" "+maxPizzaId);

        Integer tempCloserSmallerPizza = this.getIndexOfCloserBiggerPizza(minPizzaId); //we go up
        Integer tempCloserBiggerPizza = this.getIndexOfCloserSmallerPizza(maxPizzaId-1); //we go down

        if ((tempCloserBiggerPizza == -1) || (tempCloserSmallerPizza == -1)) {
            return false;
        }


        if ((slices.compareTo(this.pizzaInMenu.get(tempCloserSmallerPizza)) >= 0) && (slices.compareTo(this.pizzaInMenu.get(tempCloserBiggerPizza)) <= 0)) {
            return true;
        }
        return false;
    }

    public boolean isBiggerThanMaxSlicesDouble(Integer slices) {

        if (slices.compareTo(this.getSlicesOfBiggestPizza() * 2) >= 0) {
            return true;
        }
        return false;
    }

    public boolean isSmallerThanMinSlices(Integer slices) {
        if (slices.compareTo(this.getSlicesOfSmallestPizza()) < 0) {
            return true;
        }
        return false;
    }


    public boolean isBiggerThanMaxSlices(Integer slices) {
        if (slices.compareTo(this.getSlicesOfBiggestPizza()) > 0) {
            return true;
        }
        return false;
    }

    //run after boolean isBiggerThanMAxSlicesDouble(slices)
    public Integer findPizzaSegmentWithCloserSlices(Integer slices) {
        //Integer stacksOfTenOrLessPizza = (this.numberOfPizzaInMenu / 10);
        if (this.isSmallerThanMinSlices(slices)) {
            return this.getSmallerPizza() / 10;
        }
        if (this.isBiggerThanMaxSlices(slices)) {
            return this.getBiggerPizza() / 10;
        }

        Integer tempPizzaSegments = this.cielOfPizzaNumber;
        while (tempPizzaSegments > 0) {
            if (this.includeSlicesInPizzaBlock((tempPizzaSegments - 1) * 10, tempPizzaSegments * 10, slices)) {
                return tempPizzaSegments;
            }
            tempPizzaSegments--;
        }

        return 0;
    }

    public Integer findPizzaWithCloserSlices(Integer slices, Integer pizzaSegment) {
        //Integer closerMaxSlicesPizza = -1;
        Integer closerMinSlicesPizza = -1;
        //Integer closerBiggerPizzaId = -1;
        Integer closerSmallerPizzaId = -1;
        pizzaSegment = (pizzaSegment) * 10 ;
        if (slices.compareTo(this.getSlicesOfBiggestPizza()) >= 0) {
            return this.getBiggerPizza();
        }


        if (slices.compareTo(this.getSlicesOfSmallestPizza()) < 0) {
            //System.out.println("-------------");
            return -1;
        }

        for (Integer pizzaId = pizzaSegment; pizzaId.compareTo(pizzaSegment - 10) > 0; pizzaId--) {
            //System.out.println(pizzaId);
            if (this.pizzaInMenu.containsKey(pizzaId)) {
                Integer tempPizzaIdSlices = this.pizzaInMenu.get(pizzaId);
                //System.out.println(tempPizzaIdSlices);
                if (tempPizzaIdSlices.equals(slices)) {
                    return pizzaId;
                } else if (tempPizzaIdSlices.compareTo(slices) > 0) {
                    //System.out.println("-------");
                    /*
                    if (closerMaxSlicesPizza.equals(-1)) {
                        //System.out.println("b");
                        closerMaxSlicesPizza = this.pizzaInMenu.get(pizzaId);
                        closerBiggerPizzaId = pizzaId;
                    } else {
                        if (tempPizzaIdSlices.compareTo(closerMaxSlicesPizza) < 0) {
                            closerMaxSlicesPizza = this.pizzaInMenu.get(pizzaId);
                            closerBiggerPizzaId = pizzaId;
                        }
                    }*/

                } else {

                    if (closerMinSlicesPizza.equals(-1)) {
                        //System.out.println("s");
                        closerMinSlicesPizza = this.pizzaInMenu.get(pizzaId);
                        closerSmallerPizzaId = pizzaId;
                    } else {
                        if (tempPizzaIdSlices.compareTo(closerMinSlicesPizza) > 0) {
                            closerMinSlicesPizza = this.pizzaInMenu.get(pizzaId);
                            closerSmallerPizzaId = pizzaId;
                        }
                    }

                }
            }
        }
        //System.out.println(closerMaxSlicesPizza+ " "+ closerMinSlicesPizza);
        //Integer maxDiff = closerMaxSlicesPizza - slices;
        //Integer minDiff = slices - closerMinSlicesPizza;

        //if (maxDiff.compareTo(minDiff) < 0) {
        //  return closerBiggerPizzaId;
        //} else {
        return closerSmallerPizzaId;
        //}

    }


    public Integer[] getBestFitPizza(Integer slices) {
        Integer tempPizzaSegment = findPizzaSegmentWithCloserSlices(slices);
        //System.out.println(tempPizzaSegment);
        Integer pizzaId = findPizzaWithCloserSlices(slices, tempPizzaSegment);
        //System.out.println(pizzaId);
        if (!pizzaId.equals(-1)) {
            Integer[] pizzaIdAndSlices = new Integer[]{pizzaId, this.pizzaInMenu.get(pizzaId)};
            this.pizzaInMenu.remove(pizzaId);
            return pizzaIdAndSlices;
        }
        return null;
    }


}
