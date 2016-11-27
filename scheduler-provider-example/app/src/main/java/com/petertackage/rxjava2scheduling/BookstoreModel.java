package com.petertackage.rxjava2scheduling;

import io.reactivex.Observable;

interface BookstoreModel {

    Observable<Book> getFavoriteBook();
}
