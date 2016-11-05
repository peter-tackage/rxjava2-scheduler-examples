package com.petertackage.androidschedulersdemo;

import io.reactivex.Scheduler;

interface SchedulerProvider {

    Scheduler computation();

    Scheduler mainThread();

    // Add other Schedulers as required.
}
