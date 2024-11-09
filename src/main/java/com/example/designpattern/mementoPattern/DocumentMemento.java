package com.example.designpattern.mementoPattern;

public class DocumentMemento {

    private String content;

    public DocumentMemento(String content) {
        this.content = content;
    }

    public String getSavedContent() {
        return this.content;
    }
}
