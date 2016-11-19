package com.petertackage.androidschedulersdemo;

import io.reactivex.Observable;

interface BookstoreModel {

    Observable<Book> getFavoriteBook();
}
