import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class userPanel {
    static Scanner scanner = new Scanner(System.in);

    public static void user() {
//        settings.clearScreen();
        productList();

        System.out.println(" ");

        int nrNapoju;
        while (true) {
            System.out.print("Podaj numer produktu: ");
            try {
                nrNapoju = scanner.nextInt();
                System.out.println(" ");

                payment(nrNapoju);
                System.out.println("Wybierz poprawny numer");
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }
    }

    public static void productList() {
        settings.clearScreen();
        try {
            String zapytanie = "SELECT * FROM `produkty`";
            ResultSet resultPracownik = queryExecutor.executeSelect(zapytanie);

            while (resultPracownik.next()) {

                String table1 = resultPracownik.getString("id_produktu");
                String table2 = resultPracownik.getString("nazwa_produktu");
                String table3 = resultPracownik.getString("cena_produktu");
                int table4 = resultPracownik.getInt("ilosc_sztuk");

                if (table4 > 0) {
                    System.out.println("Nr: " + table1 + " " + table2 + " - " + table3 + " zł");
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void payment(int nrNapoju) {
        settings.clearScreen();
        try {
            String zapytanie = "SELECT * FROM `produkty` WHERE `id_produktu` = "+nrNapoju+"";
            ResultSet result = queryExecutor.executeSelect(zapytanie);

            while (result.next()) {

                String table1 = result.getString("id_produktu");
                String table2 = result.getString("nazwa_produktu");
                double table3 = Double.parseDouble(result.getString("cena_produktu"));

                System.out.println("Wybrałeś pozycję nr. " + table1);
                System.out.println("Nazwa: " + table2 + ", Cena: " + table3 + " zł");

                System.out.println(" ");

                double kwota;
                while (true) {
                    System.out.print("Wprowadź sumę wrzuconych monet (zl,gr): ");
                    try {
                        kwota = scanner.nextDouble();

                        System.out.println(" ");

                        if (kwota == table3) {
                            String transakcja = "INSERT INTO `transakcje`(`nazwa_produktu`, `cena_produktu`, `kwota_pobrana`, `reszta`) VALUES ('" + table2 + "','" + table3 + "','" + kwota + "', 0)";
                            queryExecutor.executeQuery(transakcja);

                            String aktualizacjaSztuk = "UPDATE `produkty` SET `ilosc_sztuk`= `ilosc_sztuk` - 1 WHERE `nazwa_produktu` = '"+table2+"'";
                            queryExecutor.executeQuery(aktualizacjaSztuk);

                            String zmianaSalda = "UPDATE `kasetka` SET `saldo`= `saldo` + '"+table3+"'";
                            queryExecutor.executeQuery(zmianaSalda);

                            System.out.println("Operacja przebiegła pomyślnie, odbierz produkt");
                            endMenu();

                        } else if (kwota > table3) {
                            double reszta = kwota - table3;

                            String transakcja = "INSERT INTO `transakcje`(`nazwa_produktu`, `cena_produktu`, `kwota_pobrana`, `reszta`) VALUES ('" + table2 + "','" + table3 + "','" + kwota + "','" + reszta + "')";
                            queryExecutor.executeQuery(transakcja);

                            String aktualizacjaSztuk = "UPDATE `produkty` SET `ilosc_sztuk`= `ilosc_sztuk` - 1 WHERE `nazwa_produktu` = '"+table2+"'";
                            queryExecutor.executeQuery(aktualizacjaSztuk);

                            String zmianaSalda = "UPDATE `kasetka` SET `saldo`= `saldo` + '"+table3+"'";
                            queryExecutor.executeQuery(zmianaSalda);

                            System.out.println("Operacja przebiegła pomyślnie");
                            System.out.println("Twoja reszta to: " + String.format("%.2f zł", reszta));
                            System.out.println("Odbierz produkt oraz resztę");
                            endMenu();

                        } else {
                            System.out.println("Operacja odrzucona");
                            endMenu();
                        }
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                        scanner.next();
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void endMenu() {
//        settings.clearScreen();
        System.out.println(" ");
        System.out.println("Co chcesz zrobić?");
        System.out.println("1. Zacznij od nowa");
        System.out.println("2. Start");
        System.out.println(" ");

        int wybor;
        while (true) {
            System.out.print("Wprowadź liczbę: ");
            try {
                wybor = scanner.nextInt();

                switch (wybor) {
                    case 1 -> user();
                    case 2 -> vendingMachine.machineMenu();
                    default -> {
                        System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                        settings.clearScreen();
                        endMenu();
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }
    }
}
