package restaurants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Restaurant {
    public PizzaMenu pizzaMenu;
    public Command command;


    public Restaurant(File file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder numberInString = new StringBuilder();
        int i = br.read();
        while (true) {
            if ((char) i == ' ') {
                //System.out.println(Integer.valueOf(numberInString.toString()));
                this.command = new Command(Integer.valueOf(numberInString.toString()));
                numberInString = new StringBuilder();
            } else if ((char) i == '\n') {
                //System.out.println(Integer.valueOf(numberInString.toString()));
                this.pizzaMenu = new PizzaMenu(Integer.valueOf(numberInString.toString()));
                numberInString = new StringBuilder();
                break;
            } else {
                //System.out.println(Integer.valueOf(numberInString.toString()));
                numberInString.append((char) i);

            }
            i = br.read();

        }
        Integer pizzaId = 0;
        while (true) {

            i = br.read();
            if (i == -1) {
                if (!numberInString.toString().equals("")) {
                    this.pizzaMenu.addPizzaToMenu(pizzaId, Integer.valueOf(numberInString.toString()));
                    //pizzaId = pizzaId+1;
                    //break;
                }

                break;
            } else if ((char) i == '\n') {
                //System.out.println(Integer.valueOf(numberInString.toString()));
                this.pizzaMenu.addPizzaToMenu(pizzaId, Integer.valueOf(numberInString.toString()));
                numberInString = new StringBuilder();
                pizzaId = pizzaId + 1;
            } else if ((char) i == ' ') {
                //System.out.println(Integer.valueOf(numberInString.toString()));
                this.pizzaMenu.addPizzaToMenu(pizzaId, Integer.valueOf(numberInString.toString()));
                numberInString = new StringBuilder();
                pizzaId = pizzaId + 1;
            } else {
                numberInString.append((char) i);
            }
        }
        br.close();
    }


    public void buildSolution() {
        Integer restOfOperations = 0;
        while (this.command.sizeOfDevRest() > 0) {
            Integer pivotOfDevRest = this.command.getLastElementRest();

            if (pivotOfDevRest.compareTo(this.pizzaMenu.getSlicesOfBiggestPizza() * 2) > 0) {
                this.command.devLastAddedRestIntoTwo(pivotOfDevRest);
            } else {

                //System.out.print(pivotOfDevRest);
                Integer[] closerElementToPivot = this.pizzaMenu.getBestFitPizza(pivotOfDevRest);
                //System.out.print(closerElementToPivot[1]);
                this.command.addNewPizzaToCommand(closerElementToPivot[0], closerElementToPivot[1]);

                restOfOperations = pivotOfDevRest - closerElementToPivot[1];

                if (!restOfOperations.equals(0)) {

                    if (!command.addRestOfOperationIntoLastElement(restOfOperations) && (restOfOperations >= this.pizzaMenu.getSmallerPizza())) {
                        //System.out.println(restOfOperations);
                        closerElementToPivot = this.pizzaMenu.getBestFitPizza(restOfOperations);


                        if (closerElementToPivot != null) {
                            this.command.addNewPizzaToCommand(closerElementToPivot[0], closerElementToPivot[1]);
                            restOfOperations = restOfOperations - closerElementToPivot[1];
                        }


                    }

                }


            }


        }
        // a little correction to result sadly no time to fix
        if (restOfOperations != 0) {
            /*
            System.out.println(restOfOperations);
            Integer littleCloser = this.command.removeFirstElementFromCommand();
            littleCloser = littleCloser + restOfOperations;
            Integer[] closerElementToPivot = this.pizzaMenu.getBestFitPizza(littleCloser);
            //System.out.print(" "+closerElementToPivot[1]);
            //restOfOperations = littleCloser - closerElementToPivot[1];
            this.command.addNewPizzaToCommand(closerElementToPivot[0], closerElementToPivot[1]);*/

        }


    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "pizzaMenu=" + this.pizzaMenu.toString() +
                ", command=" + this.command.toString() +
                '}';
    }

    public void buildOutSolution(String fileName) throws IOException {
        this.command.buildOutFile(fileName);
    }



}
