public class Member {
    private int id;
    private String name, birthday, role;

    public Member(int id, String name, String birthday, String role) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getRole() {
        return role;
    }
}
