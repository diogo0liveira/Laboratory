package com.dao.mobile.artifact.common.permission2

import androidx.annotation.StringRes
import java.lang.IllegalArgumentException

/**
 * Created in 18/03/19 16:39.
 *
 * @author Diogo Oliveira.
 */
data class RationalePermanentlyDenied(@StringRes var message: Int = STRING_INVALID, @StringRes var because: Int = STRING_INVALID)
{
    fun message(): Int
    {
        if(message == STRING_INVALID)
        {
            throw IllegalArgumentException("message não definida para \"RationalePermanentlyDenied\"")
        }

        return message
    }

    fun because(): Int
    {
        if(message == STRING_INVALID)
        {
            throw IllegalArgumentException("because não definida para \"RationalePermanentlyDenied\"")
        }

        return message
    }
}