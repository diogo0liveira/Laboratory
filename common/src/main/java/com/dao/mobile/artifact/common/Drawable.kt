package com.dao.mobile.artifact.common

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

/**
 * Created in 05/02/19 11:45.
 *
 * @author Diogo Oliveira.
 */
object Drawable
{
    /**
     * Colore um drawable.
     *
     * @param id    "id do drawable".
     * @param color "id da cor que será utilizada no drawable".
     *
     * @return (drawable na cor pretendida)
     */
    fun draw(@DrawableRes id: Int, @ColorRes color: Int): Drawable?
    {
        return get(id)?.apply {
            DrawableCompat.setTint(this, Color[color])
        }
    }

    /**
     * Recupera um drawable.
     *
     * @param id "id do drawable".
     *
     * @return (um drawable objeto)
     */
    operator fun get(@DrawableRes id: Int): Drawable?
    {
        return ContextCompat.getDrawable(ApplicationController.getInstance().getContext(), id)
    }
}
