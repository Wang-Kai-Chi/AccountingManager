import java.sql.*;
import java.util.Properties;

class DBConnector {
    private Connection connection;
    private ResultSet resultSet;
    private String[] headers;

    public DBConnector(String dbName) throws SQLException {
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "root");

        String url = "jdbc:mysql://localhost:3306/"+dbName;
        connection = DriverManager.getConnection(url, prop);
    }

    public void query(String sql) throws SQLException {
        Statement stmt = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        resultSet = stmt.executeQuery(sql);

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int colCount = rsmd.getColumnCount();
        headers = new String[colCount];

        for (int i = 0; i < headers.length; i++) {
            headers[i] = rsmd.getColumnName(i + 1);
        }
    }

    public int getRows() {
        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException e) {

            return 0;
        }
    }

    public int getCols() {
        return headers.length;
    }

    public String[] getHeaders() {
        return headers;
    }

    public String getData(int row, int col) {
        try {
            resultSet.absolute(row);
            return resultSet.getString(col);
        } catch (SQLException e) {
            System.out.printf("%d : %d :%s\n", row, col, e);
            return "no data";
        }
    }
}
