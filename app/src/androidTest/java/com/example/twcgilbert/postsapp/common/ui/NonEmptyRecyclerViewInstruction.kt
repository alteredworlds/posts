package com.example.twcgilbert.postsapp.common.ui

import android.support.test.InstrumentationRegistry
import android.support.v7.widget.RecyclerView
import com.azimolabs.conditionwatcher.Instruction
import com.example.twcgilbert.postsapp.R
import com.example.twcgilbert.postsapp.app.TestApp

/**
 * Created by twcgilbert on 04/10/2017.
 */
class NonEmptyRecyclerViewInstruction : Instruction() {
    override fun getDescription() = "Wait for non-empty recyclerView"

    override fun checkCondition(): Boolean {
        var retVal = false
        val testApp = InstrumentationRegistry.getTargetContext().applicationContext as? TestApp
        if (null != testApp) {
            val recyclerView = testApp.currentActivity?.findViewById<RecyclerView>(R.id.recyclerView)
            if (null != recyclerView) {
                val adapter = recyclerView.adapter
                if (null != adapter) {
                    retVal = adapter.itemCount > 0
                }
            }
        }
        return retVal
    }
}