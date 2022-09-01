package se.iths.labborationer;

import java.util.Arrays;
import java.util.Scanner;

public class Labboration1 {

    static ElectricData[] electricPrices;
    static int timeAtLowest;
    static int timeAtHighest;


    public static void main(String[] args) {

        start();


    }

    //startar upp menyn och har funktionaliteten för menyn.
    public static void start() {
        Scanner scanner = new Scanner(System.in);



        while (true) {
            greeting();

            String input = scanner.nextLine();
            if (Integer.parseInt(input) == 1)
                inmatning();

            if (Integer.parseInt(input) == 2)
                minMaxMedel(electricPrices);

            if (Integer.parseInt(input) == 3)
                printSortedArray(sortedArray(electricPrices));
            if (Integer.parseInt(input) == 4)
                calculateCheapest4Hours();


        }

    }

    //printar ut vad priset är mellan de olika klockslagen.
    private static void printPricePerHour() {
        for (int i = 0; i < electricPrices.length; i++) {
            System.out.println("Mellan klockan " + printTimes(i) + " är priset " + electricPrices[i].getPrice() + " kr per kW/h.");

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

    // Metod för alternativ 1 i menyn, frågar efter elpris och sparar det i en array.
    public static ElectricData[] inmatning() {
        Scanner scanner = new Scanner(System.in);

        electricPrices = new ElectricData[24];

        addPricesToArray(scanner);
        return electricPrices;
    }

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
        int timeHelper = i + 1;

        System.out.println("Vad var priset mellan kl " + printTimes(i) + "?");


    }

    //printar ut tiden i rätt format.
    public static String printTimes(int i) {
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
            averagePriceHelper = averagePriceHelper + array[i].getPrice();
        }
        int averagePrice = averagePriceHelper / array.length;
        return averagePrice;
    }

    //räknar ut vilket det lägsta priset under dagen var. Samt sparar vilken tid det inträffade.
    private static int getLowestPrice(ElectricData[] array, int lowestPrice, int i) {
        if (array[i].getPrice() < lowestPrice) {
            lowestPrice = array[i].getPrice();
            timeAtLowest = i;
        }
        return lowestPrice;
    }

    //räknar ut vilket det högsta priset under dagen var. Samt sparar vilken tid det inträffade.
    private static int getHighestPrice(ElectricData[] array, int highestPrice, int i) {
        if (array[i].getPrice() > highestPrice) {
            highestPrice = array[i].getPrice();
            timeAtHighest = i;
        }
        return highestPrice;
    }

    //Skapar en ny array och sorterar på billigaste priset.
    private static ElectricData[] sortedArray(ElectricData[] array){
        ElectricData[] sortedArray = Arrays.copyOf(array, 24);
        ElectricData priceHelper = new ElectricData(0,0,0);

        for (int i = 0; i < sortedArray.length; i++) {
            for (int j = 0; j < sortedArray.length-1; j++) {
                if(sortedArray[j].getPrice() > sortedArray[j+1].getPrice()){
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

            System.out.println(printTimes(array[i].getPosition()) + " " + array[i].getPrice() + " öre");
        }
    }

    private static void calculateCheapest4Hours(){
        int fourHourPrice = 0;
        int helper = Integer.MAX_VALUE;
        int timeToStartCharge = 0;
        for (int i = 0; i < electricPrices.length-4; i++) {
            fourHourPrice = electricPrices[i].getPrice() + electricPrices[i+1].getPrice() + electricPrices[i+2].getPrice() + electricPrices[i+4].getPrice();
            if(fourHourPrice < helper){
                helper = fourHourPrice;
                timeToStartCharge = electricPrices[i].getPosition();
            }

        }

        System.out.println("Mellan klockan " + electricPrices[timeToStartCharge+1].getTime() + " och " + electricPrices[timeToStartCharge+5].getTime() + " är det billigast att ladda. Medelpriset under de fyra timmarna är "+ (helper/4) );
    }


}

/*¨
* loopa genom array med electricdata objekt
* electriddataarray[i] är billigare än
*
* */

