package com.petertackage.androidschedulersdemo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

final class MainPresenter {

    private final BookstoreModel bookstoreModel;
    private final BookView view;
    private final CompositeDisposable disposable = new CompositeDisposable();

    MainPresenter(BookstoreModel bookstoreModel,
                  BookView view) {
        this.bookstoreModel = bookstoreModel;
        this.view = view;
    }

    void bind() {
        disposable.add(bookstoreModel.getFavoriteBook()
                                     .map(Book::getTitle)
                                     .observeOn(AndroidSchedulers.mainThread())
                                     .subscribe(view::setBookTitle));
    }

    void unbind() {
        disposable.clear();
    }

}
