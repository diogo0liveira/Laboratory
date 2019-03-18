package com.dao.mobile.artifact.common.permission2

import androidx.annotation.StringRes
import java.lang.IllegalArgumentException

/**
 * Created in 18/03/19 16:38.
 *
 * @author Diogo Oliveira.
 */
data class RationaleAccepted(@StringRes var message: Int = STRING_INVALID)
{
    fun message(): Int
    {
        if(message == STRING_INVALID)
        {
            throw IllegalArgumentException("message não definida para \"RationaleAccepted\"")
        }

        return message
    }
}