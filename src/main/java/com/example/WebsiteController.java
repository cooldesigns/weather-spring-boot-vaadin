package com.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class used to scrape and collect information from the website in order to get the 5 day forcast.
 * along with any other information needed for program.
 *
 * Created by raphael on 1/27/15.
 */
public class WebsiteController {

    private final String WUNDERGROUND_KEY = "3cfd31d747b993d5";
    private final String WEATHER_UNDERGROUND = "http://api.wunderground.com/api/";


    private String[] argument;
    private String[] queryArgs;
    private String apiHttp = null;
    private JsonObject jsonObject;


    /**
     * HttpQuery Builder: Uses weather underground API to retrieve address
     * for wunderground.json data
     */
    public void HttpQueryBuilder(){
        setApiHttp(WEATHER_UNDERGROUND + WUNDERGROUND_KEY + "/conditions/q/" + queryArgs[1] + "/" + queryArgs[0] + ".json");
        //System.out.println(getApiHttp());
    }

    /**
     * jParse:
     *
     * method jParse uses the Gson library
     *
     * uses the included connection method and parse the Json data into useful analytics
     * for the user. Currently (ver 1.0) outputs and sends useful data to Output Class
     * which formats the data into a text based printout.
     *
     * @see com.example.Output
     * @see com.google.gson.Gson
     * @return JsonObject
     */
    public JsonObject jParse(){
        String json = null;
        try {
            json = connectWunderground(getApiHttp());;
        } catch (IOException e) {
            e.printStackTrace();
        }
        setJsonObject((JsonObject) new JsonParser().parse(json));
        JsonObject jObj = getJsonObject();

        //System.out.println("jObj:" + jObj.get("current_observation").getAsJsonObject().get("display_location").getAsJsonObject().get("full").getAsString());
        Output output = new Output(null, null, null, null, null);

        output.setLocation(jObj.get("current_observation").getAsJsonObject().get("display_location").getAsJsonObject().get("full").getAsString());
        output.setTemp(jObj.get("current_observation").getAsJsonObject().get("temperature_string").getAsString());
        output.setFeelsLike(jObj.get("current_observation").getAsJsonObject().get("feelslike_string").getAsString());
        output.setLocalTime(jObj.get("current_observation").getAsJsonObject().get("observation_time").getAsString());
        output.setWindGust(jObj.get("current_observation").getAsJsonObject().get("wind_string").getAsString());

        System.out.println(output.output());

        return jObj;
    }


    /**
     * connectWunderground:
     *
     * uses wunderground API to connect to Json file on weatherunderground website.
     *
     * reads stream and returns a string representation of .json file.
     *
     * @param wunderConn
     * @return
     * @throws IOException
     */
    public String connectWunderground(String wunderConn) throws IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(wunderConn);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));

            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1){
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                reader.close();
            }
        }
        return wunderConn;
    }

    /**
     * argumentParser method: accepts command line argument String array and
     * separates by commas and white space.
     *
     * Ex: Boston, Ma:
     *
     * yields:
     *
     * String[0] = Boston
     * String[1] = MA
     * @param argument
     */
    public void argumentParser(String[] argument){
        String regex = ",\\s";
        queryArgs = argument;

    }

    //-----------------Getters and Setters-----------------

    public WebsiteController(String[] argument) {
        this.argument = argument;
        argumentParser(argument);
    }

    public WebsiteController(){
    }

    public String[] getArgument() {
        return argument;
    }

    public void setArgument(String[] argument) {
        this.argument = argument;
    }

    public String getApiHttp() {
        return apiHttp;
    }

    public void setApiHttp(String apiHttp) {
        this.apiHttp = apiHttp;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
