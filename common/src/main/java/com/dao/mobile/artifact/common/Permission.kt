package com.dao.mobile.artifact.common

import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dao.mobile.artifact.common.permission.*
import com.google.android.material.snackbar.Snackbar

/**
 * Created in 12/02/19 16:53.
 *
 * @author Diogo Oliveira.
 */
class Permission private constructor(activity: AppCompatActivity?, fragment: Fragment?, private val anchor: View)
{
    constructor(activity: AppCompatActivity, anchor: View) : this(activity, null, anchor)

    constructor(fragment: Fragment, anchor: View) : this(null, fragment, anchor)

    private val helper: PermissionHelper by lazy {
        PermissionHelper(
            activity,
            fragment
        )
    }

    /**
     * Verifica se tem permissão para acesso a conta.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionContacts(type: Contacts = Contacts.GET) = helper.checkSelfPermission(type.get())

    /**
     * Verifica se tem permissão para utilizar a camera.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionCamera() = helper.checkSelfPermission(CAMERA)

    /**
     * Verifica se tem permissão para utilizar a localização.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionLocation(type: Location = Location.ACCESS_FINE) = helper.checkSelfPermission(type.get())

    /**
     * Verifica se tem permissão para utilizar o microfone.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionMicrophone() = helper.checkSelfPermission(MICROPHONE)

    /**
     * Verifica se tem permissão para recursos de telefonia.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionPhone(type: Phone = Phone.CALL_PHONE) = helper.checkSelfPermission(type.get())

    /**
     * Verifica se tem permissão para acesso ao sensores.
     *
     * @return (true se permissão concedida)
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    fun isPermissionSensors() = helper.checkSelfPermission(SENSORS)

    /**
     * Verifica se tem permissão para recursos de SMS.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionSms(type: Sms = Sms.READ_SMS) = helper.checkSelfPermission(type.get())

    /**
     * Verifica se tem permissão para escrever no disco.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionStorage(type: Storage = Storage.READ) = helper.checkSelfPermission(type.get())

    /**
     * Solicita a permissão para o usuário para utilizar a conta.
     */
    fun contacts(type: Contacts = Contacts.GET)
    {
        helper.grandPermission(type.get(),
            REQUEST_CONTACTS, object :
                PermissionHelper.Callback
        {
            override fun showDialog()
            {
                showDialogInformative(R.string.permission_cmon_dialog_contacts,
                    REQUEST_CONTACTS
                )
            }

            override fun showSnackbar()
            {
                //@formatter:off
                showSnackbarKnowMore(R.string.permission_cmon_rationale_contacts,
                    R.string.permission_cmon_dialog_contacts_accept)
                //@formatter:on
            }
        })
    }

    /**
     * Solicita a permissão para o usuário para utilizar a camera.
     */
    fun camera()
    {
        helper.grandPermission(
            CAMERA,
            REQUEST_CAMERA, object :
                PermissionHelper.Callback
        {
            override fun showDialog()
            {
                showDialogInformative(R.string.permission_cmon_dialog_camera,
                    REQUEST_CAMERA
                )
            }

            override fun showSnackbar()
            {
                //@formatter:off
                showSnackbarKnowMore(R.string.permission_cmon_rationale_camera,
                    R.string.permission_cmon_dialog_camera_accept)
                //@formatter:on
            }
        })
    }

    /**
     * Solicita a permissão para o usuário para utilizar a localização.
     */
    fun location(type: Location = Location.ACCESS_FINE)
    {
        helper.grandPermission(type.get(),
            REQUEST_LOCATION, object :
                PermissionHelper.Callback
        {
            override fun showDialog()
            {
                showDialogInformative(R.string.permission_cmon_dialog_location,
                    REQUEST_LOCATION
                )
            }

            override fun showSnackbar()
            {
                //@formatter:off
                showSnackbarKnowMore(R.string.permission_cmon_rationale_location,
                    R.string.permission_cmon_dialog_location_accept)
                //@formatter:on
            }
        })
    }

