package br.com.caelum.chat;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String nickname;

    public User(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
