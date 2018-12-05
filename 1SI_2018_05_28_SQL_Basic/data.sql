COPY mentors(first_name, last_name, nick_name, phone_number, email, city, favourite_number)
FROM '/Users/elzbietakrzych/Documents/codecool/DBSQL/2018_05_28_SI/sql-application-process-EKrzych/mentors.csv'
DELIMITER ',' csv NULL AS 'NULL' QUOTE '''';

COPY applicants(first_name, last_name, phone_number, email, application_code)
FROM '/Users/elzbietakrzych/Documents/codecool/DBSQL/2018_05_28_SI/sql-application-process-EKrzych/applicants.csv'
DELIMITER ',' csv NULL AS 'NULL' QUOTE '''';