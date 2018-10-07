package com.gptm.app.utility;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class HttpConnection {

    /**
     * A static method to make GET API calls.
     *
     * @param link  <p> The API call that needs to made. </p>
     * @return  <p> The API result. </p>
     * @throws Exception    <p> Multiple Exceptions needed to be handled. </p>
     */
    public static String httpGetConnection(String link) throws Exception   {
        return httpData(link, 1);
    }

    /**
     * A static method to make all API calls.
     *
     * @param link  <p> The API call that needs to made. </p>
     * @return  <p> The API result. </p>
     * @throws Exception    <p> Multiple Exceptions needed to be handled. </p>
     */
    public static String httpConnection(String link) throws Exception   {
        return httpData(link, 0);
    }

    private static String httpData(String link, int i) throws Exception {
        String result;

        Log.i("Link", link);

        URL urls = new URL(link);
        HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
        conn.setReadTimeout(150000); //milliseconds
        conn.setConnectTimeout(15000); // milliseconds
        conn.setRequestMethod("POST");

        if ((i == 0))
            conn.setRequestMethod("POST");
        else
            conn.setRequestMethod("GET");

        conn.connect();

        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null)
                sb.append(line);

            result = String.valueOf(sb);

            Log.i("Result", result);

            return result;
        } else {
            throw new UnknownHostException("Unsuccessful Connection");
        }
    }
}