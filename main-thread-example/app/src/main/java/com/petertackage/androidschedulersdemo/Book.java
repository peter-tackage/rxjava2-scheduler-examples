package com.petertackage.androidschedulersdemo;

class Book {

    private final String title;

    private Book(String title) {
        this.title = title;
    }

    static Book create(String title) {
        return new Book(title);
    }

    String getTitle() {
        return title;
    }

}
