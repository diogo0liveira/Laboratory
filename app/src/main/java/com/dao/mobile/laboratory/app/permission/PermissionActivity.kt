package com.dao.mobile.laboratory.app.permission

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dao.mobile.artifact.common.Permission
import com.dao.mobile.artifact.common.Permission2
import com.dao.mobile.artifact.common.permission.Camera
import com.dao.mobile.artifact.common.permission.Contacts
import com.dao.mobile.artifact.common.permission.Storage
import com.dao.mobile.artifact.common.permission2.RationaleAccepted
import com.dao.mobile.artifact.common.permission2.RationalePermanentlyDenied
import com.dao.mobile.laboratory.app.R
import kotlinx.android.synthetic.main.activity_permission.*

/**
 * Created in 12/02/19 18:24.
 *
 * @author Diogo Oliveira.
 */
private const val REQUEST_PERMISSION_CONTACTS = 1001

class PermissionActivity : AppCompatActivity(), View.OnClickListener, Permission2.Callback
{
    private val permission: Permission by lazy { Permission(this, container) }
    private val permission2: Permission2 by lazy { Permission2(this, container) }

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
        val granted2 = permission2.accepted(requestCode, permissions, grantResults)

        if(granted || granted2)
        {
            grantPermission()
        }
    }

    override fun onPermissionPermanentlyDenied(rationale: RationalePermanentlyDenied, requestCode: Int)
    {
        when(requestCode)
        {
            REQUEST_PERMISSION_CONTACTS ->
            {
                rationale.message = R.string.permission_rationale_contacts
                rationale.because = R.string.permission_contacts_accept
            }
        }
    }

    override fun onRationaleAccepted(rationale: RationaleAccepted, requestCode: Int)
    {
        when(requestCode)
        {
            REQUEST_PERMISSION_CONTACTS ->
            {
                rationale.message = R.string.permission_contacts
            }
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
            R.id.buttonGrantMultiple ->
            {
                grantMultiplePermission()
            }
            R.id.buttonGrantPermission2 ->
            {
                grantPermission2()
            }
        }
    }

    private fun initializeView()
    {
        buttonGrant.setOnClickListener(this)
        buttonGrantMultiple.setOnClickListener(this)
        buttonGrantPermission2.setOnClickListener(this)
    }

    private fun grantPermission()
    {
        if(permission.hasPermissionContacts())
        {
            textLabelHasPermission.visibility = View.VISIBLE
        }
        else
        {
            permission.contacts(Contacts.GET)
        }
    }

    private fun grantMultiplePermission()
    {
        if(permission.hasMultiplePermissions(Contacts.GET, Camera.OPEN, Storage.READ))
        {
            textLabelHasPermission.visibility = View.VISIBLE
        }
        else
        {
            permission.multiplePermissions(Contacts.GET, Camera.OPEN, Storage.READ)
        }
    }

    private fun grantPermission2()
    {
        if(permission2.hasPermissions(Manifest.permission.GET_ACCOUNTS))
        {
            textLabelHasPermission.visibility = View.VISIBLE
        }
        else
        {
            permission2.grandPermission(this, REQUEST_PERMISSION_CONTACTS, Manifest.permission.GET_ACCOUNTS)
        }
    }
}

