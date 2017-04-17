package com.bsuir.poit.studentcommunicator.model;

import java.util.List;

public class MessageNotification {
    private String reason;
    private int author;
    private String message;
    private List<Receiver> recievers;

    public MessageNotification(String reason, int author, String message, List<Receiver> recievers) {
        this.reason = reason;
        this.author = author;
        this.message = message;
        this.recievers = recievers;
    }

    public String getReason() {
        return reason;
    }

    public int getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public List<Receiver> getRecievers() {
        return recievers;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (this == object) {
            return true;
        }

        if (this.getClass() != object.getClass()) {
            return false;
        }

        MessageNotification messageNotification = (MessageNotification) object;
        return (author == messageNotification.author)
                && (reason == null
                ? messageNotification.reason == null
                : reason.equals(messageNotification.reason))
                && (message == null
                ? messageNotification.message == null
                : message.equals(messageNotification.message))
                && (recievers == null
                ? messageNotification.recievers == null
                : recievers.equals(messageNotification.recievers));
    }

    @Override
    public int hashCode() {
        int ht = 17;
        ht = 31 * ht + (reason == null
                ? 0
                : reason.hashCode());
        ht = 31 * ht + author;
        ht = 31 * ht + (message == null
                ? 0
                : message.hashCode());
        ht = 31 * ht + (recievers == null
                ? 0
                : recievers.hashCode());
        return ht;
    }
}
