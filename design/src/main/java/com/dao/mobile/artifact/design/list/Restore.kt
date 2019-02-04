package com.dao.mobile.artifact.design.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



/**
 * Created in 04/02/19 14:20.
 *
 * @author Diogo Oliveira.
 */
@Parcelize
class Restore<T : Parcelable>(item: T, position: Int): Parcelable
{
}