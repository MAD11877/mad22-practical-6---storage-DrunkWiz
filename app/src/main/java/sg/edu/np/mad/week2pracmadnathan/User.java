package sg.edu.np.mad.week2pracmadnathan;

public class User {
    private String Name;
    private String Description;
    private Integer id;
    private Boolean followed;

    public User(){}

    public User(String name, String description, Integer id, Boolean followed) {
        Name = name;
        Description = description;
        this.id = id;
        this.followed = followed;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFollowed() {
        return followed;
    }

    public void setFollowed(Boolean followed) {
        this.followed = followed;
    }
}
