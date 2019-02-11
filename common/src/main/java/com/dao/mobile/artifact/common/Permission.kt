package com.dao.mobile.artifact.common

import android.Manifest
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
import java.util.*

const val CAMERA = Manifest.permission.CAMERA
//const val CONTACTS = Manifest.permission.READ_CONTACTS
const val LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
const val STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE

//private val PERMISSIONS_CONTACTS = arrayOf(CONTACTS)
private val PERMISSIONS_CAMERA = arrayOf(CAMERA)
private val PERMISSIONS_STORAGE = arrayOf(STORAGE)
private val PERMISSIONS_LOCATION = arrayOf(LOCATION)

private const val PREFERENCE_PERMISSION = "PREFERENCE_PERMISSION"
private const val PERMISSION_CONTACTS_SHOW = "PERMISSION_CONTACTS_SHOW"
private const val PERMISSION_CAMERA_SHOW = "PERMISSION_CAMERA_SHOW"
private const val PERMISSION_STORAGE_SHOW = "PERMISSION_STORAGE_SHOW"
private const val PERMISSION_LOCATION_SHOW = "PERMISSION_LOCATION_SHOW"

/**
 * Gerencia permissão em tempo de execução utilizado a partir da api 23.
 *
 * Created in 05/02/19 10:53.
 * @author Diogo Oliveira.
 */
class Permission private constructor(val activity: AppCompatActivity?, val fragment: Fragment?, private val anchor: View)
{
    private val preferences: SharedPreferences =
        getContext().getSharedPreferences(PREFERENCE_PERMISSION, Context.MODE_PRIVATE)
    private var repeat: Boolean = false

    constructor(activity: AppCompatActivity, anchor: View) : this(activity, null, anchor)

    constructor(fragment: Fragment, anchor: View) : this(null, fragment, anchor)

    /**
     * Verifica se tem permissão para acesso a conta.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionContacts(@PermissionType type: String) = checkSelfPermission(type)

    /**
     * Verifica se tem permissão para utilizar a camera.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionCamera() = checkSelfPermission(CAMERA)

    /**
     * Verifica se tem permissão para escrever no disco.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionStorage() = checkSelfPermission(STORAGE)

    /**
     * Verifica se tem permissão para utilizar a localização.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionLocation() = checkSelfPermission(LOCATION)

    /**
     * Solicita a permissão para o usuário para utilizar a conta.
     */
    fun contacts(type: String = Manifest.permission.GET_ACCOUNTS)
    {
        if(shouldShowRequestPermissionRationale(type))
        {
            if(repeat)
            {
                showDialogInformative(R.string.permission_cmon_dialog_contacts, REQUEST_CONTACTS, type)
                reportPreferences(PERMISSION_CAMERA_SHOW)
                repeat = false
            } else
            {
                requestPermissions(arrayOf(type), REQUEST_CONTACTS)
            }
        } else
        {
            if(preferences.getBoolean(PERMISSION_CONTACTS_SHOW, false))
            {
                showSnackbarKnowMore(
                    R.string.permission_cmon_rationale_account, R.string.permission_cmon_dialog_contacts_accept
                )
            } else
            {
                requestPermissions(arrayOf(type), REQUEST_CONTACTS)
            }
        }
    }

    /**
     * Solicita a permissão para o usuário para utilizar a camera.
     */
    fun camera()
    {
        if(shouldShowRequestPermissionRationale(CAMERA))
        {
            if(repeat)
            {
                showDialogInformative(R.string.permission_cmon_dialog_camera, REQUEST_CAMERA)
                reportPreferences(PERMISSION_CAMERA_SHOW)
                repeat = false
            } else
            {
                requestPermissions(PERMISSIONS_CAMERA, REQUEST_CAMERA)
            }
        } else
        {
            if(preferences.contains(PERMISSION_CAMERA_SHOW) && preferences.getBoolean(PERMISSION_CAMERA_SHOW, false))
            {
                showSnackbarKnowMore(
                    R.string.permission_cmon_rationale_camera, R.string.permission_cmon_dialog_camera_accept
                )
            } else
            {
                requestPermissions(PERMISSIONS_CAMERA, REQUEST_CAMERA)
            }
        }
    }

