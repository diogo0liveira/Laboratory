@file:Suppress("unused")

package com.dao.mobile.artifact.common

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_MAIN
import android.content.Intent.CATEGORY_HOME
import android.net.Uri
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask


/**
 * Created in 23/08/18 16:49.
 *
 * @author Diogo Oliveira.
 */
open class ApplicationController : Application()
{
    companion object
    {
        private lateinit var instance: ApplicationController

        @JvmStatic
        @Synchronized
        fun getInstance(): ApplicationController
        {
            return instance
        }
    }

    /**
     * Aplicação em execução.
     *
     * @return true quando a aplicação estiver em execução.
     */
    private var isRunning: Boolean = false

    override fun onCreate()
    {
        super.onCreate()
        instance = this
        Logger.d("ApplicationController initialize")
    }

    fun getContext(): Context
    {
        return instance.applicationContext
    }

    internal fun initiatedApplication()
    {
        isRunning = true
    }

    internal fun closedApplication()
    {
        isRunning = false
    }

    fun startDetailsSettings()
    {
        val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName"))

        intent.addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK and
                 Intent.FLAG_ACTIVITY_CLEAR_TASK and
                 Intent.FLAG_ACTIVITY_NO_HISTORY and
                 Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)

        startActivity(intent)
    }

    fun getVersion(): String
    {
        try
        {
            val info = getContext().packageManager.getPackageInfo(getContext().packageName, 0)
            return getString(R.string.app_cmon_version, info.versionName)
        }
        catch(e: Exception)
        {
            e.printStackTrace()
        }

        return ""
    }

    fun finish()
    {
        startActivity(intentFor<Any>().setAction(ACTION_MAIN).addCategory(CATEGORY_HOME).clearTop().newTask())

        try
        {
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(0)
        }
        catch(e: Exception)
        {
            e.printStackTrace()
        }
    }
}