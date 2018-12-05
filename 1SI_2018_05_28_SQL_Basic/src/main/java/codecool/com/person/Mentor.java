package codecool.com.person;

public class Mentor extends Person {
    private String nick_name;
    private String city;
    private Integer favourite_number;

    public Mentor(String first_name, String last_name, String phone_number, String email, String nick_name, String city, Integer favourite_number) {
        super(first_name, last_name, phone_number, email);
        this.nick_name = nick_name;
        this.city = city;
        this.favourite_number = favourite_number;
    }

    public String getNick_name() {
        return nick_name;
    }

    public String getCity() {
        return city;
    }

    public Integer getFavourite_number() {
        return favourite_number;
    }

    @Override
    public String toString() {
        return getFirst_name() + ' ' +
                getLast_name() + ' ' +
                getPhone_number() + ' ' +
                getEmail() + ' ' +
                nick_name + ' ' +
                city + ' ' +
                favourite_number +"\n";
    }
}

