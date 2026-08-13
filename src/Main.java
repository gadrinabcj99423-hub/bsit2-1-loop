import java.util.ArrayList;
import java.util.Scanner;


class Vehicle {
    protected String brand;
    protected int year;

    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    public void displayInfo() {
        System.out.println("Vehicle: " + brand + " (" + year + ")");
    }
}


class Car extends Vehicle {
    private int numberOfDoors;

    public Car(String brand, int year, int numberOfDoors) {
        super(brand, year);
        this.numberOfDoors = numberOfDoors;
    }


    public void displayInfo() {
        System.out.println("Car: " + brand + " (" + year + ") - "
                + numberOfDoors + " doors");
    }
}


class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle(String brand, int year, boolean hasSidecar) {
        super(brand, year);
        this.hasSidecar = hasSidecar;
    }


    public void displayInfo() {
        String sidecarText;

        if (hasSidecar) {
            sidecarText = "with sidecar";
        } else {
            sidecarText = "no sidecar";
        }

        System.out.println("Motorcycle: " + brand + " (" + year + ") - "
                + sidecarText);
    }
}


public class Main {
    public static void main(String[] args) {


        ArrayList<Vehicle> vehicles = new ArrayList<>();

        int choices = 0;


        try (Scanner input = new Scanner(System.in)) {

            while (choices != 5) {

                String menu = """
                        
                        ===== VEHICLE MANAGER =====
         
                        [1] Add Car
                        [2] Add Motorcycle
                        [3] Remove a Vehicle
                        [4] Display all Vehicles
                        [5] Exit
                        """;
                System.out.println(menu);
                System.out.print("Please Enter your Choices: ");

                choices = input.nextInt();
                input.nextLine();

                if (choices == 1) {

                    System.out.print("Brand: ");
                    String brand = input.nextLine();

                    System.out.print("Year: ");
                    int year = input.nextInt();

                    System.out.print("Number of doors: ");
                    int doors = input.nextInt();

                    vehicles.add(new Car(brand, year, doors));

                    System.out.println(" The car has been added!");

                }


                else if (choices == 2) {

                    System.out.print("Brand: ");
                    String brand = input.nextLine();

                    System.out.print("Year: ");
                    int year = input.nextInt();

                    System.out.print("Has sidecar? (true/false): ");
                    boolean sidecar = input.nextBoolean();

                    vehicles.add(new Motorcycle(brand, year, sidecar));

                    System.out.println(" The motorcycle has been added!");

                }


                else if (choices == 3) {

                    System.out.print("Enter the number to remove: ");
                    int number = input.nextInt();

                    if (number >= 1 && number <= vehicles.size()) {
                        vehicles.remove(number - 1);
                        System.out.println(" It has been removed!");
                    } else {
                        System.out.println(" Invalid number.");
                    }

                }

                else if (choices == 4) {

                    System.out.println("--- All the Vehicles ---");

                    if (vehicles.isEmpty()) {
                        System.out.println("(none yet)");
                    }

                    for (int i = 0; i < vehicles.size(); i++) {
                        System.out.print((i + 1) + ". ");
                        vehicles.get(i).displayInfo();
                    }

                }

                else if (choices != 5) {
                    System.out.println("It's Invalid option.");
                }
            }

            System.out.println("Goodbye!");
        }
    }
}