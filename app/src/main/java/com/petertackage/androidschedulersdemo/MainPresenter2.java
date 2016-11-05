package com.petertackage.androidschedulersdemo;

import java.util.logging.Level;
import java.util.logging.Logger;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Individual Schedulers are provided to the Presenter in the constructor.
 *
 * These can be modified as required by your test.
 */
class MainPresenter2 {

    private static final String TAG = MainPresenter2.class.getSimpleName();

    private final BookstoreModel bookstoreModel;
    private final BookView view;
    private final Scheduler computationScheduler;
    private final Scheduler mainThreadScheduler;
    private final CompositeDisposable disposable = new CompositeDisposable();

    MainPresenter2(BookstoreModel bookstoreModel,
                   BookView view,
                   Scheduler computationScheduler,
                   Scheduler mainThreadScheduler) {
        this.bookstoreModel = bookstoreModel;
        this.view = view;
        this.computationScheduler = computationScheduler;
        this.mainThreadScheduler = mainThreadScheduler;
    }

    void bind() {
        disposable.add(bookstoreModel.getFavoriteBook()
                                     .filter(book -> book.getDescription().contains("RxJava"))
                                     .map(Book::getTitle)
                                     .subscribeOn(computationScheduler)
                                     .observeOn(mainThreadScheduler)
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
