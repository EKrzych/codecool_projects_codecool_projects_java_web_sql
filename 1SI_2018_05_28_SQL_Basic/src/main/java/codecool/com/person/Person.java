package codecool.com.person;

public abstract class Person {
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email;

    public Person(String first_name, String last_name, String phone_number, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }
}
