package codecool.com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public interface DBConnectionInterface {

    default Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/codecool_application",
                            "elzbietakrzych", "");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return c;
    }
}
