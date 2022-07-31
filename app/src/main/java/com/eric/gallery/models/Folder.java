package com.eric.gallery.models;

public class Folder {
    private final String name;
    private final String path;
    private final int id;

    public Folder(String path, String name,int id) {
        this.path = path;
        this.name = name;
        this.id = id;
    }

    public String getPath() {
        return path;
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
