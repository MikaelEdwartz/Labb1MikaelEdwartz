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


    public void setTime(int time){
        this.time = time;
    }
    public void setElectricPrice(int price){
        this.electricPrice = price;
    }

    public int getPrice(){
        return this.electricPrice;
    }

    public String toString(){
        return "Mellan kl " + this.time + " är priset: " + this.electricPrice;
    }
}
