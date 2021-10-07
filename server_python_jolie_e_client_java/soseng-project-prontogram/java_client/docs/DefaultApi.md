# DefaultApi

All URIs are relative to *https://soseng-unibo.stoplight.io/mocks/soseng-unibo/soseng-project-documentation/6636887*

Method | HTTP request | Description
------------- | ------------- | -------------
[**postRegister**](DefaultApi.md#postRegister) | **POST** /register | Registra un nuovo utente

<a name="postRegister"></a>
# **postRegister**
> postRegister(body)

Registra un nuovo utente

Registra un nuovo utente; prende in input solo un nome utente e una password

### Example
```java
// Import classes:
//import prontogramprovider.ApiException;
//import prontogramprovider.prontogram_client.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
RegisterBody body = new RegisterBody(); // RegisterBody | 
try {
    apiInstance.postRegister(body);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#postRegister");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**RegisterBody**](RegisterBody.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/vnd.api+json
 - **Accept**: Not defined

