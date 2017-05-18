package com.example.darko.myapplication;

/**
 * Created by Darko on 11/6/2016.
 */

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static com.example.darko.myapplication.Certificate_Auth.DO_NOT_VERIFY;


class API_Signup extends AsyncTask<String, Object, String> {

    private String result;


    @Override
    protected void onPreExecute() {
    }

    protected String doInBackground(String... params) {
        URL url;
        HttpsURLConnection https = null;
        String line;


        //Connects to server to pull up the requested Players's stats.
        try {
            url = new URL("http://52.14.155.129/project/signup.php.");
            HttpURLConnection http = null;

            Certificate_Auth.trustAllHosts();
            https = (HttpsURLConnection) url.openConnection();
            https.setHostnameVerifier(DO_NOT_VERIFY);


            https.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(https.getOutputStream());
            StringBuilder sBuilder = new StringBuilder();
            //Pass the position and team abbreviation.
            writer.write("&email=" + params[0]);
            writer.write("&passcode=" + params[1]);
            writer.write("&name=" + params[2]);
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(https.getInputStream()));


            while ((line = reader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }
            result = sBuilder.toString();
            writer.close();
            reader.close();
        }

        catch (MalformedURLException e) {
            System.out.print(e);
        }
        catch (IOException e) {
            System.out.print(e);
        } finally  {
            https.disconnect();
        }

        System.out.print("RESULT: " + result);
        return result;
    }

    public void onPostExecute(String result) {
    }

}

