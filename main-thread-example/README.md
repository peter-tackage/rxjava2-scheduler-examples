# Main Thread Example

Shows how to use `RxAndroidPlugins.setInitMainThreadSchedulerHandler` to allow overriding of the
default `AndroidSchedulers.mainThread` Scheduler in a test environment to prevent `java.lang.ExceptionInInitializerError
`.