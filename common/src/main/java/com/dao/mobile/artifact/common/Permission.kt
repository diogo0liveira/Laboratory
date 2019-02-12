package com.dao.mobile.artifact.common

import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.*


/**
 * Gerencia permissão em tempo de execução utilizado a partir da api 23.
 *
 * Created in 05/02/19 10:53.
 * @author Diogo Oliveira.
 */
class Permission private constructor(activity: AppCompatActivity?, fragment: Fragment?, anchor: View) : PermissionBase(activity, fragment, anchor)
{
    constructor(activity: AppCompatActivity, anchor: View) : this(activity, null, anchor)

    constructor(fragment: Fragment, anchor: View) : this(null, fragment, anchor)

    /**
     * Verifica se tem permissão para acesso a conta.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionCalendar(type: Calendar = Calendar.READ) = checkSelfPermission(type.get())

    /**
     * Verifica se tem permissão para acesso a conta.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionContacts(type: Contacts = Contacts.GET) = checkSelfPermission(type.get())

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
    fun isPermissionStorage(type: Storage = Storage.READ) = checkSelfPermission(type.get())

    /**
     * Verifica se tem permissão para utilizar a localização.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionLocation(type: Location = Location.ACCESS_FINE) = checkSelfPermission(type.get())

    /**
     * Verifica se tem permissão para utilizar o microfone.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionMicrophone() = checkSelfPermission(MICROPHONE)

    /**
     * Verifica se tem permissão para recursos de telefonia.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionPhone(type: Phone = Phone.CALL_PHONE) = checkSelfPermission(type.get())

    /**
     * Verifica se tem permissão para acesso ao sensores.
     *
     * @return (true se permissão concedida)
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    fun isPermissionSensors() = checkSelfPermission(SENSORS)

    /**
     * Verifica se tem permissão para recursos de SMS.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionSms(type: Sms = Sms.READ_SMS) = checkSelfPermission(type.get())

    /**
     * Solicita a permissão para o usuário para utilizar a conta.
     */
    fun contacts(type: Contacts = Contacts.GET)
    {
        grandPermission(type.get(), REQUEST_CONTACTS, object : Callback
        {
            override fun showDialog()
            {
//                showDialogInformative(R.string.permission_cmon_dialog_contacts, REQUEST_CONTACTS, type)
            }

            override fun showSnackbar()
            {
                showSnackbarKnowMore(R.string.permission_cmon_rationale_account, R.string.permission_cmon_dialog_contacts_accept)
            }
        })

//        if(shouldShowRequestPermissionRationale(type.get()))
//        {
//            if(repeat)
//            {
//                showDialogInformative(R.string.permission_cmon_dialog_contacts, REQUEST_CONTACTS, type)
//                reportPreferences(PERMISSION_CAMERA_SHOW)
//                repeat = false
//            } else
//            {
//                requestPermissions(arrayOf(type.get()), REQUEST_CONTACTS)
//            }
//        } else
//        {
//            if(preferences.getBoolean(PERMISSION_CONTACTS_SHOW, false))
//            {
//                showSnackbarKnowMore(R.string.permission_cmon_rationale_account,
//                    R.string.permission_cmon_dialog_contacts_accept)
//            } else
//            {
//                requestPermissions(arrayOf(type.get()), REQUEST_CONTACTS)
//            }
//        }
    }

//    /**
//     * Solicita a permissão para o usuário para utilizar a camera.
//     */
//    fun camera()
//    {
//        if(shouldShowRequestPermissionRationale(CAMERA))
//        {
//            if(repeat)
//            {
//                showDialogInformative(R.string.permission_cmon_dialog_camera, REQUEST_CAMERA)
//                reportPreferences(PERMISSION_CAMERA_SHOW)
//                repeat = false
//            } else
//            {
//                requestPermissions(arrayOf(CAMERA), REQUEST_CAMERA)
//            }
//        } else
//        {
//            if(preferences.contains(PERMISSION_CAMERA_SHOW) && preferences.getBoolean(PERMISSION_CAMERA_SHOW, false))
//            {
//                showSnackbarKnowMore(R.string.permission_cmon_rationale_camera,
//                    R.string.permission_cmon_dialog_camera_accept)
//            } else
//            {
//                requestPermissions(arrayOf(CAMERA), REQUEST_CAMERA)
//            }
//        }
//    }
//
//    /**
//     * Solicita a permissão para o usuário para escrever no disco.
//     */
//    fun storage(type: Storage = Storage.READ)
//    {
//        if(shouldShowRequestPermissionRationale(type.get()))
//        {
//            if(repeat)
//            {
//                showDialogInformative(R.string.permission_cmon_dialog_storage, REQUEST_STORAGE, type)
//                reportPreferences(PERMISSION_CAMERA_SHOW)
//                repeat = false
//            } else
//            {
//                requestPermissions(arrayOf(type.get()), REQUEST_STORAGE)
//            }
//        } else
//        {
//            if(preferences.getBoolean(PERMISSION_STORAGE_SHOW, false))
//            {
//                showSnackbarKnowMore(R.string.permission_cmon_rationale_storage,
//                    R.string.permission_cmon_dialog_storage_accept)
//            } else
//            {
//                requestPermissions(arrayOf(type.get()), REQUEST_STORAGE)
//            }
//        }
//    }
//
//    /**
//     * Solicita a permissão para o usuário para utilizar a localização.
//     */
//    fun location(type: Location = Location.ACCESS_FINE)
//    {
//        if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION))
//        {
//            if(repeat)
//            {
//                showDialogInformative(R.string.permission_cmon_dialog_location, REQUEST_LOCATION, type)
//                reportPreferences(PERMISSION_CAMERA_SHOW)
//                repeat = false
//            } else
//            {
//                requestPermissions(arrayOf(type.get()), REQUEST_LOCATION)
//            }
//        } else
//        {
//            if(preferences.getBoolean(PERMISSION_LOCATION_SHOW, false))
//            {
//                showSnackbarKnowMore(R.string.permission_cmon_rationale_location,
//                    R.string.permission_cmon_dialog_location_accept)
//            } else
//            {
//                requestPermissions(arrayOf(type.get()), REQUEST_LOCATION)
//            }
//        }
//    }

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
                    repeat = result != PackageManager.PERMISSION_GRANTED

