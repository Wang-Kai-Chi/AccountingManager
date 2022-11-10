import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            DBConnector dbConnector = new DBConnector("test123");
            System.out.println("connection start");

            dbConnector.query("SELECT * FROM member");

            for(String s:dbConnector.getHeaders())
                System.out.print(s+" : ");

            System.out.println(dbConnector.getData(1,2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("hello world");
    }
}

