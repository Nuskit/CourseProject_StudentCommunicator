package com.bsuir.poit.studentcommunicator.model;

import java.util.List;
import java.util.Objects;

// TODO: add author in service level
public class SendMessageNotification {
    private final String reason;
    private final String message;
    private final List<Receiver> receivers;

    public SendMessageNotification(String reason, String message, List<Receiver> receivers) {
        this.reason = reason;
        this.message = message;
        this.receivers = receivers;
    }

    public String getReason() {
        return reason;
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
        return Objects.equals(reason, that.reason) &&
                Objects.equals(message, that.message) &&
                Objects.equals(receivers, that.receivers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reason, message, receivers);
    }
}
