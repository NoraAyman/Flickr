package com.example.nora.flickrr;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

    private QueryUtils() {
    }

    public static List<ImageDetails> extractFeatureFromJson(String stringJSON) {

        if (TextUtils.isEmpty(stringJSON)) {
            return null;
        }

        List<ImageDetails> images = new ArrayList<>();
        try {

            JSONObject object = new JSONObject(stringJSON);
            JSONObject y= object.getJSONObject("photos");
            JSONArray imagesArray = y.getJSONArray("photo");

            for (int counter = 0; counter < imagesArray.length(); counter++) {

                JSONObject currentImage = imagesArray.getJSONObject(counter);


                long id = currentImage.getLong("id");
                String title = currentImage.getString("title");
                Uri x= Uri.parse(title);
                Log.e("fbgaevdgng", title);

                ImageDetails obj = new ImageDetails(x, id);
                images.add(obj);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the images JSON results", e);
        }

        return images;
    }

    private static URL createUrl(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e("", "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("", "Problem retrieving the images JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    public static List<ImageDetails> fetchData(String requestUrl) {

        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("", "Problem making the HTTP request.", e);
        }

        List<ImageDetails> images = extractFeatureFromJson(jsonResponse);

        return images;
    }
}