    /**
     * Solicita a permissão para o usuário para escrever no disco.
     */
    fun storage()
    {
        if(shouldShowRequestPermissionRationale(STORAGE))
        {
            if(repeat)
            {
                showDialogInformative(R.string.permission_cmon_dialog_storage, REQUEST_STORAGE)
                reportPreferences(PERMISSION_CAMERA_SHOW)
                repeat = false
            } else
            {
                requestPermissions(PERMISSIONS_STORAGE, REQUEST_STORAGE)
            }
        } else
        {
            if(preferences.getBoolean(PERMISSION_STORAGE_SHOW, false))
            {
                showSnackbarKnowMore(
                    R.string.permission_cmon_rationale_storage, R.string.permission_cmon_dialog_storage_accept
                )
            } else
            {
                requestPermissions(PERMISSIONS_STORAGE, REQUEST_STORAGE)
            }
        }
    }

    /**
     * Solicita a permissão para o usuário para utilizar a localização.
     */
    fun location()
    {
        if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION))
        {
            if(repeat)
            {
                showDialogInformative(R.string.permission_cmon_dialog_location, REQUEST_LOCATION)
                reportPreferences(PERMISSION_CAMERA_SHOW)
                repeat = false
            } else
            {
                requestPermissions(PERMISSIONS_LOCATION, REQUEST_LOCATION)
            }
        } else
        {
            if(preferences.getBoolean(PERMISSION_LOCATION_SHOW, false))
            {
                showSnackbarKnowMore(
                    R.string.permission_cmon_rationale_location, R.string.permission_cmon_dialog_location_accept
                )
            } else
            {
                requestPermissions(PERMISSIONS_LOCATION, REQUEST_LOCATION)
            }
        }
    }

    fun multiplePermissions(vararg permissions: String)
    {
        val listPermissionsNeeded = ArrayList<String>(permissions.size)

        for(permission in permissions)
        {
            if(checkSelfPermission(permission))
            {
                listPermissionsNeeded.add(permission)
            }
        }

        if(!listPermissionsNeeded.isEmpty())
        {
            requestPermissions(listPermissionsNeeded.toTypedArray(), REQUEST_MULTIPLE_PERMISSIONS)
        }
    }

    private fun getContext(): Context
    {
        return (activity ?: fragment?.activity!!)
    }

    private fun reportPreferences(key: String)
    {
        val editor = preferences.edit()
        editor.putBoolean(key, true)
        editor.apply()
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

    private fun showDialogExplicative(message: Int)
    {
        val builder = buildDialog()
        builder.setMessage(message)
        builder.setNegativeButton(R.string.permission_cmon_dialog_button_settings) { _, _ ->
            ApplicationController.getInstance().startDetailsSettings()
        }.show()
    }

    private fun showSnackbarKnowMore(message: Int, because: Int)
    {
        Snackbar.make(anchor, message, Snackbar.LENGTH_LONG)
            .setAction(R.string.permission_cmon_snackbar_know_more) { showDialogExplicative(because) }.show()
    }

    private fun showDialogInformative(message: Int, request: Int, type: String = "")
    {
        val builder = buildDialog()
        builder.setMessage(message)

        builder.setNegativeButton(R.string.permission_cmon_dialog_button_back) { _, _ ->
            when(request)
            {
                REQUEST_CONTACTS ->
                {
                    contacts(type)
                }
                REQUEST_CAMERA ->
                {
                    camera()
                }
                REQUEST_STORAGE ->
                {
                    storage()
                }
                REQUEST_LOCATION ->
                {
                    location()
                }
            }
        }

        builder.show()
    }

    private fun checkSelfPermission(permission: String): Boolean
    {
        return (ActivityCompat.checkSelfPermission(getContext(), permission) == PackageManager.PERMISSION_GRANTED)
    }

    private fun shouldShowRequestPermissionRationale(permission: String): Boolean
    {
        activity?.let {
            return ActivityCompat.shouldShowRequestPermissionRationale(it, permission)
        }

        fragment?.let {
            return it.shouldShowRequestPermissionRationale(permission)
        }

        return false
    }

    private fun requestPermissions(permissions: Array<String>, @IntRange(from = 0) requestCode: Int)
    {
        activity?.let {
            ActivityCompat.requestPermissions(it, permissions, requestCode)
        }

        fragment?.requestPermissions(permissions, requestCode)
    }

    companion object
    {
        private val resources = ApplicationController.getInstance().resources

        val REQUEST_CONTACTS = resources.getInteger(R.integer.cmon_request_camera)
        val REQUEST_CAMERA = resources.getInteger(R.integer.cmon_request_contacts)
        val REQUEST_LOCATION = resources.getInteger(R.integer.cmon_request_location)
        val REQUEST_STORAGE = resources.getInteger(R.integer.cmon_request_storage)
        val REQUEST_MULTIPLE_PERMISSIONS = resources.getInteger(R.integer.cmon_request_multi_permissions)
    }
}