package com.dao.mobile.artifact.common

import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import androidx.annotation.IntRange
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.dao.mobile.artifact.common.permission2.RationaleAccepted
import com.dao.mobile.artifact.common.permission2.RationalePermanentlyDenied
import com.google.android.material.snackbar.Snackbar

/**
 * Created in 18/03/19 15:18.
 *
 * @author Diogo Oliveira.
 */
class Permission2 private constructor(
    private val activity: AppCompatActivity?,
    private val fragment: Fragment?,
    private val anchor: View)
{
    constructor(activity: AppCompatActivity, anchor: View) : this(activity, null, anchor)

    constructor(fragment: Fragment, anchor: View) : this(null, fragment, anchor)

    private var justify: Boolean = false
    private lateinit var callback: Callback

    /**
     * Verifica se tem permissão.
     *
     * @return (true se permissão concedida)
     */
    fun hasPermissions(vararg permissions: String): Boolean
    {
        return permissions.all { (ActivityCompat.checkSelfPermission(getContext(), it) == PackageManager.PERMISSION_GRANTED) }
    }

    /**
     * Solicita a permissão para o usuário para utilizar a conta.
     */
    fun grandPermission(callback: Callback, requestCode: Int, vararg permissions: String)
    {
        val requestPermission = permissions.all { shouldShowRequestPermissionRationale(it) }
        this.callback = callback

        if(requestPermission || (!justify && !requestPermission))
        {
            if(justify)
            {
                val rationale = RationaleAccepted()
                callback.onRationaleAccepted(rationale, requestCode)
                showDialogInformative(rationale) { grandPermission(callback, requestCode, *permissions) }
                justify = false
            }
            else
            {
                requestPermissions(permissions, requestCode)
            }
        }
        else
        {
            val rationale = RationalePermanentlyDenied()
            callback.onPermissionPermanentlyDenied(rationale, requestCode)
            showSnackbarKnowMore(rationale)
        }
    }

    fun accepted(requestCode: Int, permissions: Array<out String>, grantResults: IntArray): Boolean
    {
        val requestPermissions = mutableListOf<String>()

        grantResults.forEachIndexed { index, value ->
            if(value == PackageManager.PERMISSION_DENIED)
            {
                requestPermissions.add(permissions[index])
            }
        }

        if(grantResults.isNotEmpty())
        {
            justify = true
            grandPermission(callback, requestCode, *requestPermissions.toTypedArray())
            return false
        }

        Snackbar.make(anchor, R.string.permission_cmon_accepted, Snackbar.LENGTH_SHORT).show()
        return true
    }

    private fun requestPermissions(permissions: Array<out String>, @IntRange(from = 0) requestCode: Int)
    {
        activity?.let {
            ActivityCompat.requestPermissions(it, permissions, requestCode)
        } ?: fragment?.requestPermissions(permissions, requestCode)
    }

    private fun shouldShowRequestPermissionRationale(permission: String): Boolean
    {
        activity?.let {
            return ActivityCompat.shouldShowRequestPermissionRationale(it, permission)
        } ?: fragment?.let {
            return it.shouldShowRequestPermissionRationale(permission)
        }

        return false
    }

    private fun showDialogInformative(rationale: RationaleAccepted, block: () -> Unit)
    {
        val builder = buildDialog()
        builder.setMessage(rationale.message())
        builder.setNegativeButton(R.string.permission_cmon_dialog_button_back) { _, _ -> block() }
        builder.show()
    }

    private fun showSnackbarKnowMore(rationale: RationalePermanentlyDenied)
    {
        Snackbar.make(anchor, rationale.message(), Snackbar.LENGTH_LONG)
            .setAction(R.string.permission_cmon_snackbar_know_more) { showDialogExplicative(rationale.because()) }.show()
    }

    private fun showDialogExplicative(message: Int)
    {
        val builder = buildDialog()
        builder.setMessage(message)
        builder.setNegativeButton(R.string.permission_cmon_dialog_button_settings) { _, _ ->
            ApplicationController.getInstance().startDetailsSettings()
        }.show()
    }

    private fun buildDialog(): AlertDialog.Builder
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
        fun onRationaleAccepted(rationale: RationaleAccepted, requestCode: Int)
        fun onPermissionPermanentlyDenied(rationale: RationalePermanentlyDenied, requestCode: Int)
    }
}