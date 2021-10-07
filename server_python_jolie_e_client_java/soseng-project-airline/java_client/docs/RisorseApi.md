# RisorseApi

All URIs are relative to *https://stoplight.io/mocks/soseng-unibo/soseng-project-documentation/7424067*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getFlights**](RisorseApi.md#getFlights) | **GET** /flights | Restituisci le offerte attive
[**postLmflight**](RisorseApi.md#postLmflight) | **POST** /LMflight | Crea un volo last minute
[**postNotifypayment**](RisorseApi.md#postNotifypayment) | **POST** /notifypayment | Ricevi pagamento
[**postRegistration**](RisorseApi.md#postRegistration) | **POST** /registration | Registra un nuovo utente

<a name="getFlights"></a>
# **getFlights**
> InlineResponse200 getFlights()

Restituisci le offerte attive

È la risorsa che restituisce tutte le offerte di voli attive, compresi i voli last minute.

### Example
```java
// Import classes:
//import airline.ApiException;
//import airline.airline_client.RisorseApi;


RisorseApi apiInstance = new RisorseApi();
try {
    InlineResponse200 result = apiInstance.getFlights();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#getFlights");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**InlineResponse200**](InlineResponse200.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="postLmflight"></a>
# **postLmflight**
> postLmflight(body)

Crea un volo last minute

Endpoint che permette di creare un volo lastminute, specificando tutti i parametri del volo; il server lo inoltrerà poi ad acmesky

### Example
```java
// Import classes:
//import airline.ApiClient;
//import airline.ApiException;
//import airline.Configuration;
//import airline.auth.*;
//import airline.airline_client.RisorseApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: companytoken
ApiKeyAuth companytoken = (ApiKeyAuth) defaultClient.getAuthentication("companytoken");
companytoken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//companytoken.setApiKeyPrefix("Token");

RisorseApi apiInstance = new RisorseApi();
Lmflight body = new Lmflight(); // Lmflight | 
try {
    apiInstance.postLmflight(body);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#postLmflight");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Lmflight**](Lmflight.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

[companytoken](../README.md#companytoken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="postNotifypayment"></a>
# **postNotifypayment**
> postNotifypayment(body)

Ricevi pagamento

È la risorsa che permette al fornitore dei servizi bancari di inviare alla compagnia aerea la quota del pagamento ricevuto dal cliente per l&#x27;acquisto dell&#x27;offerta.

### Example
```java
// Import classes:
//import airline.ApiClient;
//import airline.ApiException;
//import airline.Configuration;
//import airline.auth.*;
//import airline.airline_client.RisorseApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: companytoken
ApiKeyAuth companytoken = (ApiKeyAuth) defaultClient.getAuthentication("companytoken");
companytoken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//companytoken.setApiKeyPrefix("Token");

RisorseApi apiInstance = new RisorseApi();
NotifypaymentBody body = new NotifypaymentBody(); // NotifypaymentBody | 
try {
    apiInstance.postNotifypayment(body);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#postNotifypayment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**NotifypaymentBody**](NotifypaymentBody.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

[companytoken](../README.md#companytoken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="postRegistration"></a>
# **postRegistration**
> InlineResponse2001 postRegistration(body)

Registra un nuovo utente

È la risorsa che permette a chi intende interagire con la compagnia aerea di ottenere l&#x27;APIKey per poter essere identificato e autorizzato.

### Example
```java
// Import classes:
//import airline.ApiException;
//import airline.airline_client.RisorseApi;


RisorseApi apiInstance = new RisorseApi();
MapsV1Credentials body = new MapsV1Credentials(); // MapsV1Credentials | 
try {
    InlineResponse2001 result = apiInstance.postRegistration(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#postRegistration");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**MapsV1Credentials**](MapsV1Credentials.md)|  | [optional]

### Return type

[**InlineResponse2001**](InlineResponse2001.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

