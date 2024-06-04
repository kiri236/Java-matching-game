package com.User;

public class user implements Comparable<user>{
    private String Username;
    private String Password;
    public user(String username,String password)
    {
        this.Username=username;
        this.Password=password;
    }
    public user()
    {
    }
    public void setUsername(String username) {
        Username = username;
    }
    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public int compareTo(user o) {

        return this.getUsername().compareTo(o.getUsername());
    }
    @Override
    public String toString() {
        return super.toString();
    }
    public boolean equals(user other) {
        return this.getUsername().equals(other.getUsername());
    }
    public boolean Password_Match(String password)
    {
        return this.getPassword().equals(password);
    }
}
