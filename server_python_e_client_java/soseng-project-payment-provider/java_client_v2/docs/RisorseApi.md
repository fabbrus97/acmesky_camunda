# RisorseApi

All URIs are relative to *https://stoplight.io/mocks/soseng-unibo/soseng-project-documentation/7424070*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getLink**](RisorseApi.md#getLink) | **POST** /link | Genera link di pagamento
[**postPaymentdata**](RisorseApi.md#postPaymentdata) | **POST** /paymentdata | Ricevi dati di pagamento
[**postRegistration**](RisorseApi.md#postRegistration) | **POST** /registration | Registra un nuovo utente

<a name="getLink"></a>
# **getLink**
> Activelink getLink(body)

Genera link di pagamento

È la risorsa che, a fronte di una richiesta HTTP nella cui intestazione vi è un &#x60;token&#x60; identificativo valido, restituisce un codice di pagamento relativo all&#x27;offerta specificata.

### Example
```java
// Import classes:
//import paymentprovider.ApiClient;
//import paymentprovider.ApiException;
//import paymentprovider.Configuration;
//import paymentprovider.auth.*;
//import paymentprovider.payment_client.RisorseApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: apikey
ApiKeyAuth apikey = (ApiKeyAuth) defaultClient.getAuthentication("apikey");
apikey.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//apikey.setApiKeyPrefix("Token");

RisorseApi apiInstance = new RisorseApi();
LinkBody body = new LinkBody(); // LinkBody | 
try {
    Activelink result = apiInstance.getLink(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#getLink");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**LinkBody**](LinkBody.md)|  | [optional]

### Return type

[**Activelink**](Activelink.md)

### Authorization

[apikey](../README.md#apikey)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="postPaymentdata"></a>
# **postPaymentdata**
> postPaymentdata(body)

Ricevi dati di pagamento

È la risorsa che permette al cliente intenzionato ad acquistare un&#x27;offerta di inviare al fornitore dei servizi bancari il pagamento corrispondente.

### Example
```java
// Import classes:
//import paymentprovider.ApiClient;
//import paymentprovider.ApiException;
//import paymentprovider.Configuration;
//import paymentprovider.auth.*;
//import paymentprovider.payment_client.RisorseApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: apikey
ApiKeyAuth apikey = (ApiKeyAuth) defaultClient.getAuthentication("apikey");
apikey.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//apikey.setApiKeyPrefix("Token");

RisorseApi apiInstance = new RisorseApi();
PaymentDataBody body = new PaymentDataBody(); // PaymentDataBody | 
try {
    apiInstance.postPaymentdata(body);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#postPaymentdata");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**PaymentDataBody**](PaymentDataBody.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

[apikey](../README.md#apikey)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="postRegistration"></a>
# **postRegistration**
> PaymentRegistration postRegistration(body)

Registra un nuovo utente

È la risorsa che permette a chi intende interagire con il fornitore dei servizi bancari di ottenere l&#x27;APIKey per poter essere identificato e autorizzato.

### Example
```java
// Import classes:
//import paymentprovider.ApiException;
//import paymentprovider.payment_client.RisorseApi;


RisorseApi apiInstance = new RisorseApi();
MapsV1Credentials body = new MapsV1Credentials(); // MapsV1Credentials | 
try {
    PaymentRegistration result = apiInstance.postRegistration(body);
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

[**PaymentRegistration**](PaymentRegistration.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

