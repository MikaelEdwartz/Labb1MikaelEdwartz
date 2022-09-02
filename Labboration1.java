package se.iths.labborationer;

import java.util.Arrays;
import java.util.Scanner;

public class Labboration1 {

    static ElectricData[] electricPrices;
    static int timeAtLowest;
    static int timeAtHighest;


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

            switch(input) {
                case "1" -> inmatning(scanner);
                case "2" -> minMaxMedel(electricPrices);
                case "3" -> printSortedArray(sortArray(electricPrices));
                case "4" -> calculateCheapest4Hours();
                case "5" -> System.out.println("VG");
                case "g" -> loop = false;


/*            if (Integer.parseInt(input) == 1)
                inmatning(scanner);

            if (Integer.parseInt(input) == 2)
                minMaxMedel(electricPrices);

            if (Integer.parseInt(input) == 3)
                printSortedArray(sortedArray(electricPrices));

            if (Integer.parseInt(input) == 4)
                calculateCheapest4Hours();
*/

            }
        }

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

    private static void printPricePerHour() {
        for (int i = 0; i < electricPrices.length; i++) {
            System.out.println("Mellan klockan " + printHour(i) + " är priset " + getPrice(electricPrices, i) + " kr per kW/h.");

        }
    }

    private static int getPrice(ElectricData[] electricPrices, int i) {
        return electricPrices[i].getPrice();
    }
    public static ElectricData[] inmatning(Scanner scanner) {

        electricPrices = new ElectricData[24];
        addPricesToArray(scanner);
        return electricPrices;
    }


    // Metod för alternativ 1 i menyn, frågar efter elpris och sparar det i en array.
    //lägger till pris/tid objekten till en array.
    private static void addPricesToArray(Scanner scanner) {
        for (int i = 0; i < 24; i++) {

            askPriceAndPrintTime(i);
            int priceInput = scanner.nextInt();
            electricPrices[i] = new ElectricData(i, priceInput, i);

        }
    }
    //printar ut tiden när användaren frågas efter elpriser under dygnet.
    private static void askPriceAndPrintTime(int i) {

        System.out.println("Vad var priset mellan kl " + printHour(i) + "?");
    }




    //letar reda på min, och max pris, samt räknar ut dagens medel.

    public static void minMaxMedel(ElectricData[] array) {
        int highestPrice = Integer.MIN_VALUE;
        int lowestPrice = Integer.MAX_VALUE;

        for (int i = 0; i < 24; i++) {
            highestPrice = getHighestPrice(array, highestPrice, i);
            lowestPrice = getLowestPrice(array, lowestPrice, i);
        }
        int averagePrice = averagePricePerDay(array);

        printHighestLowestAverage(highestPrice, timeAtHighest, lowestPrice, timeAtLowest, averagePrice);
    }

    //räknar ut vilket det lägsta priset under dagen var. Samt sparar vilken tid det inträffade.
    private static int getLowestPrice(ElectricData[] array, int lowestPrice, int i) {
        if (getPrice(array, i) < lowestPrice) {
            lowestPrice = getPrice(array, i);
            timeAtLowest = i;
        }
        return lowestPrice;
    }

    //räknar ut vilket det högsta priset under dagen var. Samt sparar vilken tid det inträffade.
    private static int getHighestPrice(ElectricData[] array, int highestPrice, int i) {
        if (getPrice(array, i) > highestPrice) {
            highestPrice = getPrice(array, i);
            timeAtHighest = i;
        }
        return highestPrice;
    }


    //printar ut vilket pris som var högst, lägst samt medelpriset under dygnet, vilka timmar som de inträffar på.
    private static void printHighestLowestAverage(int highestPrice, int timeAtHighest, int lowestPrice, int timeAtLowest, int averagePrice) {
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

    private static ElectricData[] sortArray(ElectricData[] array){
        ElectricData[] sortedArray = Arrays.copyOf(array, 24);
        ElectricData priceHelper = new ElectricData(0,0,0);

        for (int i = 0; i < sortedArray.length; i++) {
            for (int j = 0; j < sortedArray.length-1; j++) {
                if(getPrice(sortedArray, j) > getPrice(sortedArray, j + 1)){
                    priceHelper = sortedArray[j];
                    sortedArray[j] = sortedArray[j+1];
                    sortedArray[j+1] = priceHelper;

                }
            }
        }



        return sortedArray;
    }
    //printar ut den sorterade arrayn med billigaste priset först.

    private static void printSortedArray(ElectricData[] array){
        for (int i = 0; i < array.length; i++) {

            System.out.println(printHour(array[i].getTime()) + " " + getPrice(array, i) + " öre");
        }
    }

    private static void calculateCheapest4Hours(){
        int fourHourPrice;
        int helper = Integer.MAX_VALUE;
        int timeToStartCharge = 0;
        for (int i = 0; i < electricPrices.length-4; i++) {
            fourHourPrice = getPrice(electricPrices, i) + getPrice(electricPrices, i + 1) + getPrice(electricPrices, i + 2) + getPrice(electricPrices, i + 4);
            if(fourHourPrice < helper){
                helper = fourHourPrice;
                timeToStartCharge = electricPrices[i].getTime();
            }

        }

        System.out.println("Mellan klockan " + electricPrices[timeToStartCharge].getTime() + " och " + electricPrices[timeToStartCharge+4].getTime() + " är det billigast att ladda. Medelpriset under de fyra timmarna är "+ (helper/4) );
    }








    //printar ut tiden i rätt format. T.ex om i är 5 printas 05-06 ut
    public static String printHour(int i) {
        int timeHelper = i + 1;
        if (i < 9) {
            return "0" + i + "-0" + timeHelper;
        } else if (i < 10) {
            return "0" + i + "-" + timeHelper;
        } else if (i == 10) {
            return i + "-" + timeHelper;
        } else {
            return i + "-" + timeHelper;
        }

    }







}

/*¨
* loopa genom array med electricdata objekt
* electriddataarray[i] är billigare än
*
* */

