import java.util.Scanner;

public class vendingMachinePanel {
    public static void user() {
        Scanner scanner = new Scanner(System.in);
        userPanel.productList();

        System.out.println(" ");
        System.out.print("Podaj numer produktu: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Podana wartość nie jest liczbą. Proszę spróbować ponownie: ");
            scanner.nextLine();
        }

        int nrNapoju = scanner.nextInt();
        System.out.println(" ");

        userPanel.payment(nrNapoju);
    }

    public static void admin() {
        //zapis transakcji do pliku
        //wprowadzenie hasla do systemu

//        Administrator
//        1. Wyświetl listę produktów
//                - numer produktu
//                - nazwa produktu
//                - cena produktu
//        2. Dodaj napój
//                - nazwa napoju
//                - ilosc sztuk
//        3. Zmien cene produktu
//                - podaj numer produktu
//                - poadj nową cenę
//        4. Usuń produkt ze sprzedaży
//                - podaj numer produktu (zmieni sie liczba produktow na 0)
//        5. Wyświetl listę operacji
    }
}
