/*
Programmer Name: Steven Nak
Date: October 6, 2016
Description: Demonstrating OOP through a car dealership with Inheritences 
*/
public class TestDealer {

 public static void main(String[] args) {
  CarDealerShip dealer = new CarDealerShip();
  dealer.addNewCar("GM Buick Century", 2004, 20000, "Silver");
  dealer.addUsedCar("Toyota Corolla", 1999, 9000, true);
  dealer.addNewCar("Honda Civic", 2004, 15000, "Green");
  dealer.addNewCar("BMW 320i", 2004, 35000, "Black");
  dealer.addUsedCar("Toyota Sienna", 2000, 11000, false);
  System.out.println(dealer.printReport());
  System.out.println("***************************************");
  System.out.println(dealer.printAllCarsWithSellingPriceBelow(10000));
  System.out.println("****************************************");
  System.out.println(dealer.printAllCarsOfColor("Green"));
 }

 static class CarDealerShip implements Dealer {
  private final int max = 80;
  private Car[] car;
  private int num1 = 0;
  private String colorReport, aReport, bReport;
  private int number;


  //public CarDealerShip(){
  //cars= new Car[max];
  //num1=0;
  //}


  public CarDealerShip() {
   car = new Car[max];
   if (num1 == 80)
    System.out.println("FULL");
  }
  public void addNewCar(String model, int year, int price, String color) {
   car[num1] = new NewCar(model, year, price, color);
   num1++;

  }
  public void addUsedCar(String model, int year, int price, boolean rusty) {
   car[num1] = new UsedCar(model, year, price, rusty);
   num1++;

  }

   public String printReport() {
   String report = "";
     for( int i=0; i<num1; i++){
       report += "Car " + (i+1) + ":" + car[i].toString() + "\n";
     }
   return report;
  }

  public String printAllCarsWithSellingPriceBelow(int price) {
   String bReport="";
   for (int i = 0; i < num1; i++) {
    if (car[i].getPrice() < price) {
     number = i + 1;
     bReport = "Car" + i + car[i].toString() + "\n";

    }
   }
   return bReport;
  }
  public String printAllCarsOfColor(String color) {
   String colorReport="";
    for(int x=0; x<num1; x++){
      if(car[x] instanceof NewCar){
       NewCar newc = (NewCar)car[x];
        if (newc.color.equals(color)){
          colorReport +="Car " + x + ":" + car[x].toString() + "\n";
        }
      }
    }
   return colorReport;
  }
 }
 static class Car {
  private String model;
  private int price, year;

  // create the method

  public Car(String aModel, int aYear, int aPrice) {
   model = aModel;
   year = aYear;
   price = aPrice;

  }
  public void setModel(String aModel) {
   model = aModel;
  }
  public String getModel() {
   return model;
  }

  public void setPrice(int aPrice) {
   price = aPrice;
  }

  public double getPrice() {
   return price;
  }
  public void setYear(int aYear) {
   year = aYear;
  }
  public int getYear() {
   return year;
  }

  public String toString() {
   return "\n Model:" + getModel() + "\n Price:" + getPrice() + "\n Year:" + getYear();
  }

 }
 static class NewCar extends Car {
  //creates class that inherits the car class and all its parameters.
  private String color;
  private int num1 = 1;

  public NewCar(String aModel, int aYear, int aPrice, String aColor) {
   super(aModel, aYear, aPrice);
   color = aColor;
  }

  public void setColor(String aColor) {
   color = aColor;
  }
  public String getColor() {
   return color;
  }
  public String toString() {
   return "New car\nModel: " + getModel() + "\nPrice:" + getPrice() + "\nYear:" + getYear() + "\nColor: " + color + "\nSelling Price: " + getPrice() + "\n";
  }
 }
 static class UsedCar extends Car {
  //creates class that inherits car class and all its parameters
  private boolean rusty;
  private static final double discount = 500.00;
  public UsedCar(String aModel, int aPrice, int aYear, boolean aRusty) {
   super(aModel, aPrice, aYear);
   rusty = aRusty;
  }
  public boolean getRusty() {
   return rusty;
    

  }
  public void setRusty(boolean aRusty) {
   rusty = aRusty;
  }
  public double getSellingPrice() {
   if (rusty)
    return getPrice() - discount;
   else
    return getPrice();
  }

  public String toString() {
   return " Used Car\nModel:" + getModel() + "\nPrice:" + getPrice() + "\nYear:" + getYear() + "\nCondition:" + rusty + "\nSelling Price: " + getSellingPrice() + "\n";

  }
 }
 interface Dealer {
  void addNewCar(String model, int year, int price, String color);
  void addUsedCar(String model, int year, int price, boolean rusty);
  String printReport();
  String printAllCarsWithSellingPriceBelow(int price);
  String printAllCarsOfColor(String color);
 }
}
