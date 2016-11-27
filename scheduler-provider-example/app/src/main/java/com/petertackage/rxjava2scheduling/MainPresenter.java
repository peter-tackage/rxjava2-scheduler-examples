package com.petertackage.rxjava2scheduling;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;

final class MainPresenter {

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
                                     // Delay for dramatic effect!
                                     .delay(5, TimeUnit.SECONDS, schedulerProvider.computation())
                                     .observeOn(schedulerProvider.ui())
                                     .subscribe(view::setBookTitle));
    }

    void unbind() {
        disposable.clear();
    }

}
