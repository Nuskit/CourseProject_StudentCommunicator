package com.bsuir.poit.studentcommunicator.model;

public class Receiver {
    private final int id;
    private final String name;

    public Receiver(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
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

        Receiver receiver = (Receiver) object;
        return (this.id == receiver.id)
                && name.equals(receiver.name);
    }

    @Override
    public int hashCode(){
        int ht = 17;
        ht = 31 * ht + id;
        ht = 31 * ht + name.hashCode();
        return ht;
    }
}
