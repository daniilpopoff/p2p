package org.example;

public class User {

    private String user_name;
    private String email;
    private String password;
    private String phone_number;


    public User(int id, String user_name, String email, String password, String phone_number) {
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
    }
    public String getUser_name() {
        return user_name;
    }

    public int getUserId(){//TODO надо написать какойто код который вытаскивает id нынешнего юзера из бд

    }

}
