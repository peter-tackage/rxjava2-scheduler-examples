package com.petertackage.androidschedulersdemo;

import java.util.logging.Level;
import java.util.logging.Logger;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Individual Schedulers are provided to the Presenter in the constructor.
 *
 * These
 */
class MainPresenter3 {

    private static final String TAG = MainPresenter3.class.getSimpleName();

    private final BookstoreModel bookstoreModel;
    private final BookView view;
    private final SchedulerProvider schedulerProvider;
    private final CompositeDisposable disposable = new CompositeDisposable();

    MainPresenter3(BookstoreModel bookstoreModel,
                   BookView view,
                   SchedulerProvider schedulerProvider) {
        this.bookstoreModel = bookstoreModel;
        this.view = view;
        this.schedulerProvider = schedulerProvider;
    }

    void bind() {
        disposable.add(bookstoreModel.getFavoriteBook()
                                     .filter(book -> book.getDescription().contains("RxJava"))
                                     .map(Book::getTitle)
                                     .subscribeOn(schedulerProvider.computation())
                                     .observeOn(schedulerProvider.mainThread())
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
