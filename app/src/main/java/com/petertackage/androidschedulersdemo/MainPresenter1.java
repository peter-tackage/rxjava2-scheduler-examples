package com.petertackage.androidschedulersdemo;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class MainPresenter1 {

    private static final String TAG = MainPresenter1.class.getSimpleName();

    private final BookstoreModel bookstoreModel;
    private final BookView view;
    private final CompositeDisposable disposable = new CompositeDisposable();

    MainPresenter1(BookstoreModel bookstoreModel,
                   BookView view) {
        this.bookstoreModel = bookstoreModel;
        this.view = view;
    }

    void bind() {
        disposable.add(bookstoreModel.getFavoriteBook()
                                     .filter(book -> book.getDescription().contains("RxJava"))
                                     .map(Book::getTitle)
                                     .subscribeOn(Schedulers.computation())
                                     .observeOn(AndroidSchedulers.mainThread())
                                     .subscribe(view::setBookTitle,
                                                this::logError));
    }

    void unbind() {
        disposable.clear();
    }

    private void logError(final Throwable e) {
        Logger.getLogger(TAG)
              .log(Level.SEVERE,
                   "Error setting book text",
                   e);
    }
}
