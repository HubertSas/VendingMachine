import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class userPanel {
    static Scanner scanner = new Scanner(System.in);
    public static void productList() {
        try {
            String zapytanie = "SELECT * FROM `produkty`";
            ResultSet resultPracownik = queryExecutor.executeSelect(zapytanie);

            while (resultPracownik.next()) {

                String table1 = resultPracownik.getString("id_produktu");
                String table2 = resultPracownik.getString("nazwa_produktu");
                String table3 = resultPracownik.getString("cena_produktu");

                System.out.println("Nr: " + table1 + " " + table2 + " - " + table3 + " zł");


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void payment(int nrNapoju) {
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
                System.out.print("Wprowadź sumę wrzuconych monet (zl,gr): ");
                //moge dodac opcjie z numerkami 1.1zl itd
                double kwota = scanner.nextDouble();
                scanner.nextLine();
                System.out.println(" ");


                if (kwota == table3) {
                    String transakcja = "INSERT INTO `transakcje`(`nazwa_produktu`, `cena_produktu`, `kwota_pobrana`, `reszta`) VALUES ('" + table2 + "','" + table3 + "','" + kwota + "','brak')";
                    queryExecutor.executeQuery(transakcja);

                    System.out.println("Operacja przebiegła pomyślnie, odbierz produkt");
                    endMenu();

                } else if (kwota > table3) {
                    double reszta = kwota - table3;

                    String transakcja = "INSERT INTO `transakcje`(`nazwa_produktu`, `cena_produktu`, `kwota_pobrana`, `reszta`) VALUES ('" + table2 + "','" + table3 + "','" + kwota + "','" + reszta + "')";
                    queryExecutor.executeQuery(transakcja);

                    System.out.println("Operacja przebiegła pomyślnie");
                    System.out.println("Twoja reszta to: " + String.format("%.2f zł", reszta));
                    System.out.println("Odbierz produkt oraz resztę");
                    endMenu();

                } else {
                    System.out.println("Operacja odrzucona");
                    endMenu();
                }


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void endMenu() {
        System.out.println(" ");
        System.out.println("Co chcesz zrobić?");
        System.out.println("1. Zacznij od nowa");
        System.out.println("2. Zakończ");
        System.out.println(" ");

        System.out.print("Wprowadź liczbę: ");
        int wybor = scanner.nextInt();

        scanner.nextLine();
        switch (wybor) {
            case 1 -> vendingMachinePanel.user();
            case 2 -> System.exit(0);
        }
    }
}
