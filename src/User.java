public class User {
    private final boolean admin;
    private final String username;
    private final String email;
    private final String password;

    public User(boolean admin , String username , String email , String password ){
        this.admin = admin;
        this.username = username;
        this.email = email;
        this.password = password;


    }


    public String getUsername() {
        return  username;
    }
    public String getEmail() {
        return  email;
    }
    public boolean checkpasswword(String password) {
        return this.password.equals(password);
    }

    public boolean isAdmin() {
        return admin;
    }
}
