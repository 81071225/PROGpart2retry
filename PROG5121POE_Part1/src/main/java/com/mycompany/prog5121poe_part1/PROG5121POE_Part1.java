package com.mycompany.prog5121poe_part1;

import java.util.Scanner;
import java.util.ArrayList;

public class PROG5121POE_Part1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginSystem = new Login();

        System.out.println("--- REGISTER ---");
        System.out.print("Enter username (must contain '_' and max 5 chars): ");
        String user = scanner.nextLine();
        
        System.out.print("Enter password (min 8 chars, 1 capital, 1 number, 1 special): ");
        String pass = scanner.nextLine();
        
        System.out.print("Enter cell number (with international code +27): ");
        String cell = scanner.nextLine();
        
        System.out.print("Enter first name: ");
        String fName = scanner.nextLine();
        
        System.out.print("Enter last name: ");
        String lName = scanner.nextLine();

        loginSystem.setInputData(user, pass, cell, fName, lName);
        String regResult = loginSystem.registerUser();
        System.out.println(regResult);

        if (regResult.contains("successfully")) {
            System.out.println("\n--- LOGIN ---");
            System.out.print("Enter username: ");
            String loginUser = scanner.nextLine();
            
            System.out.print("Enter password: ");
            String loginPass = scanner.nextLine();

            boolean success = loginSystem.loginUser(loginUser, loginPass);
            System.out.println(loginSystem.returnLoginStatus(success));

            if (success) {
                ArrayList<Task> taskList = new ArrayList<>();
                int choice;
                
                do {
                    System.out.println("\n--- MAIN MENU ---");
                    System.out.println("1. Add tasks");
                    System.out.println("2. Show report (Coming soon)");
                    System.out.println("3. Quit");
                    System.out.print("Choose an option: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("How many tasks would you like to add? ");
                            int numTasks = scanner.nextInt();
                            scanner.nextLine();

                            int totalHours = 0;

                            for (int i = 0; i < numTasks; i++) {
                                System.out.println("\n--- Task " + i + " ---");
                                System.out.print("Enter Task Name: ");
                                String tName = scanner.nextLine();

                                System.out.print("Enter Task Description (max 50 chars): ");
                                String tDesc = scanner.nextLine();

                                System.out.print("Enter Developer Details (e.g., First Last): ");
                                String tDev = scanner.nextLine();

                                System.out.print("Enter Task Duration (in hours): ");
                                int tDuration = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Enter Task Status (To Do, Done, Doing): ");
                                String tStatus = scanner.nextLine();

                                Task newTask = new Task(tName, i, tDesc, tDev, tDuration, tStatus);

                                if (!newTask.checkTaskDescription()) {
                                    System.out.println("Please enter a task description of less than 50 characters.");
                                    i--;
                                    continue;
                                }

                                taskList.add(newTask);
                                totalHours += newTask.getTaskDuration();

                                System.out.println("Task successfully captured!");
                                System.out.println(newTask.printTaskDetails());
                            }
                            System.out.println("Total combined hours of all tasks: " + totalHours + " hours");
                            break;

                        case 2:
                            System.out.println("Feature under development.");
                            break;

                        case 3:
                            System.out.println("Exiting application. Goodbye!");
                            break;

                        default:
                            System.out.println("Invalid option. Please choose again.");
                    }
                } while (choice != 3);
            }
        }
        scanner.close();
    }
}