                    when(requestCode)
                    {
                        REQUEST_CONTACTS ->
                        {
                            if(repeat)
                            {
                                contacts()
                                return false
                            }
                            else
                            {
                                preferences.edit().remove(PERMISSION_CONTACTS_SHOW).apply()
                            }
                        }
//                        REQUEST_CAMERA ->
//                        {
//                            if(repeat)
//                            {
//                                camera()
//                                return false
//                            }
//                            else
//                            {
//                                preferences.edit().remove(PERMISSION_CAMERA_SHOW).apply()
//                                break
//                            }
//                        }
//                        REQUEST_STORAGE ->
//                        {
//                            if(repeat)
//                            {
//                                storage()
//                                return false
//                            }
//                            else
//                            {
//                                preferences.edit().remove(PERMISSION_STORAGE_SHOW).apply()
//                                                            }
//                        }
//                        REQUEST_LOCATION ->
//                        {
//                            if(repeat)
//                            {
//                                location()
//                                return false
//                            }
//                            else
//                            {
//                                preferences.edit().remove(PERMISSION_LOCATION_SHOW).apply()
//                                                            }
//                        }
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

    private fun showDialogInformative(message: Int, request: Int, type: Type = Type.None)
    {
        val builder = buildDialog()
        builder.setMessage(message)

//        builder.setNegativeButton(R.string.permission_cmon_dialog_button_back) { _, _ ->
//            when(request)
//            {
//                REQUEST_CONTACTS ->
//                {
//                    contacts(Contacts.valueOf(type.get()))
//                }
//                REQUEST_CAMERA ->
//                {
////                    camera()
//                }
//                REQUEST_STORAGE ->
//                {
////                    storage(Storage.valueOf(type.get()))
//                }
//                REQUEST_LOCATION ->
//                {
////                    location(Location.valueOf(type.get()))
//                }
//            }
//        }

        builder.show()
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