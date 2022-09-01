package se.iths.labborationer;

public class ElectricData {
    int time;
    int electricPrice;
    int originalPosition;

    public ElectricData(int time, int price, int originalPosition){
        this.time = time;
        this.electricPrice = price;
        this.originalPosition = originalPosition;

    }

    public int getTime(){

        return this.time;

    }

    public int getPosition(){
        return this.originalPosition;
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