    /**
     * Solicita a permissão para o usuário para escrever no disco.
     */
    fun storage(type: Storage = Storage.READ)
    {
        helper.grandPermission(type.get(),
            REQUEST_STORAGE, object :
                PermissionHelper.Callback
        {
            override fun showDialog()
            {
                showDialogInformative(R.string.permission_cmon_dialog_storage,
                    REQUEST_STORAGE
                )
            }

            override fun showSnackbar()
            {
                //@formatter:off
                showSnackbarKnowMore(R.string.permission_cmon_rationale_storage,
                    R.string.permission_cmon_dialog_storage_accept)
                //@formatter:on
            }
        })
    }

    /**
     * Solicita a permissão para o usuário para utilizar o microfone.
     */
    fun microphone()
    {
        helper.grandPermission(
            MICROPHONE,
            REQUEST_MICROPHONE, object :
                PermissionHelper.Callback
        {
            override fun showDialog()
            {
                showDialogInformative(R.string.permission_cmon_dialog_microphone,
                    REQUEST_MICROPHONE
                )
            }

            override fun showSnackbar()
            {
                //@formatter:off
                showSnackbarKnowMore(R.string.permission_cmon_rationale_microphone,
                    R.string.permission_cmon_dialog_microphone_accept)
                //@formatter:on
            }
        })
    }

    fun accepted(requestCode: Int, grantResults: IntArray): Boolean
    {
        if(grantResults.isEmpty())
        {
            return false
        }
        else
        {
            if(requestCode == REQUEST_MULTIPLE_PERMISSIONS)
            {
                for(result in grantResults)
                {
                    if(result != PackageManager.PERMISSION_GRANTED)
                    {
                        return false
                    }
                }
            }
            else
            {
                for(result in grantResults)
                {
                    helper.justify = result == PackageManager.PERMISSION_DENIED

                    when(requestCode)
                    {
                        REQUEST_CONTACTS ->
                        {
                            if(helper.justify)
                            {
                                contacts()
                                return false
                            }
                        }
                        REQUEST_CAMERA ->
                        {
                            if(helper.justify)
                            {
                                camera()
                                return false
                            }
                        }
                        REQUEST_LOCATION ->
                        {
                            if(helper.justify)
                            {
                                location()
                                return false
                            }
                        }
                        REQUEST_MICROPHONE ->
                        {
                            if(helper.justify)
                            {
                                microphone()
                                return false
                            }
                        }
                        REQUEST_STORAGE ->
                        {
                            if(helper.justify)
                            {
                                storage()
                                return false
                            }
                        }
                        else ->
                        {
                            return false
                        }
                    }
                }
            }

//            Snackbar.make(anchor, R.string.permission_cmon_accepted, Snackbar.LENGTH_SHORT).show()
            return true
        }
    }

    private fun showDialogExplicative(message: Int)
    {
        val builder = helper.buildDialog()
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

    private fun showDialogInformative(message: Int, request: Int)
    {
        val builder = helper.buildDialog()
        builder.setMessage(message)

        builder.setNegativeButton(R.string.permission_cmon_dialog_button_back) { _, _ ->
            when(request)
            {
                REQUEST_CONTACTS ->
                {
                    contacts()
                }
                REQUEST_CAMERA ->
                {
                    camera()
                }
                REQUEST_MICROPHONE ->
                {
                    microphone()
                }
                REQUEST_LOCATION ->
                {
                    location()
                }
                REQUEST_STORAGE ->
                {
                    storage()
                }
            }
        }

        builder.show()
    }

    companion object
    {
        private val resources = ApplicationController.getInstance().resources

        val REQUEST_CONTACTS = resources.getInteger(R.integer.cmon_request_camera)
        val REQUEST_CAMERA = resources.getInteger(R.integer.cmon_request_contacts)
        val REQUEST_LOCATION = resources.getInteger(R.integer.cmon_request_location)
        val REQUEST_STORAGE = resources.getInteger(R.integer.cmon_request_storage)
        val REQUEST_MICROPHONE = resources.getInteger(R.integer.cmon_request_microphone)
        val REQUEST_MULTIPLE_PERMISSIONS = resources.getInteger(R.integer.cmon_request_multi_permissions)
    }
}