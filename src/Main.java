import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        vendingMachine();

        System.out.println("1. Przejdź do zakupów");
        System.out.println("2. Przejdź do panelu administracyjnego");
        System.out.println(" ");
        System.out.print("Wprowadź liczbę: ");
        int wybor = scanner.nextInt();

        switch (wybor) {
            case 1 -> vendingMachinePanel.user();
            case 2 -> vendingMachinePanel.admin();
        }
    }

    public static void vendingMachine() {
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