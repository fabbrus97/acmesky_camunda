package it.unibo.soseng.acmesky;

import io.swagger.airline.*;
import io.swagger.client.model.*;
import io.swagger.airline.airline_client.RisorseApi;

public class GetOffersService {

    public GetOffersService() {

    }

    public static InlineResponse2001 service(String airline){
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath(airline);


        //registrati presso la compagnia aerea; quest'operazione fornisce una chiave
        //che va salvata per le richieste successive

        RisorseApi apiInstance = new RisorseApi();
        MapsV1Credentials body = new MapsV1Credentials(); // MapsV1Credentials |


        body.setUsername("Mario");
        body.setPassword("12345abcd");

        try {
            InlineResponse2001 result = apiInstance.postRegistration(body);
            System.out.println(result);
            return result;
        } catch (ApiException e) {
            System.err.println("Exception when calling RisorseApi#postRegistration");
            e.printStackTrace();
        }

        /*RisorseApi apiInstance = new RisorseApi(defaultClient);
        // InlineObject1 inlineObject1 = new InlineObject1(); // InlineObject1 |

        MapsV1Credentials mapsV1Credentials = new MapsV1Credentials();
        mapsV1Credentials.setUsername("Mario");
        mapsV1Credentials.setPassword("12345abcd");


        InlineResponse2001 res = apiInstance.postRegistration(mapsV1Credentials);
        */
        return null;
    }
}
