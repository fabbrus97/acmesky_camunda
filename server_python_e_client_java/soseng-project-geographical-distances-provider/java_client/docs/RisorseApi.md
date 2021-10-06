# RisorseApi

All URIs are relative to *https://soseng-unibo.stoplight.io/mocks/soseng-unibo/soseng-project-documentation/2282674*

Method | HTTP request | Description
------------- | ------------- | -------------
[**postDistance**](RisorseApi.md#postDistance) | **POST** /distance | Calcola distanza geografica
[**postRegistration**](RisorseApi.md#postRegistration) | **POST** /registration | Registra un nuovo utente

<a name="postDistance"></a>
# **postDistance**
> DistanceResult postDistance(body)

Calcola distanza geografica

È la risorsa che restituisce la distanza geografica tra due punti geografici oppure le distanze geografiche tra un punto e ciascun punto di una lista.

### Example
```java
// Import classes:
//import geoprovider.ApiClient;
//import geoprovider.ApiException;
//import geoprovider.Configuration;
//import geoprovider.auth.*;
//import geoprovider.geo_client.RisorseApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: authorization
HttpBasicAuth authorization = (HttpBasicAuth) defaultClient.getAuthentication("authorization");
authorization.setUsername("YOUR USERNAME");
authorization.setPassword("YOUR PASSWORD");

RisorseApi apiInstance = new RisorseApi();
GeoBody body = new GeoBody(); // GeoBody | Specifica un punto geografico di partenza e un array di uno o più punti di arrivo.
try {
    DistanceResult result = apiInstance.postDistance(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#postDistance");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**GeoBody**](GeoBody.md)| Specifica un punto geografico di partenza e un array di uno o più punti di arrivo. | [optional]

### Return type

[**DistanceResult**](DistanceResult.md)

### Authorization

[authorization](../README.md#authorization)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="postRegistration"></a>
# **postRegistration**
> RegisterMaps postRegistration(body)

Registra un nuovo utente

È la risorsa che permette ad un utente di registrarsi, dopo aver fornito la propria username e password.

### Example
```java
// Import classes:
//import geoprovider.ApiException;
//import geoprovider.geo_client.RisorseApi;


RisorseApi apiInstance = new RisorseApi();
Credentials body = new Credentials(); // Credentials | 
try {
    RegisterMaps result = apiInstance.postRegistration(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#postRegistration");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Credentials**](Credentials.md)|  | [optional]

### Return type

[**RegisterMaps**](RegisterMaps.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

