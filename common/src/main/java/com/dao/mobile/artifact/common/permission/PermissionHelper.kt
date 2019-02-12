package com.dao.mobile.artifact.common.permission

import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import androidx.annotation.IntRange
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.dao.mobile.artifact.common.PERMISSION_CAMERA_SHOW
import com.dao.mobile.artifact.common.PERMISSION_CONTACTS_SHOW
import com.dao.mobile.artifact.common.PermissionBase

/**
 * Created in 12/02/19 17:02.
 *
 * @author Diogo Oliveira.
 */
internal class PermissionHelper constructor(
    private val activity: AppCompatActivity?,
    private val fragment: Fragment?,
    private val anchor: View)
{

    fun grandPermission(permission: String, request: Int)
    {
        if(shouldShowRequestPermissionRationale(permission))
        {
            requestPermissions(arrayOf(permission), request)
        }
        else
        {

        }

//        if(shouldShowRequestPermissionRationale(permission))
//        {
//            if(repeat)
//            {
//                callback.showDialog()
//                reportPreferences(PERMISSION_CAMERA_SHOW)
//                repeat = false
//            }
//            else
//            {
//                requestPermissions(arrayOf(permission), request)
//            }
//        }
//        else
//        {
//            if(preferences.getBoolean(PERMISSION_CONTACTS_SHOW, false))
//            {
//                callback.showSnackbar()
//            }
//            else
//            {
//                requestPermissions(arrayOf(permission), request)
//            }
//        }
    }

    fun checkSelfPermission(permission: String): Boolean
    {
        return (ActivityCompat.checkSelfPermission(getContext(), permission) == PackageManager.PERMISSION_GRANTED)
    }

    private fun shouldShowRequestPermissionRationale(permission: String): Boolean
    {
        activity?.let {
            return ActivityCompat.shouldShowRequestPermissionRationale(it, permission)
        } ?:

        fragment?.let {
            return it.shouldShowRequestPermissionRationale(permission)
        }

        return false
    }

    private fun requestPermissions(permissions: Array<String>, @IntRange(from = 0) requestCode: Int)
    {
        activity?.let {
            ActivityCompat.requestPermissions(it, permissions, requestCode)
        } ?:

        fragment?.requestPermissions(permissions, requestCode)
    }

    private fun getContext(): Context
    {
        return (activity ?: fragment?.activity!!)
    }
}