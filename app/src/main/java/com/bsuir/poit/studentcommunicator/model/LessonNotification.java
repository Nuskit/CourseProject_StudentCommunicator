package com.bsuir.poit.studentcommunicator.model;

import java.util.Date;

public class LessonNotification {
    private final Date time;
    private final String author;
    private final String description;

    public LessonNotification(Date time, String author, String description){
        this.time = time;
        this.author = author;
        this.description = description;
    }

    public Date getTime(){
        return time;
    }

    public String getAuthor(){
        return author;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public boolean equals(Object object){
        if (object == null){
            return false;
        }

        if (this == object){
            return true;
        }

        if (this.getClass() != object.getClass()){
            return false;
        }

        LessonNotification lessonNotification = (LessonNotification)object;
        return time.equals(lessonNotification.time)
                && author.equals(lessonNotification.author)
                && description.equals(lessonNotification.description);
    }

    @Override
    public int hashCode(){
        int ht = 17;
        ht = 31 * ht + time.hashCode();
        ht = 31 * ht + author.hashCode();
        ht = 31 * ht + description.hashCode();
        return ht;
    }
}
