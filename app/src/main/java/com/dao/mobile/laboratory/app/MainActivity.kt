package com.dao.mobile.laboratory.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dao.mobile.laboratory.app.sqlite.SqliteActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created in 15/01/19 20:46.
 *
 * @author Diogo Oliveira.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
    }

    override fun onClick(view: View)
    {
        when(view.id)
        {
            R.id.buttonSqlite ->
            {
                startActivity(Intent(this, SqliteActivity::class.java))
            }
        }
    }

    private fun initializeView()
    {
        buttonSqlite.setOnClickListener(this)
    }
}
