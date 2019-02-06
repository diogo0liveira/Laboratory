package com.dao.mobile.laboratory.app.sqlite.data

/**
 * Created in 17/01/19 09:26.
 *
 * @author Diogo Oliveira.
 */
data class User(val id: Int, val name: String)
{
    override fun toString() = "$id = $name"
}