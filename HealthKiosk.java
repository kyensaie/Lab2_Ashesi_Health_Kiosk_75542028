import java.util.Scanner;

public class HealthKiosk {
    public static void main(String[] args) {
        // This allows input from users
        Scanner sc = new Scanner(System.in);

        //Task 1 we are to create a service code(p/l/t/c) where users input one of these characters and by the help of the switch case each of these letters corresponds to a case
        System.out.print(" Enter service code (P/L/T/C) :");
        char letter = sc.next().charAt(0);
        letter = Character.toUpperCase(letter);

        switch (letter) {
            case 'P':
                System.out.println(" Go to : Pharmacy Desk");
                break;
            case 'L':
                System.out.println("Go to : Lab Desk");
                break;
            case 'T':
                System.out.println("Go to : Triage Desk");
                break;
            case 'C':
                System.out.println("Go to : Counseling Desk");
                break;
            default:
                System.out.println("Invalid service code");
        }

        // Task 2 so after a letter is chosen each case has a specific prompts it goes through and asks user
        System.out.println(" Enter the health metric");
        System.out.println("1 : BMI");
        System.out.println("2 : Dosage");
        System.out.println("3 : Simple trig helper");
        System.out.print(" Enter your choice (1/2/3) :");
        int choice = sc.nextInt();

        // Declare shared variables here so they can be used later
        double roundedBMI = 0;
        int tablets = 0;
        double degrees = 0;
        double weight = 0;
        double height = 0;

        switch (choice) {
            case 1: // Calculates BMI
                System.out.print(" Enter your weight (in kg) :");
                weight = sc.nextDouble();
                System.out.print(" Enter your height (in metres) :");
                height = sc.nextDouble();

                double bmi = weight / (height * height);
                roundedBMI = Math.round(bmi * 10) / 10.0;
                System.out.println("BMI is :" + roundedBMI);

                if (bmi < 18.5) {
                    System.out.println("You are Underweight");
                } else if (bmi < 25) {
                    System.out.println("You are Normal");
                } else if (bmi < 30) {
                    System.out.println("You are Overweight");
                } else {
                    System.out.println("You are Obese");
                }
                break;

            case 2: // dosage calculations
                System.out.print(" Enter the required dosage (in mg) :");
                double required = sc.nextDouble();
                tablets = (int) Math.ceil(required / 250.0);

                System.out.println("You need " + tablets + " tablet(s)");
                break;

            case 3: // trig helper
                System.out.print(" Enter an angle in degrees :");
                degrees = sc.nextDouble();

                double angleRadians = Math.toRadians(degrees);

                double sinValue = Math.round(Math.sin(angleRadians) * 1000) / 1000.0;
                double cosValue = Math.round(Math.cos(angleRadians) * 1000) / 1000.0;

                System.out.println(" sin(" + degrees + ") =" + sinValue);
                System.out.println(" cos(" + degrees + ") =" + cosValue);
                break;

            default:
                System.out.println("Invalid choice");
        }

        //TASK 3 This task creates  Student ID using a randomly generated letter at the beginning followed by four random digits
        char letter1 = (char) ('A' + (int) (Math.random() * 26));
        int d1 = 3 + (int) (Math.random() * 7);
        int d2 = 3 + (int) (Math.random() * 7);
        int d3 = 3 + (int) (Math.random() * 7);
        int d4 = 3 + (int) (Math.random() * 7);

        String id = "" + letter1 + d1 + d2 + d3 + d4;

        System.out.println(" Your ID is : " + id);
        if (id.length() != 5) {
            System.out.println("Invalid length");
        } else if (!Character.isLetter(id.charAt(0))) {
            System.out.println("Invalid.. first character must be a letter!");
        } else if (!Character.isDigit(id.charAt(1)) || !Character.isDigit(id.charAt(2)) ||
                !Character.isDigit(id.charAt(3)) || !Character.isDigit(id.charAt(4))) {
            System.out.println("The last 4 characters must be digits");
        } else {
            System.out.println("Your ID is OK !");
        }

        //TASK 4 this task creates a secure code for users by entering the first letter of their name and shifts it by 2 then adds the last two digits of the Student ID and then appends the rounded metric value
        System.out.println("Enter your name : ");
        String firstName = sc.next();

        char baseCode = Character.toUpperCase(firstName.charAt(0));
        System.out.println("Base code : " + baseCode);

        char shifted = (char) ('A' + (baseCode - 'A' + 2) % 26);
        System.out.println("Shifted letter : " + shifted);

        String last2Digits = id.substring(3, 5);
        System.out.println("Last 2 digits : " + last2Digits);

        int metricVal = 0;

        if (choice == 1) {
            metricVal = (int) Math.round(roundedBMI);
        } else if (choice == 2) {
            metricVal = tablets;
        } else if (choice == 3) {
            metricVal = (int) Math.round(Math.sin(Math.toRadians(degrees)) * 100);
        }

        String displayCode = shifted + last2Digits + "-" + metricVal;
        System.out.println("Display Code: " + displayCode);

        // TASK 5 This task gives a summary of the important data the user got or chose and displays them nicely
        String summary = "";

        switch (letter) {
            case 'P':
                summary = "PHARMACY | ID=" + id + " | Code=" + displayCode;
                break;
            case 'L':
                summary = "LAB | ID=" + id + " | Code=" + displayCode;
                break;
            case 'T':
                summary = "TRIAGE | ID=" + id + " | BMI=" +
                        Math.round(weight / (height * height) * 10) / 10.0 +
                        " | Code=" + displayCode;
                break;
            case 'C':
                summary = "COUNSELING | ID=" + id + " | Code=" + displayCode;
                break;
            default:
                summary = "Invalid service code";
        }

        System.out.println("Summary: " + summary);

        sc.close();
    }
}
