package com.dao.mobile.laboratory.app.async;

import android.support.annotation.NonNull;

import java.util.Locale;

/**
 * Created in 08/11/18 11:16.
 *
 * @author Diogo Oliveira.
 */
public class Progress
{
    private int percentage;
    private String message;
    private boolean isFailed;

    public Progress(int percentage, String message)
    {
        this.percentage = percentage;
        this.message = message;
    }

    public int getPercentage()
    {
        return percentage;
    }

    public void setPercentage(int percentage)
    {
        this.percentage = percentage;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public boolean isFailed() {
        return isFailed;
    }

    public void setFailed(boolean failed) {
        isFailed = failed;
    }

    @NonNull
    @Override
    public String toString()
    {
        return (String.format("%1$s - %2$s",
                String.format(Locale.getDefault(), "%1$d%%", percentage), message));
    }
}
