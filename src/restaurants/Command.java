package restaurants;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Command {
    private Integer maxSlices;
    private HashMap<Integer, Integer> pizzaCommand;
    private ArrayList<Integer> slicesDevRest;
    // n = n/2 + n/2 we search for n/2 closer number of slices and store the other n/2 for search later;
    // in case n/2 was bigger than (max slices of biggest pizza)*2 we re do same as before n/2 = n/4 + n/4
    // in case n/2 was same as pizza slices number we command it in case it was bigger we add the rest to next value in slicesDevRest and we keep re-doing as before


    public Command(Integer maxSlices) {
        this.maxSlices = maxSlices;
        this.pizzaCommand = new HashMap<>();
        this.slicesDevRest = new ArrayList<>();
        this.slicesDevRest.add(maxSlices);
    }

    public Integer removeFirstElementFromCommand() {
        Integer key = (Integer) this.pizzaCommand.keySet().toArray()[0];

        return this.pizzaCommand.remove(key);
    }


    public void addNewPizzaToCommand(Integer pizzaId, Integer slices) {
        this.pizzaCommand.put(pizzaId, slices);
    }


    @Override
    public String toString() {
        return "Command{" +
                "maxSlices=" + maxSlices +
                ", pizzaCommand=" + pizzaCommand.toString() +
                ", slicesDevRest=" + slicesDevRest.toString() +
                '}';
    }

    public void addToCommand(Integer pizzaId, Integer slices) {
        this.pizzaCommand.put(pizzaId, slices);
    }

    public Integer getLastElementRest() {
        return this.slicesDevRest.remove(this.slicesDevRest.size() - 1);
    }

    //slicesdevRest
    public Integer getPivot() {
        int sizeOfDevRest = this.slicesDevRest.size();
        if (sizeOfDevRest > 0) {
            return sizeOfDevRest;
        }
        return -1;
    }

    //slicesdevRest
    public void devLastAddedRestIntoTwo() {
        Integer lastElement = this.slicesDevRest.remove(this.slicesDevRest.size() - 1);
        Integer lastElementIntoTwo = lastElement / 2;
        this.slicesDevRest.add(lastElementIntoTwo + lastElement % 2);
        this.slicesDevRest.add(lastElementIntoTwo);
    }

    public void devLastAddedRestIntoTwo(Integer lastElement) {

        Integer lastElementIntoTwo = lastElement / 2;
        this.slicesDevRest.add(lastElementIntoTwo + lastElement % 2);
        this.slicesDevRest.add(lastElementIntoTwo);
    }

    //slicesdevRest
    public boolean addRestOfOperationIntoLastElement(Integer rest) {
        Integer lastElementIndex = this.slicesDevRest.size();
        if (lastElementIndex > 0) {

            this.slicesDevRest.set(lastElementIndex - 1, this.slicesDevRest.get(lastElementIndex - 1) + rest);
            return true;
        }
        return false;
    }

    public boolean addToLastOfDevRest(Integer numberToAdd) {
        int sizeOfDevRest = this.slicesDevRest.size();
        if (sizeOfDevRest > 0) {
            this.slicesDevRest.set(sizeOfDevRest - 1, this.slicesDevRest.get(sizeOfDevRest - 1) + numberToAdd);
            return true;
        } else {
            return false;
        }
    }

    public int sizeOfDevRest() {
        return this.slicesDevRest.size();
    }

    public Integer getNumberOfSlicesInCommand() {
        Integer numberOfSlices = 0;
        for (Integer pizzaId : this.pizzaCommand.keySet()) {
            numberOfSlices = numberOfSlices + this.pizzaCommand.get(pizzaId);
        }
        return numberOfSlices;
    }

    public void buildOutFile(String fileName) throws IOException {
        //System.out.println(this.toString());
        fileName = "src/out/" + fileName;
        String pathOfFile = fileName.replaceFirst("in", "out");
        try {
            FileWriter file = new FileWriter(pathOfFile);
            StringBuilder lineOfSolution = new StringBuilder();
            //System.out.println(this.pizzaCommand.size());
            lineOfSolution.append(this.pizzaCommand.size() + "\n");
            for (Integer key : this.pizzaCommand.keySet()) {
                //System.out.println(lineOfSolution);
                lineOfSolution.append(key.toString()+ " ");
            }

            file.write(lineOfSolution.toString()); // l‘écriture  // fermeture du strem
            file.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

}
