package com.petertackage.rxjava2scheduling;

import android.support.annotation.VisibleForTesting;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;

final class MainPresenter {

    @VisibleForTesting
    static final long DELAY_TIME_SEC = 5;

    private final BookstoreModel bookstoreModel;
    private final BookView view;
    private final SchedulerProvider schedulerProvider;
    private final CompositeDisposable disposable = new CompositeDisposable();

    MainPresenter(BookstoreModel bookstoreModel,
                  BookView view,
                  SchedulerProvider schedulerProvider) {
        this.bookstoreModel = bookstoreModel;
        this.view = view;
        this.schedulerProvider = schedulerProvider;
    }

    void bind() {
        disposable.add(bookstoreModel.getFavoriteBook()
                                     .map(Book::getTitle)
                                     .delay(DELAY_TIME_SEC, TimeUnit.SECONDS,
                                            schedulerProvider.computation())
                                     .observeOn(schedulerProvider.ui())
                                     .subscribe(view::setBookTitle));
    }

    void unbind() {
        disposable.clear();
    }

}
