package com.dao.mobile.laboratory.app.async;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created in 09/11/18 17:24.
 *
 * @author Diogo Oliveira.
 */
public class AsynchronousProviders
{
    public static <T extends Asynchronous> T of(Class<T> asyncClass, FragmentManager manager)
    {
        T asynchronous = null;

        try
        {
            asynchronous = asyncClass.newInstance();
            Asynchronous running = (Asynchronous)manager.findFragmentByTag(asynchronous.tag());

            if(running == null)
            {
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(asynchronous, asynchronous.tag()).commit();
            }
            else
            {
                //noinspection unchecked
                asynchronous = (T) running;
            }
        }
        catch(IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch(InstantiationException e)
        {
            e.printStackTrace();
        }

        return asynchronous;
    }
}
