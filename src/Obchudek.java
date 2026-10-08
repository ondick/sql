import java.sql.*;
import java.util.Scanner;

public class Obchudek {
    Scanner sc = new Scanner(System.in);

    public void vypisObchod() throws SQLException {
        String url = "jdbc:mysql://localhost/obchod"; // URL databáze: server, port, název DB
        String username = "root"; // Uživatelské jméno
        String password = ""; // Heslo k databázi
// Připojení k databázi
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Připojení k databázi bylo úspěšné!");
// Výpis dat z tabulky
            String dotazSELECT = "SELECT * FROM objednavky INNER JOIN zakaznici ON objednavky.zakaznik_id = zakaznici.id WHERE zakaznici.email = ?";
            System.out.println("Napis email");
            String em = sc.nextLine();

            try (PreparedStatement stmt = conn.prepareStatement(dotazSELECT)) {
                stmt.setString(1, em);
                try (ResultSet rs = stmt.executeQuery()) {
                    System.out.println("Seznam objednavek:");
                    while (rs.next()) {
                        int zakaznik_id = rs.getInt("zakaznik_id");
                        String produkt = rs.getString("produkt");
                        double cena = rs.getDouble("cena");
                        System.out.println(zakaznik_id + " " + produkt + " " + cena);
                    }
                } catch (SQLException e) {
                    System.err.println("Problém s SQL.");
                }
            } catch (SQLException e) {
                System.err.println("Problém s připojením.");
            }
        }
    }
}
