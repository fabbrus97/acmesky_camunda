package it.unibo.soseng.acmesky;

import geoprovider.*;
import geoprovider.auth.*;
import io.swagger.client.model.*;
import geoprovider.geo_client.RisorseApi;


import java.util.ArrayList;

public class GetDistanceService {

    public GetDistanceService() {
    }

    public static void service(){

        if (StaticValues.geoprovToken == null) {
            RisorseApi apiInstance = new RisorseApi();
            Credentials body = new Credentials();

            body.setPassword(StaticValues.geoprovUsername);
            body.setUsername(StaticValues.getGeoprovpassword);

            try {
                InlineResponse2001 result = apiInstance.postRegistration(body);
                System.out.println(result);
                StaticValues.geoprovToken = result;
            } catch (ApiException e) {
                System.err.println("Exception when calling RisorseApi#postRegistration");
                e.printStackTrace();
            }
        }

        ApiClient defaultClient = Configuration.getDefaultApiClient();

        defaultClient.setApiKey(StaticValues.geoprovToken.getToken());

        RisorseApi apiInstance = new RisorseApi();

        try{
            GeoBody body = new GeoBody();
            

        }catch (ApiException e) {
            System.err.println("Exception when calling RisorseApi#postAllmessage");
            e.printStackTrace();
        }
    }
}

class Distances{

    public ArrayList<Distance> distances;

    public ArrayList<Distance> getDistances() {
        return distances;
    }

    public void setDistances(ArrayList<Distance> distances) {
        this.distances = distances;
    }

    public Distances() {
    }
}

class Distance{

    int value;
    String unit;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    Distance(){

    }
}
