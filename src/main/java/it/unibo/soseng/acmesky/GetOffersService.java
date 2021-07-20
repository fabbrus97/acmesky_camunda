package it.unibo.soseng.acmesky;

import io.swagger.airline.ApiClient;
import io.swagger.airline.ApiException;
import io.swagger.airline.Configuration;
import io.swagger.airline.airline_client.RisorseApi;
import io.swagger.client.model.InlineResponse200;
import io.swagger.client.model.InlineResponse2001;
import io.swagger.client.model.MapsV1Credentials;

import java.util.ArrayList;
import java.util.Date;

public class GetOffersService {

    public GetOffersService(){

    }

    public static void service(String airline){

        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath(airline);

        if (StaticValues.airline_token == ""){
            RisorseApi apiInstance = new RisorseApi();
            MapsV1Credentials body = new MapsV1Credentials(); // MapsV1Credentials |

            body.setUsername(StaticValues.airline_username);
            body.setPassword(StaticValues.airline_password);

            try {
                InlineResponse2001 result = apiInstance.postRegistration(body);
                System.out.println(result);
                StaticValues.airline_token = result.getToken();
            } catch (ApiException e) {
                System.err.println("Exception when calling RisorseApi#postRegistration");
                e.printStackTrace();
            }
        }

        RisorseApi apiInstance = new RisorseApi();
        try {
            InlineResponse200 result = apiInstance.getFlights();
            System.out.println(result);
            result.getFlights().get(0).
        } catch (ApiException e) {
            System.err.println("Exception when calling RisorseApi#getFlights");
            e.printStackTrace();
        }
    }

    public static void saveJSON(){
    }

}

class Offers{

    String companyName;
    ArrayList<Flight> flights = new ArrayList<Flight>();

    public Offers() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }
}
class Flight{

    String departureFrom;
    String destination;
    String offerCode;
    Price price;
    Date takeoff;

    public Flight() {
    }

    public String getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(String departureFrom) {
        this.departureFrom = departureFrom;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Date getTakeoff() {
        return takeoff;
    }

    public void setTakeoff(Date takeoff) {
        this.takeoff = takeoff;
    }
}
class Price{
    int amount;
    String currency;
}
