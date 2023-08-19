package crypto.entidadesdenegocio;

public class UserEN {
    private int id;
    private String nameUser;
    private String lastName;
    private int userPassword;
    private String gmail;

    
    public UserEN() {
    }

    
    public UserEN(int id, String nameUser, String lastName, int userPassword, String gmail) {
        this.id = id;
        this.nameUser = nameUser;
        this.lastName = lastName;
        this.userPassword = userPassword;
        this.gmail = gmail;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(int userPassword) {
        this.userPassword = userPassword;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

}