
package se.iths.labborationer.labb1MikaelEdwartz;

public class ElectricPriceTime {
    private final int time;
    private final int electricPrice;

    public ElectricPriceTime(int time, int price){
        this.time = time;
        this.electricPrice = price;
    }

    public int getTime(){
        return this.time;
    }

    public int getPrice(){
        return this.electricPrice;
    }
}
