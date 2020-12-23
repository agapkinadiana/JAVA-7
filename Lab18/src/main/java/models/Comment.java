package models;

import java.sql.Date;


public class Comment {

    private int id;
    private int referenceId;
    private String sessionId;
    private Date stamp;
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getStamp() {
        return stamp;
    }

    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public Comment(int id, int referenceId, String sessionId, Date stamp, String comment) {
        this.id = id;
        this.referenceId = referenceId;
        this.sessionId = sessionId;
        this.stamp = stamp;
        this.comment = comment;
    }
}
