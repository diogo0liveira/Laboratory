package com.dao.mobile.artifact.sqlite

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.dao.mobile.artifact.common.ApplicationController

/**
 * Created in 16/01/19 17:10.
 *
 * @author Diogo Oliveira.
 */
class TestAndroidJUnitRunner : AndroidJUnitRunner()
{
    @Throws(Exception::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application
    {
        return super.newApplication(cl, ApplicationController::class.java.name, context)
    }
}