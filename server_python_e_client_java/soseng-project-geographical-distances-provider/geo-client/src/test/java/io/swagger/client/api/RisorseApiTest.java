/*
 * fornitoreDistanzeAPI
 * È l'API RESTful offerta dal *Fornitore delle distanze geografiche* che, come suggerisce il nome, vi racchiude la capability di calcolare la distanza tra due posizioni geografiche.
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.Credentials;
import io.swagger.client.model.GeoBody;
import io.swagger.client.model.InlineResponse200;
import io.swagger.client.model.InlineResponse2001;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for RisorseApi
 */
@Ignore
public class RisorseApiTest {

    private final RisorseApi api = new RisorseApi();

    /**
     * Calcola distanza geografica
     *
     * È la risorsa che restituisce la distanza geografica tra due punti geografici oppure le distanze geografiche tra un punto e ciascun punto di una lista.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void postDistanceTest() throws ApiException {
        GeoBody body = null;
        InlineResponse200 response = api.postDistance(body);

        // TODO: test validations
    }
    /**
     * Registra un nuovo utente
     *
     * È la risorsa che permette ad un utente di registrarsi, dopo aver fornito la propria username e password.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void postRegistrationTest() throws ApiException {
        Credentials body = null;
        InlineResponse2001 response = api.postRegistration(body);

        // TODO: test validations
    }
}
