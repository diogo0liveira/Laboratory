package com.dao.mobile.laboratory.app

import android.app.Application
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService

/**
 * Created in 23/01/19 22:11.
 *
 * @author Diogo Oliveira.
 */
class AppController: Application()
{
    override fun onCreate()
    {
        super.onCreate()

        if(BuildConfig.DEBUG)
        {
            SQLiteStudioService.instance().start(this)
        }
    }

    override fun onTerminate() {
        SQLiteStudioService.instance().stop()
        super.onTerminate()
    }
}