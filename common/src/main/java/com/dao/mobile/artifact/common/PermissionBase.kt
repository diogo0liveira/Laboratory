package com.dao.mobile.artifact.common

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.view.View
import androidx.annotation.IntRange
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * Created in 12/02/19 15:33.
 *
 * @author Diogo Oliveira.
 */
private const val PREFERENCE_PERMISSION = "PREFERENCE_PERMISSION"
internal const val PERMISSION_CONTACTS_SHOW = "PERMISSION_CONTACTS_SHOW"
internal const val PERMISSION_CAMERA_SHOW = "PERMISSION_CAMERA_SHOW"
internal const val PERMISSION_STORAGE_SHOW = "PERMISSION_STORAGE_SHOW"
internal const val PERMISSION_LOCATION_SHOW = "PERMISSION_LOCATION_SHOW"

abstract class PermissionBase constructor(
    private val activity: AppCompatActivity?,
    private val fragment: Fragment?,
    private val anchor: View)
{
    val preferences: SharedPreferences = getContext().getSharedPreferences(PREFERENCE_PERMISSION, Context.MODE_PRIVATE)
    var repeat: Boolean = false

    private fun getContext(): Context
    {
        return (activity ?: fragment?.activity!!)
    }

   protected fun checkSelfPermission(permission: String): Boolean
    {
        return (ActivityCompat.checkSelfPermission(getContext(), permission) == PackageManager.PERMISSION_GRANTED)
    }

    protected fun grandPermission(permission: String, request: Int, callback: Callback)
    {
        if(shouldShowRequestPermissionRationale(permission))
        {
            if(repeat)
            {
                callback.showDialog()
                reportPreferences(PERMISSION_CAMERA_SHOW)
                repeat = false
            } else
            {
                requestPermissions(arrayOf(permission), request)
            }
        } else
        {
            if(preferences.getBoolean(PERMISSION_CONTACTS_SHOW, false))
            {
                callback.showSnackbar()
            } else
            {
                requestPermissions(arrayOf(permission), request)
            }
        }
    }

   private fun reportPreferences(key: String)
    {
        val editor = preferences.edit()
        editor.putBoolean(key, true)
        editor.apply()
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

    private fun showDialogExplicative(message: Int)
    {
        val builder = buildDialog()
        builder.setMessage(message)
        builder.setNegativeButton(R.string.permission_cmon_dialog_button_settings) { _, _ ->
            ApplicationController.getInstance().startDetailsSettings()
        }.show()
    }

    fun showSnackbarKnowMore(message: Int, because: Int)
    {
        Snackbar.make(anchor, message, Snackbar.LENGTH_LONG)
            .setAction(R.string.permission_cmon_snackbar_know_more) { showDialogExplicative(because) }.show()
    }

    fun shouldShowRequestPermissionRationale(permission: String): Boolean
    {
        activity?.let {
            return ActivityCompat.shouldShowRequestPermissionRationale(it, permission)
        }

        fragment?.let {
            return it.shouldShowRequestPermissionRationale(permission)
        }

        return false
    }

    fun requestPermissions(permissions: Array<String>, @IntRange(from = 0) requestCode: Int)
    {
        activity?.let {
            ActivityCompat.requestPermissions(it, permissions, requestCode)
        }

        fragment?.requestPermissions(permissions, requestCode)
    }

    protected interface Callback
    {
        fun showDialog()

        fun showSnackbar()
    }
}