package se.iths.labborationer;

import java.util.Arrays;
import java.util.Scanner;

public class Labboration1 {

    static ElectricData[] electricPrices = new ElectricData[24];
    static int timeAtLowest;
    static int timeAtHighest;
    static int lowestPrice = Integer.MAX_VALUE;
    static int highestPrice = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        start(scanner);

    }

    //startar upp menyn och har funktionaliteten för menyn.
    public static void start(Scanner scanner) {

        boolean loop = true;

        while (loop) {
            greeting();
            String input = scanner.nextLine();

            loop = menuOptions(loop, input);
        }

    }

    private static boolean menuOptions(boolean loop, String input) {
        switch (input) {
            case "1" -> autoAddElectricPrices();  //inmatning(scanner); för manuell inmatning
            case "2" -> printHighestLowestAndAverage(electricPrices);
            case "3" -> printSortedArray(sortArray(electricPrices));
            case "4" -> calculateCheapest4Hours();
            case "5" -> createAndPrintGraph();
            case "e" -> loop = false;
        }
        return loop;
    }

    public static void autoAddElectricPrices() {
        for (int i = 0; i < 24; i++) {

            int priceInput = (int) (Math.random() * 520) + 1;
            electricPrices[i] = new ElectricData(i, priceInput);
        }
        getHighestAndLowestPrices(electricPrices);
    }

    //printar ut menyn för användaren.
    private static void greeting() {
        System.out.println("Elpriser\n" +
                "========\n" +
                "1. Inmatning\n" +
                "2. Min, Max och Medel\n" +
                "3. Sortera\n" +
                "4. Bästa Laddningstid (4h)\n" +
                "e. Avsluta");
    }
    //printar ut vad priset är mellan de olika klockslagen.

    public static void inputPricesToArray(Scanner scanner) {

        electricPrices = new ElectricData[24];
        addPricesToArray(scanner);
    }


    // Metod för alternativ 1 i menyn, frågar efter elpris och sparar det i en array.
    //lägger till pris/tid objekten till en array.
    private static void addPricesToArray(Scanner scanner) {
        for (int i = 0; i < 24; i++) {
            setPricesToArray(scanner, i);
        }
        getHighestAndLowestPrices(electricPrices);
    }


    //printar ut tiden när användaren frågas efter elpriser under dygnet.
    private static void askPriceAndPrintTime(int i) {

        System.out.println("Vad var priset mellan kl " + printHour(i) + "?");
    }

    private static void setPricesToArray(Scanner scanner, int i) {
        askPriceAndPrintTime(i);
        int priceInput = scanner.nextInt();
        electricPrices[i] = new ElectricData(i, priceInput);
    }

    //letar reda på min, och max pris, samt räknar ut dagens medel.

    public static void printHighestLowestAndAverage(ElectricData[] array) {


        int averagePrice = averagePricePerDay(array);
        printHighestLowestAverage(highestPrice, timeAtHighest, timeAtLowest, averagePrice);
    }

    private static void getHighestAndLowestPrices(ElectricData[] array) {
        getHighestPriceOfDay(array);
        getLowestPriceOfDay(array);
    }

    private static int getPrice(ElectricData[] electricPrices, int i) {
        return electricPrices[i].getPrice();
    }

    //räknar ut vilket det lägsta priset under dagen var. Samt sparar vilken tid det inträffade.
    private static void getLowestPriceOfDay(ElectricData[] array) {
        for (int i = 0; i < array.length; i++) {
            if (getPrice(array, i) < lowestPrice) {
                lowestPrice = getPrice(array, i);
                timeAtLowest = i;
            }
        }
    }

    //räknar ut vilket det högsta priset under dagen var. Samt sparar vilken tid det inträffade.
    private static void getHighestPriceOfDay(ElectricData[] array) {

        for (int i = 0; i < array.length; i++) {
            if (getPrice(array, i) > highestPrice) {
                highestPrice = getPrice(array, i);
                timeAtHighest = i;

            }
        }
    }


    //printar ut vilket pris som var högst, lägst samt medelpriset under dygnet, vilka timmar som de inträffar på.
    private static void printHighestLowestAverage(int highestPrice, int timeAtHighest, int timeAtLowest, int averagePrice) {
        System.out.println("Det högsta priset på dygnet är " + highestPrice + " och infaller kl " + timeAtHighest);
        System.out.println("Det lägsta priset på dygnet är " + lowestPrice + " och infaller kl " + timeAtLowest);
        System.out.println("Medel priset var " + averagePrice + " kr under dygnet.");
    }


    //räknar ut vad medelpriset var under dagen.
    private static int averagePricePerDay(ElectricData[] array) {
        int averagePriceHelper = 0;
        for (int i = 0; i < 24; i++) {
            averagePriceHelper = averagePriceHelper + getPrice(array, i);
        }

        int averagePrice = averagePriceHelper / array.length;

        return averagePrice;
    }


    //Skapar en ny array och sorterar på billigaste priset.

    private static ElectricData[] sortArray(ElectricData[] array) {
        ElectricData[] sortedArray = Arrays.copyOf(array, 24);

        ElectricData priceHelper;

        for (int i = 0; i < sortedArray.length; i++) {
            for (int j = 0; j < sortedArray.length - 1; j++) {
                if (getPrice(sortedArray, j) > getPrice(sortedArray, j + 1)) {
                    priceHelper = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = priceHelper;

                }
            }
        }


        return sortedArray;
    }
    //printar ut den sorterade arrayn med billigaste priset först.

    private static void printSortedArray(ElectricData[] array) {
        for (int i = 0; i < array.length; i++) {

            System.out.println(printHour(array[i].getTime()) + " " + getPrice(array, i) + " öre");
        }
    }

    private static void calculateCheapest4Hours() {
        int fourHourPrice;
        double helper = Integer.MAX_VALUE;
        int timeToStartCharge = 0;
        for (int i = 0; i < electricPrices.length - 4; i++) {
            fourHourPrice = getPrice(electricPrices, i) + getPrice(electricPrices, i + 1) + getPrice(electricPrices, i + 2) + getPrice(electricPrices, i + 4);
            if (fourHourPrice < helper) {
                helper = fourHourPrice;
                timeToStartCharge = electricPrices[i].getTime();
            }

        }

        System.out.println("Mellan klockan " + electricPrices[timeToStartCharge].getTime() + " och " + electricPrices[timeToStartCharge + 4].getTime() + " är det billigast att ladda. Medelpriset under de fyra timmarna är " + (helper / 4));
    }


    //printar ut tiden i rätt format. T.ex om i är 5 printas 05-06 ut
    public static String printHour(int i) {
        int timeHelper = i + 1;

        if (i < 9)
            return "0" + i + "-0" + timeHelper;
        else if (i < 10)
            return "0" + i + "-" + timeHelper;
        else
            return i + "-" + timeHelper;


    }

    public static String printOneHour(int i) {

        if (i < 9)
            return "0" + i;
        else if (i < 10)
            return "0" + i;
        else
            return String.valueOf(i);


    }


    public static void createAndPrintGraph() {

        String[][] visualRep = new String[8][26];

        createGraphBody(visualRep);
        printVisualGraph(visualRep);

    }

    private static void createGraphBody(String[][] visualRep) {
        int numberOfWhiteSpaces = String.valueOf(highestPrice).length();
        int whiteSpaceHelper = String.valueOf(lowestPrice).length();


        visualRep[0][0] = String.valueOf(highestPrice);
        visualRep[6][0] = String.valueOf(lowestPrice);

        createValuesInGraph(visualRep);
        addVerticalBorderToGraph(visualRep, numberOfWhiteSpaces, whiteSpaceHelper);
        addHorizontalBorderToGraph(visualRep);
        addHoursToGraph(visualRep);


    }

    private static void addVerticalBorderToGraph(String[][] visualRep, int numberOfWhiteSpaces, int whiteSpaceHelper) {
        addVerticalWhiteSpaceAndNumberToGraph(visualRep, numberOfWhiteSpaces, whiteSpaceHelper);

        addPipeToVerticalBorder(visualRep);
    }

    private static void addPipeToVerticalBorder(String[][] visualRep) {
        for (int i = 0; i < 8; i++) {

            visualRep[i][1] = "|";
        }
    }

    private static void addVerticalWhiteSpaceAndNumberToGraph(String[][] visualRep, int numberOfWhiteSpaces, int whiteSpaceHelper) {
        for (int i = 0; i < 8; i++) {

            visualRep[i][0] = printWhiteSpaces(numberOfWhiteSpaces);
            if (i == 0)
                visualRep[i][0] = String.valueOf(highestPrice);

            if (i == 5)
                visualRep[i][0] = printWhiteSpaces(numberOfWhiteSpaces - whiteSpaceHelper) + lowestPrice;


        }
    }

    private static void addHorizontalBorderToGraph(String[][] visualRep) {
        for (int i = 6; i < 7; i++) {
            for (int j = 2; j < 26; j++) {
                visualRep[i][j] = "---";
            }
        }
    }

    private static void addHoursToGraph(String[][] visualRep) {
        int k = 2;
        for (int i = 0; i < 24; i++) {


            visualRep[7][k] = printOneHour(i) + " ";
            k++;

        }
    }

    private static void createValuesInGraph(String[][] visualRep) {
        double priceCheck = (double) highestPrice / 6;
        int divisionHelper = 6;

        for (int i = 0; i < 6; i++) {
            for (int j = 2; j < 26; j++) {

                addPriceToGraph(visualRep, priceCheck, divisionHelper, i, j);

                printPriceIfLastRow(visualRep, i, j);


            }
            divisionHelper--;
        }
    }

    private static void addPriceToGraph(String[][] visualRep, double priceCheck, int divisionHelper, int i, int j) {
        if (electricPrices[j - 2].getPrice() >= (priceCheck * divisionHelper)) {
            visualRep[i][j] = " * ";
        } else {
            visualRep[i][j] = "   ";
        }
    }

    private static void printPriceIfLastRow(String[][] visualRep, int i, int j) {
        if (i == 5) {
            if (electricPrices[j - 2].getPrice() >= lowestPrice) {
                visualRep[i][j] = " * ";
            } else {
                visualRep[i][j] = "   ";
            }
        }
    }

    private static void printVisualGraph(String[][] visualRep) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 26; j++) {
                System.out.print(visualRep[i][j]);
            }
            System.out.println("");
        }
    }

    private static String printWhiteSpaces(int numberOfWhiteSpaces) {
        String whiteSpaces = "";
        for (int j = 0; j < numberOfWhiteSpaces; j++) {
            whiteSpaces = whiteSpaces + " ";
        }
        return whiteSpaces;
    }


}