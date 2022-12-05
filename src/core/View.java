package core;

import java.util.Scanner;

public abstract class View {
    private final Scanner scanner = new Scanner(System.in);


    protected String inputString(String prompt) {
        String input = "";
        do {
            try {
                System.out.print(prompt);
                input = this.scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        } while (input.equals(""));

        return input;
    }

    protected float inputFloat(String prompt) {
        float input = -1;
        do {
            try {
                System.out.print(prompt);
                input = Float.parseFloat(this.scanner.nextLine());
            } catch (Exception e) {
                System.out.println(input + " is an invalid decimal number");
            }
        } while (input == -1);

        return input;
    }

    protected int inputInteger(String prompt) {
        int input = -1;
        do {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(this.scanner.nextLine());
            } catch (Exception e) {
                System.out.println(input + " is an invalid integer");
            }
        } while (input == -1);

        return input;
    }

    protected boolean inputBoolean(String prompt) {
        System.out.print(prompt + " (Y/N) ");
        String input = this.scanner.nextLine();

        if (input.equalsIgnoreCase("y")) return true;
        else if (input.equalsIgnoreCase("n")) return false;
        System.out.println("Please type in Y for Yes or N for No");
        return inputBoolean(prompt);
    }

    protected int inputWithinRange(String prompt, int start, int end) {
        int input = -1;
        do {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(this.scanner.nextLine());
            } catch (Exception e) {
                System.out.println(input + " is an invalid integer");
            }
            if (end < input || input < start)
                System.out.println("Invalid option");
        } while (end < input || input < start);
        return input;
    }
}
