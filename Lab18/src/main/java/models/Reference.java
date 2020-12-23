package models;


public class Reference {

    private int id;
    private String url;
    private String description;
    private int minus;
    private int plus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinus() {
        return minus;
    }

    public void setMinus(int minus) {
        this.minus = minus;
    }

    public int getPlus() {
        return plus;
    }

    public void setPlus(int plus) {
        this.plus = plus;
    }


    public Reference(int id, String url, String description, int minus, int plus) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.minus = minus;
        this.plus = plus;
    }
}
