package com.example.twcgilbert.postsapp.ui

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import java.util.concurrent.TimeUnit

/**
 * Created by twcgilbert on 23/10/2017.
 */
open class PostTestBase {
    companion object {
        @BeforeClass
        @JvmStatic
        fun setup() {
            // things to execute once and keep around for the class
            // override main thread scheduler
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { ignore -> Schedulers.trampoline() }
        }

        @AfterClass
        @JvmStatic
        fun teardown() {
            // clean up after this class, leave nothing dirty behind
            RxAndroidPlugins.setInitMainThreadSchedulerHandler(null)
        }
    }

    protected val testScheduler = TestScheduler()

    open fun cleanup() {}

    @Before
    fun before() {
        // set calls to Schedulers.computation() to use our test scheduler
        RxJavaPlugins.setComputationSchedulerHandler { ignore -> testScheduler }
        // set calls to Schedulers.io() to use our test scheduler
        RxJavaPlugins.setIoSchedulerHandler { ignore -> testScheduler }
        // set calls to AndroidSchedulers.mainThread() to use our test scheduler
        RxAndroidPlugins.setMainThreadSchedulerHandler { testScheduler }

        testScheduler.advanceTimeTo(0, TimeUnit.SECONDS)
    }

    @After
    fun after() {
        cleanup()
        // restore default behaviour for Schedulers
        RxAndroidPlugins.setMainThreadSchedulerHandler { null }
        RxJavaPlugins.setComputationSchedulerHandler(null)
        RxJavaPlugins.setIoSchedulerHandler(null)
    }
}