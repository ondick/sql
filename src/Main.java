import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Knihovnicka knihovnicka = new Knihovnicka();
        knihovnicka.vypisKnih();

        Obchudek obchudek = new Obchudek();
        obchudek.vypisObchod();


    }
}
