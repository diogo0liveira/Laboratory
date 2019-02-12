package com.dao.mobile.laboratory.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dao.mobile.artifact.common.permission.Permission
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created in 15/01/19 20:46.
 *
 * @author Diogo Oliveira.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener
{
    private val permission: Permission by lazy { Permission(this, container) }

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
//                startActivity(Intent(this, SqliteActivity::class.java))

                if(permission.isPermissionContacts())
                {
                }
                else
                {
                    permission.contacts()
                }
            }
        }
    }

    private fun initializeView()
    {
        buttonSqlite.setOnClickListener(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        permission.accepted(requestCode, grantResults)
    }
}
