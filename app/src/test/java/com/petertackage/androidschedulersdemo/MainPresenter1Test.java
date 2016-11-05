package com.petertackage.androidschedulersdemo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenter1Test {

    @Mock
    BookstoreModel bookstoreModel;

    @Mock
    BookView bookView;

    private MainPresenter1 presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(__ -> Schedulers.trampoline());

        presenter = new MainPresenter1(bookstoreModel, bookView);
    }

    @Test
    public void getFavoriteBook() {

        Book book = testBook();
        when(bookstoreModel.getFavoriteBook()).thenReturn(Observable.just(book));

        presenter.bind();

        verify(bookView).setBookTitle("The adventures of the Scheduler");
    }

    @NonNull
    private Book testBook() {
        return Book.create("The adventures of the Scheduler",
                           "RxJava",
                           "The wild story of how a RxJava Scheduler took on the world and won.\n"
                           + "Based on a true story.");
    }
}