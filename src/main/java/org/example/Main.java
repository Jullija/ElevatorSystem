package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int floors, elevators;
        System.out.println("Let's create your elevator system!");
        System.out.print("How many floors? ");
        do {

            if (scanner.hasNextInt()) {
                floors = scanner.nextInt();
                if (floors > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive number of floors.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        } while (true);
        System.out.print("How many elevators? ");
        do {

            if (scanner.hasNextInt()) {
                elevators = scanner.nextInt();
                if (elevators > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive number of elevators.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        } while (true);

        scanner.close();


        ElevatorSystem elevatorSystem = new ElevatorSystem(floors, elevators);
        elevatorSystem.simulation();
    }
}




