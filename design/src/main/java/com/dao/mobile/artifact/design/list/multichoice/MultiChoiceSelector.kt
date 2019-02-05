package com.dao.mobile.artifact.design.list.multichoice

import android.os.Parcelable
import android.util.SparseBooleanArray
import kotlinx.android.parcel.Parcelize

/**
 * Created in 20/08/18 13:24.
 *
 * @author Diogo Oliveira.
 */
@Parcelize
class MultiChoiceSelector : Parcelable
{
    private var selecteds = SparseBooleanArray(0)
    private var countSelected = 0

    private fun setSelected(position: Int, selected: Boolean)
    {
        this.countSelected = if(selected) ++countSelected else --countSelected
        this.selecteds.append(position, selected)
    }

    fun isSelected(position: Int): Boolean
    {
        return this.selecteds.get(position)
    }

    fun setStateItem(position: Int): Boolean
    {
        val state = !isSelected(position)
        setSelected(position, state)
        return state
    }

    fun getItensSelected(): List<Int>
    {
        val ids = ArrayList<Int>(selecteds.size())

        for(i in 0 until selecteds.size())
        {
            if(selecteds.valueAt(i))
            {
                ids.add(selecteds.keyAt(i))
            }
        }

        return ids
    }

    fun getItensState(): SparseBooleanArray
    {
        return selecteds
    }

    fun countSelected(): Int
    {
        return countSelected
    }

    fun clear()
    {
        this.selecteds.clear()
        this.countSelected = 0
    }
}