package com.petertackage.androidschedulersdemo;

class Book {

    private final String title;
    private final String author;
    private final String description;

    private Book(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }

    static Book create(String title, String author, String description) {
        return new Book(title, author, description);
    }

    String getTitle() {
        return title;
    }

    String getAuthor() {
        return author;
    }

    String getDescription() {
        return description;
    }
}
