package codecool.com.person;

public class Applicant extends Person {
    private Integer application_code;

    public Applicant(String first_name, String last_name, String phone_number, String email, Integer application_code) {
        super(first_name, last_name, phone_number, email);
        this.application_code = application_code;
    }

    public Integer getApplication_code() {
        return application_code;
    }

    @Override
    public String toString() {
        return getFirst_name() + ' ' +
                getLast_name() + ' ' +
                getPhone_number() + ' ' +
                getEmail() + ' ' +
                application_code + "\n";
    }
}
