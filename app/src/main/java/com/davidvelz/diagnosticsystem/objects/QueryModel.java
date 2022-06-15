package com.davidvelz.diagnosticsystem.objects;

public class QueryModel {

    private String query_id;
    private String key;
    private String details;
    private String image;

    public QueryModel() {
    }

    public QueryModel(String query_id, String key, String details, String image) {
        this.query_id = query_id;
        this.key = key;
        this.details = details;
        this.image = image;
    }

    public String getQuery_id() {
        return query_id;
    }

    public String getKey() {
        return key;
    }

    public String getDetails() {
        return details;
    }

    public String getImage() {
        return image;
    }

    public void setQuery_id(String query_id) {
        this.query_id = query_id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
