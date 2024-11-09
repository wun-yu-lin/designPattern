package com.example.designpattern.mementoPattern;

import java.util.ArrayList;
import java.util.List;

public class HistoryCaretaker {
    private List<DocumentMemento> mementos;

    public HistoryCaretaker() {
        this.mementos = new ArrayList<>();
    }

    public void addMemento(DocumentMemento memento) {
        this.mementos.add(memento);
    }

    public DocumentMemento getMemento(int index) {
        return this.mementos.get(index);
    }

    public static void main(String[] args) {
        DocumentOriginator documentOriginator =  new DocumentOriginator("init");
        HistoryCaretaker historyCaretaker = new HistoryCaretaker();
        //save
        historyCaretaker.addMemento(documentOriginator.createMemento());

        //save
        documentOriginator.write("write something");
        historyCaretaker.addMemento(documentOriginator.createMemento());

        //save
        documentOriginator.write("more something");
        historyCaretaker.addMemento(documentOriginator.createMemento());

        System.out.println("content: " +  documentOriginator.getContent());

        //restore
        documentOriginator.restoreFromMemento(historyCaretaker.getMemento(1));
        System.out.println("restore content: " + documentOriginator.getContent());

        //restore
        documentOriginator.restoreFromMemento(historyCaretaker.getMemento(0));
        System.out.println("restore content: " + documentOriginator.getContent());

    }


}
