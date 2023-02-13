import java.util.InputMismatchException;
import java.util.Scanner;

public class vendingMachine {

    public static void machineMenu() {
        settings.clearScreen();
        Scanner scanner = new Scanner(System.in);
        vendingMachineASCII();

        System.out.println("1. Przejdź do zakupów");
        System.out.println("2. Przejdź do panelu administracyjnego");
        System.out.println(" ");

        int wybor;
        while (true) {
            System.out.print("Wprowadź liczbę: ");
            try {
                wybor = scanner.nextInt();

                switch (wybor) {
                    case 1 -> userPanel.user();
                    case 2 -> signIn.logowanie();
                    default -> {
                        System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                        settings.clearScreenWait();
                        vendingMachine.machineMenu();
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }
    }

    public static void vendingMachineASCII() {
        System.out.println(" ________________________________");
        System.out.println("|    ______________________      |");
        System.out.println("|   | ____________________||  |  |");
        System.out.println("|   ||  [1]   [2]   [3]   ||  |  |");
        System.out.println("|   ||  [4]   [5]   [6]   ||  |  |");
        System.out.println("|   ||  [7]   [8]   [9]   ||  _  |");
        System.out.println("|   ||  [10]  [11]  [12]  || | | |");
        System.out.println("|   ||  [13]  [14]  [15]  || | | |");
        System.out.println("|   ||  [16]  [17]  [18]  || | | |");
        System.out.println("|   ||  [19]  [20]  [21]  || |_| |");
        System.out.println("|   ||  [22]  [23]  [24]  ||  |  |");
        System.out.println("|   ||  [25]  [26]  [27]  ||  |  |");
        System.out.println("|   ||  [28]  [29]  [30]  ||  |  |");
        System.out.println("|   ||____________________||  |  |");
        System.out.println("|   |______________________|  |  |");
        System.out.println("|   ________________________  |  |");
        System.out.println("|   [                      ]  |  |");
        System.out.println("|   [______________________]  |  |");
        System.out.println("|________________________________|");
        System.out.println(" ");
    }
}
