package entity;

public class User {
    private final Integer id;
    private final String name;
    private final String last_name;
    private final String email;

    public User(Integer id, String name, String last_name, String email) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User"+id+"{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
