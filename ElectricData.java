package se.iths.labborationer;

public class ElectricData {
    int time;
    int electricPrice;

    public ElectricData(int time, int price){
        this.time = time;
        this.electricPrice = price;

    }

    public int getTime(){

        return this.time;

    }


    public int getPrice(){
        return this.electricPrice;
    }

    public String toString(){
        return "Mellan kl " + this.time + " är priset: " + this.electricPrice;
    }
}
