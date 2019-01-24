package com.dao.mobile.laboratory.app.sqlite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dao.mobile.laboratory.app.R
import kotlinx.android.synthetic.main.activity_sqlite.*

/**
 * Created in 23/01/19 20:16.
 *
 * @author Diogo Oliveira.
 */
class SqliteActivity : AppCompatActivity(), View.OnClickListener
{
    private lateinit var userDataSource: UserDataSource

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        initializeView()
    }

    override fun onClick(view: View)
    {
        when(view.id)
        {
            R.id.buttonRun ->
            {
                runSimple()
            }
        }
    }

    private fun initializeView()
    {
        userDataSource = UserDataSource(this)
        buttonRun.setOnClickListener(this)
    }

    private fun runSimple()
    {
        userDataSource.getUsers()
    }
}