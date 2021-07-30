package it.unibo.soseng.acmesky;

//import io.swagger.airline.ApiClient; TODO
//import io.swagger.airline.Configuration;
import io.swagger.client.ApiException;
import io.swagger.client.api.RisorseApi;
import io.swagger.client.auth.HttpBasicAuth;
import io.swagger.client.model.*;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import airline.ApiClient;
import airline.Configuration;

import java.util.ArrayList;

 

public class GetDistanceService {

    public GetDistanceService() {
    }

    public static void service(DelegateExecution execution){

        RisorseApi apiInstance = new RisorseApi();

        if (StaticValues.distance_token == ""){

            Credentials body = new Credentials(); // Credentials
            body.setUsername(StaticValues.distance_username);
            body.setPassword(StaticValues.distance_password);
            try {
                RegisterMaps result = apiInstance.postRegistration(body);
                StaticValues.distance_token = result.getToken();
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling RisorseApi#postRegistration");
                e.printStackTrace();
            }

        }

        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath(((ArrayList<String>)execution.getVariable("geoproviders")).get(0));
        // Configure HTTP basic authorization: authorization
        HttpBasicAuth authorization = (HttpBasicAuth) defaultClient.getAuthentication("authorization");
        authorization.setUsername(StaticValues.distance_username);
        authorization.setPassword(StaticValues.distance_password);


        GeoBody body2 = new GeoBody(); // InlineObject |
        body2.setPointA(execution.getVariable("clientAddress").toString());
        body2.setPointsB((ArrayList<String>)execution.getVariable("airports"));
        try {
            DistanceResult result = apiInstance.postDistance(body2);
            int dist = result.getDistance().get(0).getValue().intValue();
            String unit = result.getDistance().get(0).getUnit();
            if (unit.equals("m")){
                dist= (int) dist / 1000;
            }
            System.out.println(result);

            execution.setVariable("distance",dist);
            execution.setVariable("unit",unit);
        } catch (ApiException e) {
            System.err.println("Exception when calling RisorseApi#postDistance");
            e.printStackTrace();
        }
    }
}
