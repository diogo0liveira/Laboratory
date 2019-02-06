package com.dao.mobile.artifact.common

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * Created in 05/02/19 11:46.
 *
 * @author Diogo Oliveira.
 */
object Color
{
    operator fun get(@ColorRes color: Int): Int
    {
        return ContextCompat.getColor(ApplicationController.getInstance().getContext(), color)
    }
}