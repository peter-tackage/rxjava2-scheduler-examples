package com.petertackage.rxjava2scheduling;

import com.petertackage.rxjava2scheduling.test.TrampolineSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest {

    @Mock
    private BookstoreModel bookstoreModel;

    @Mock
    private BookView bookView;

    private SchedulerProvider schedulerProvider;

    private MainPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        schedulerProvider = new TrampolineSchedulerProvider();
        presenter = new MainPresenter(bookstoreModel, bookView, schedulerProvider);
    }

    @Test
    public void bind_setsFavoriteBookTitle_whenGetFavoriteBookEmits() {
        Book book = Book.create("The adventures of the Scheduler");
        when(bookstoreModel.getFavoriteBook()).thenReturn(Observable.just(book));

        presenter.bind();

        verify(bookView).setBookTitle("The adventures of the Scheduler");
    }

}