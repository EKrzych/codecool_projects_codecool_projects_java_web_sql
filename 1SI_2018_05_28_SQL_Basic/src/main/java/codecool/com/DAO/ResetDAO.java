package codecool.com.DAO;

public class ResetDAO implements DAOQueryInterface {
    private String resetMentor = "TRUNCATE TABLE mentors;";
    private String resetApplicant = "TRUNCATE TABLE applicants;";
    private String loadMentor = "COPY mentors(first_name, last_name, nick_name, phone_number, email, city, favourite_number)\n" +
                                "FROM '/Users/elzbietakrzych/Documents/codecool/codecool_projects_java_web_sql/1SI_2018_05_28_SQL_Basic/mentors.csv'\n" +
                                "DELIMITER ',' csv NULL AS 'NULL' QUOTE '''';";
    private String loadApplicant = "COPY applicants(first_name, last_name, phone_number, email, application_code)\n" +
                                    "FROM '/Users/elzbietakrzych/Documents/codecool/codecool_projects_java_web_sql/1SI_2018_05_28_SQL_Basic/applicants.csv'\n" +
                                    "DELIMITER ',' csv NULL AS 'NULL' QUOTE '''';";

    public void resetAndLoad() {
        updateQuery(resetApplicant);
        updateQuery(resetMentor);
        updateQuery(loadApplicant);
        updateQuery(loadMentor);
    }
}
