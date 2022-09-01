package se.iths.labborationer;

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
                printPricePerHour();

            if (Integer.parseInt(input) == 4)
                printPricePerHour();



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
            electricPrices[i] = new ElectricData(i, priceInput);

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
}


