package com.petertackage.rxjava2scheduling;

import com.petertackage.rxjava2scheduling.test.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest {

    @Mock
    private BookstoreModel bookstoreModel;

    @Mock
    private BookView bookView;

    private TestScheduler testScheduler;

    private MainPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testScheduler = new TestScheduler();
        presenter = new MainPresenter(bookstoreModel,
                                      bookView,
                                      new TestSchedulerProvider(testScheduler));
    }

    @Test
    public void bind_doesNotSetsFavoriteBookTitle_whenGetFavoriteBookEmits() {
        when(bookstoreModel.getFavoriteBook()).thenReturn(Observable.just(Book.create("title")));

        presenter.bind();
        testScheduler.triggerActions();

        verify(bookView, never()).setBookTitle(anyString());
    }

    @Test
    public void bind_setsFavoriteBookTitle_whenGetFavoriteBookEmits_afterDelay() {
        String title = "The adventures of the Scheduler";
        when(bookstoreModel.getFavoriteBook()).thenReturn(Observable.just(Book.create(title)));

        presenter.bind();
        testScheduler.advanceTimeBy(MainPresenter.DELAY_TIME_SEC, TimeUnit.SECONDS);
        testScheduler.triggerActions();

        verify(bookView).setBookTitle(title);
    }

}
