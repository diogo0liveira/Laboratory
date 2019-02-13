package com.dao.mobile.artifact.common.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.annotation.IntRange
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.dao.mobile.artifact.common.Drawable
import com.dao.mobile.artifact.common.R

/**
 * Created in 12/02/19 17:02.
 *
 * @author Diogo Oliveira.
 */
internal class PermissionHelper constructor(
    private val activity: AppCompatActivity?,
    private val fragment: Fragment?)
{
    var justify: Boolean = false

    fun grandPermission(vararg permission: String, request: Int, callback: Callback)
    {
        val requestPermission = shouldShowRequestPermissionRationale(permission)

        if(requestPermission || (!justify && !requestPermission))
        {
            if(justify)
            {
                callback.showDialog()
                justify = false
            }
            else
            {
                requestPermissions(permission, request)
            }
        }
        else
        {
            callback.showSnackbar()
        }
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

    private fun requestPermissions(permissions: Array<out String>, @IntRange(from = 0) requestCode: Int)
    {
        activity?.let {
            ActivityCompat.requestPermissions(it, permissions, requestCode)
        } ?:

        fragment?.requestPermissions(permissions, requestCode)
    }

    fun buildDialog(): AlertDialog.Builder
    {
        val builder = AlertDialog.Builder(getContext())
        builder.setIcon(Drawable.draw(R.drawable.vd_cmon_warning_24dp, R.color.alert_yellow))
        builder.setTitle(R.string.permission_cmon_dialog_title)
        builder.setPositiveButton(android.R.string.ok, null)
        builder.setCancelable(false)
        return builder
    }

    private fun getContext(): Context
    {
        return (activity ?: fragment?.activity!!)
    }

    interface Callback
    {
        fun showDialog()

        fun showSnackbar()
    }
}