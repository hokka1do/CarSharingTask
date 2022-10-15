package carsharing;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static String dbFilePath;
    private static String dbUrl = "jdbc:h2:";
    private static final String SQL_TABLE =
            "create table if not exists \"COMPANY\" (\n" +
                    "   ID int not null ,\n" +
                    "   NAME VARCHAR\n" +
                    ");";

    public static void main(String[] args) throws SQLException {
        String dbName = getDbNameOrDefault(args, "carsharing");
        dbFilePath = "./src/carsharing/db/" + dbName;
        dbUrl += dbFilePath;
        try (var connection = DriverManager.getConnection(dbUrl)){
            connection.setAutoCommit(true);
            connection.prepareStatement(SQL_TABLE).execute();
        }
    }

    private static String getDbNameOrDefault(String[] args, String defaultName) {
        return args.length == 2 && "-databaseFileName".equals(args[0])
                ? args[1]
                : defaultName;
    }
}