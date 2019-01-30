package com.dao.mobile.laboratory.app.async;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

/**
 * Created in 12/11/18 14:14.
 *
 * @author Diogo Oliveira.
 */
public interface Conclude
{
    void cancel();

    boolean isAttach();

    void finish(@NonNull FragmentManager manager);
}
