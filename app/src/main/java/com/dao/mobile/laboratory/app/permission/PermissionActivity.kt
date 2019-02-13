package com.dao.mobile.laboratory.app.permission

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dao.mobile.artifact.common.Permission
import com.dao.mobile.laboratory.app.R
import kotlinx.android.synthetic.main.activity_permission.*

/**
 * Created in 12/02/19 18:24.
 *
 * @author Diogo Oliveira.
 */
class PermissionActivity : AppCompatActivity(), View.OnClickListener
{
    private val permission: Permission by lazy { Permission(this, container) }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        initializeView()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val granted = permission.accepted(requestCode, permissions, grantResults)

        if(granted)
        {
            grantPermission()
        }
    }

    override fun onClick(view: View)
    {
        when(view.id)
        {
            R.id.buttonGrant ->
            {
                grantPermission()
            }
        }
    }

    private fun initializeView()
    {
        buttonGrant.setOnClickListener(this)
    }

    private fun grantPermission()
    {
        if(permission.hasPermissionContacts())
        {
            textLabelHasPermission.visibility = View.VISIBLE
        }
        else
        {
            permission.
        }
    }
}

