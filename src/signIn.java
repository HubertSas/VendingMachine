import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class signIn {

    public static void logowanie() {
        Scanner scanner = new Scanner(System.in);

        try {
            settings.clearScreen();
            System.out.println("-----------------------");
            System.out.println(" - Automat Do Napojów - ");
            System.out.println("-----------------------");
            System.out.print("Podaj login: ");
            String login = scanner.nextLine();
            System.out.print("Podaj hasło: ");
            String haslo = scanner.nextLine();

            ResultSet result = queryExecutor.executeSelect("SELECT * FROM `konta` WHERE login='" + login + "'");

            if (result.next()) {
                String loginDB = result.getString("login");
                String hasloDB = result.getString("haslo");

                if (login.equals(loginDB) && haslo.equals(hasloDB)) {
                    adminPanel.menu();
                } else {
                    System.out.println("Niepoprawne hasło, spróbuj ponownie");
                    logowanie();
                }
            } else {
                System.out.println("Niepoprawny login lub hasło, spróbuj ponownie");
                logowanie();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
