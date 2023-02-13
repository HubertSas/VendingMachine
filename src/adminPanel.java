import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class adminPanel {
    static Scanner scanner = new Scanner(System.in);
    public static void menu() {
        settings.clearScreen();
        while (true) {
            settings.clearScreen();
            System.out.println("-----------------------");
            System.out.println("-- Co chcesz zrobić --");
            System.out.println("-----------------------");
            System.out.println("1. Wyświetl listę napojów");
            System.out.println("2. Uzupełnij automat");
            System.out.println("3. Dodaj nowy napój");
            System.out.println("4. Zmień cenę napoju");
            System.out.println("5. Usuń napój");
            System.out.println("6. Wyświetl historię transakcji");
            System.out.println("7. Wypłać pieniądze");
            System.out.println("8. Wyświetl historię wypłat");
            System.out.println(" ");
            System.out.println("9. Wyloguj");
            System.out.println(" ");

            int wybor;

            while (true) {
                System.out.print("Wybierz opcję : ");
                try {
                    wybor = scanner.nextInt();

                    switch (wybor) {
                        case 1 -> {
                            showPotions();

                            System.out.println("1. Wróć");
                            System.out.print("Wybierz opcje: ");
                            scanner.nextInt();
                        }
                        case 2 -> {
                            completeMachine();

                            System.out.println("1. Wróć");
                            System.out.print("Wybierz opcje: ");
                            scanner.nextInt();
                        }
                        case 3 -> {
                            addPotion();

                            System.out.println("1. Wróć");
                            System.out.print("Wybierz opcje: ");
                            scanner.nextInt();
                        }
                        case 4 -> {
                            changePrice();

                            System.out.println("1. Wróć");
                            System.out.print("Wybierz opcje: ");
                            scanner.nextInt();
                        }
                        case 5 -> {
                            removePotion();

                            System.out.println("1. Wróć");
                            System.out.print("Wybierz opcje: ");
                            scanner.nextInt();
                        }
                        case 6 -> {
                            showTransactions();

                            System.out.println("1. Wróć");
                            System.out.print("Wybierz opcje: ");
                            scanner.nextInt();
                        }
                        case 7 -> {
                            payOut();

                            System.out.println("1. Wróć");
                            System.out.print("Wybierz opcje: ");
                            scanner.nextInt();
                        }
                        case 8 -> {
                            showPaycheck();

                            System.out.println("1. Wróć");
                            System.out.print("Wybierz opcje: ");
                            scanner.nextInt();
                        }
                        case 9 -> {
                            System.out.println("Wylogowywanie...");
                            settings.clearScreenWait();
                            vendingMachine.machineMenu();
                        }
                        default -> {
                            System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                            settings.clearScreenWait();
                            adminPanel.menu();
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

    public static void showPotions() {
        settings.clearScreen();
        try {
            String zapytanie = "SELECT * FROM `produkty`";
            ResultSet resultAdmin = queryExecutor.executeSelect(zapytanie);

            while (resultAdmin.next()) {

                String table1 = resultAdmin.getString("id_produktu");
                String table2 = resultAdmin.getString("nazwa_produktu");
                String table3 = resultAdmin.getString("cena_produktu");
                String table4 = resultAdmin.getString("ilosc_sztuk");

                System.out.println("Nr: " + table1 + " " + table2 + " - " + table3 + " zł - " + table4 + " szt");

            }

            System.out.println(" ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void completeMachine() {
        settings.clearScreen();
        showPotions();

        int idPotion;
        while (true) {
            System.out.print("Podaj nr. napoju: ");
            try {
                idPotion = scanner.nextInt();

                int numberValue;
                while (true) {
                    System.out.print("Podaj ilość sztuk: ");
                    try {
                        numberValue = scanner.nextInt();

                        if (numberValue > 0) {
                            String aktualizacjaSztuk = "UPDATE `produkty` SET `ilosc_sztuk`=`ilosc_sztuk` + '"+numberValue+"' WHERE `id_produktu` ='"+idPotion+"'";
                            queryExecutor.executeQuery(aktualizacjaSztuk);

                            System.out.println(" ");
                            System.out.println("Uzupełniono maszynę.");
                        } else {
                            System.out.println("Nieprawidłowe wartości. Spróbuj ponownie");
                            completeMachine();
                        }
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                        scanner.next();
                    }
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }
        System.out.println(" ");
    }

    public static void addPotion() {
        System.out.println(" ");
        settings.clearScreen();

        System.out.print("Podaj nazwę napoju: ");
        scanner.nextLine();
        String potionName = scanner.nextLine();

        int potionPrice;
        while (true) {
            System.out.print("Podaj cenę napoju: ");
            try {
                potionPrice = scanner.nextInt();

                int quantity;
                while (true) {
                    System.out.print("Podaj ilość dodanych sztuk: ");
                    try {
                        quantity = scanner.nextInt();

                        if (quantity > 0 && potionPrice > 0) {
                            String aktualizacjaSztuk = "INSERT INTO `produkty`(`nazwa_produktu`, `cena_produktu`, `ilosc_sztuk`) VALUES ('"+potionName+"','"+potionPrice+"','"+quantity+"')";
                            queryExecutor.executeQuery(aktualizacjaSztuk);

                            System.out.println(" ");
                            System.out.println("Dodano nowy napój.");
                        } else {
                            System.out.println("Nieprawidłowe wartości. Spróbuj ponownie");
                            addPotion();
                        }

                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                        scanner.next();
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }

        System.out.println(" ");

    }

    public static void changePrice() {
        System.out.println(" ");
        settings.clearScreen();
        showPotions();

        int potionId;
        while (true) {
        System.out.print("Podaj nr. napoju: ");
            try {
                potionId = scanner.nextInt();

                int newPrice;
                while (true) {
                    System.out.print("Podaj nową cenę produktu: ");
                    try {
                        newPrice = scanner.nextInt();

                        if (newPrice > 0) {
                            String aktualizacjaSztuk = "UPDATE `produkty` SET `cena_produktu`='"+newPrice+"' WHERE `id_produktu` = '"+potionId+"'";
                            queryExecutor.executeQuery(aktualizacjaSztuk);

                            System.out.println(" ");
                            System.out.println("Zmieniono cenę.");
                            System.out.println(" ");
                        }
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                        scanner.next();
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }
    }

    public static void removePotion() {
        System.out.println(" ");
        settings.clearScreen();
        showPotions();

        int potionId;
        while (true) {
            System.out.print("Podaj nr. napoju do usunięcia: ");
            try {
                potionId = scanner.nextInt();

                String aktualizacjaSztuk = "UPDATE `produkty` SET `ilosc_sztuk`= 0 WHERE `id_produktu` = '"+potionId+"'";
                queryExecutor.executeQuery(aktualizacjaSztuk);

                System.out.println(" ");
                System.out.println("Usunięto produkt ze sprzedaży");
                System.out.println(" ");
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }
    }

    public static void showTransactions() {
        settings.clearScreen();
        System.out.println(" ");
        StringBuilder toFile = new StringBuilder();

        try {
            String zapytanie = "SELECT * FROM `transakcje`";
            ResultSet resultAdmin = queryExecutor.executeSelect(zapytanie);

            while (resultAdmin.next()) {

                String table1 = resultAdmin.getString("id");
                String table2 = resultAdmin.getString("data_czas_transakcji");
                String table3 = resultAdmin.getString("nazwa_produktu");
                String table4 = resultAdmin.getString("kwota_pobrana");
                double value = resultAdmin.getDouble("reszta");
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String table5 = decimalFormat.format(value);

                System.out.println("ID: " + table1 + " - " + table2 + " - " + table3 + " - Pobrano: " + table4 + "zł - Reszta: " + table5 + "zł");
                toFile.append("ID: ").append(table1).append(" - ").append(table2).append(" - ").append(table3).append(" - Pobrano: ").append(table4).append("zł - Reszta: ").append(table5).append("zł\n");
            }
            System.out.println(" ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Czy chcesz zapisać dane do pliku txt?");
        System.out.println("1. Tak");
        System.out.println("2. Nie, wróć do menu ");

        int wybor;
        while (true) {
            System.out.print("Wybierz opcję: ");
            try {
                wybor = scanner.nextInt();

                switch (wybor) {
                    case 1 -> {
                        String fileName = "transakcje.txt";
                        try {
                            FileWriter fileWriter = new FileWriter(fileName);
                            fileWriter.write(toFile.toString());
                            fileWriter.close();
                            System.out.println(" ");
                            System.out.println("Dane zapisano do pliku " + fileName);
                            System.out.println(" ");
                            settings.clearScreenWait();
                            menu();
                        } catch (IOException e) {
                            System.out.println("Wystąpił błąd podczas zapisywania danych do pliku " + fileName);
                            e.printStackTrace();
                        }
                    }
                    case 2 -> menu();
                    default -> {
                        System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                        settings.clearScreenWait();
                        menu();
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                scanner.next();
            }
        }
    }

    public static void payOut() {
        System.out.println(" ");
        settings.clearScreen();
        try {
            String zapytanie = "SELECT * FROM `kasetka`";
            ResultSet resultPracownik = queryExecutor.executeSelect(zapytanie);

            int table2 = 0;
            while (resultPracownik.next()) {

                table2 = resultPracownik.getInt("saldo");

                if (table2 > 0) {
                    System.out.println("Twoje saldo to: " + table2 + " zł");
                } else {
                    System.out.println("Brak środków na koncie.");
                    settings.clearScreenWait();
                    menu();
                }
            }
            System.out.println(" ");

            System.out.println("Czy chcesz wypłacić środki?");
            System.out.println("1. Tak");
            System.out.println("2. Nie, wróć do menu ");

            int wybor;
            while (true) {
                System.out.print("Wybierz opcję: ");
                try {
                    wybor = scanner.nextInt();

                    switch (wybor) {
                        case 1 -> {
                            System.out.println("Wypłaciłeś: " + table2 + " zł");

                            String zmianaSalda = "UPDATE `kasetka` SET `saldo`= 0";
                            queryExecutor.executeQuery(zmianaSalda);

                            String ksiegowanieWplaty = "INSERT INTO `wyplata`(`saldo`) VALUES ("+table2+")";
                            queryExecutor.executeQuery(ksiegowanieWplaty);

                            settings.clearScreenWait();
                            menu();
                        }
                        case 2 -> menu();
                        default -> {
                            System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                            settings.clearScreenWait();
                            menu();
                        }
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Wprowadzono nieprawidłowe dane, spróbuj jeszcze raz.");
                    scanner.next();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showPaycheck() {
        settings.clearScreen();
        System.out.println(" ");
        StringBuilder toFile = new StringBuilder();

        try {
            String zapytanie = "SELECT * FROM `wyplata`";
            ResultSet resultAdmin = queryExecutor.executeSelect(zapytanie);

            while (resultAdmin.next()) {

                String table1 = resultAdmin.getString("id");
                String table2 = resultAdmin.getString("data_czas");
                String table3 = resultAdmin.getString("saldo");

                System.out.println("ID: " + table1 + " - " + table2 + " - " + table3 + "zł");
                toFile.append("ID: ").append(table1).append(" - ").append(table2).append(" - ").append(table3).append("zł\n");
            }
            System.out.println(" ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Czy chcesz zapisać dane do pliku txt?");
        System.out.println("1. Tak");
        System.out.println("2. Nie, wróć do menu ");

        int wybor;
        while (true) {
            System.out.print("Wybierz opcję: ");
            try {
                wybor = scanner.nextInt();

                switch (wybor) {
                    case 1 -> {
                        String fileName = "wyplaty.txt";
                        try {
                            FileWriter fileWriter = new FileWriter(fileName);
                            fileWriter.write(toFile.toString());
                            fileWriter.close();
                            System.out.println(" ");
                            System.out.println("Dane zapisano do pliku " + fileName);
                            System.out.println(" ");
                            settings.clearScreenWait();
                            menu();
                        } catch (IOException e) {
                            System.out.println("Wystąpił błąd podczas zapisywania danych do pliku " + fileName);
                            e.printStackTrace();
                        }
                    }
                    case 2 -> menu();
                    default -> {
                        System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                        settings.clearScreenWait();
                        menu();
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
