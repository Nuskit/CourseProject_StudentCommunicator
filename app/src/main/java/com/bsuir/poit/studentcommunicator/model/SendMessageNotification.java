package com.bsuir.poit.studentcommunicator.model;

import java.util.List;
import java.util.Objects;

public class SendMessageNotification {
    private final String reason;
    private final int author;
    private final String message;
    private final List<Receiver> receivers;

    public SendMessageNotification(String reason, int author, String message, List<Receiver> receivers) {
        this.reason = reason;
        this.author = author;
        this.message = message;
        this.receivers = receivers;
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

    public List<Receiver> getReceivers() {
        return receivers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendMessageNotification that = (SendMessageNotification) o;
        return author == that.author &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(message, that.message) &&
                Objects.equals(receivers, that.receivers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reason, author, message, receivers);
    }
}
