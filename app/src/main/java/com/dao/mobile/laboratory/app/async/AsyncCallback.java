package com.dao.mobile.laboratory.app.async;

/**
 * Created in 06/11/18 12:42.
 *
 * @author Diogo Oliveira.
 */
interface AsyncCallback<Params, Result>
{
    void onPreExecute();

    @SuppressWarnings("unchecked")
    Result doInBackground(Params... params);

    @SuppressWarnings("unchecked")
    void onProgressUpdate(Progress progress);

    void onPostExecute(Result result);

    void onCancelled(Result result);
}
