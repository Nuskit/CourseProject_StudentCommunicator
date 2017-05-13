package com.bsuir.poit.studentcommunicator.model;


import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MessageNotification {
    private final String reason;
    private final String author;
    private final Date createDate;
    private final String message;

    public MessageNotification(String reason, String author, Date createDate, String message) {
        this.reason = reason;
        this.author = author;
        this.createDate = createDate;
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public String getAuthor() {
        return author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageNotification that = (MessageNotification) o;
        return Objects.equals(reason, that.reason) &&
                Objects.equals(author, that.author) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reason, author, createDate, message);
    }

    @Override
    public String toString() {
        return "MessageNotification{" +
                "reason='" + reason + '\'' +
                ", author='" + author + '\'' +
                ", createDate=" + createDate +
                ", message='" + message + '\'' +
                '}';
    }
}
