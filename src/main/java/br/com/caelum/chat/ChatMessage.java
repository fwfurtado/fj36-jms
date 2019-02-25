package br.com.caelum.chat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ChatMessage implements Serializable {
    private User user;
    private LocalDateTime date;
    private String content;

    public ChatMessage(User user, String content) {
        this.user = user;
        this.content = content;
        this.date = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String toView() {
        return String.format("[%s] - [%s] -> %s", date.toString(), user.getNickname(), content);
    }
}
