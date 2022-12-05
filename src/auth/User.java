package auth;

import java.util.Objects;

public final class User {
    private final boolean isAdmin;
    private final String username;
    private final String email;
    private final String password;
    private final int id;
    private int walletBalance;

    public User(boolean isAdmin, String username, String email, String password, int id, int walletBalance) {
        this.isAdmin = isAdmin;
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = id;
        this.walletBalance = walletBalance;
    }

    public void addFunds(float funds) {
        this.walletBalance += funds;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String username() {
        return username;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    public int id() {
        return id;
    }

    public float walletBalance() {
        return this.walletBalance;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (User) obj;
        return this.isAdmin == that.isAdmin &&
                Objects.equals(this.username, that.username) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.password, that.password) &&
                this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isAdmin, username, email, password, id);
    }

    @Override
    public String toString() {
        return "User[" +
                "isAdmin=" + isAdmin + ", " +
                "username=" + username + ", " +
                "email=" + email + ", " +
                "password=" + password + ", " +
                "id=" + id + ']';
    }
}
