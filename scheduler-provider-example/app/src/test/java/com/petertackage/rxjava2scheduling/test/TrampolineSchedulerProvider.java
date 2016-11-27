package com.petertackage.rxjava2scheduling.test;

import com.petertackage.rxjava2scheduling.SchedulerProvider;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * A {@link SchedulerProvider} for testing backed by {@link Schedulers#trampoline()}.
 */
public class TrampolineSchedulerProvider implements SchedulerProvider {

    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }
}
