# RisorseApi

All URIs are relative to *https://soseng-unibo.stoplight.io/mocks/soseng-unibo/soseng-project-documentation/6636887*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getMessageUsername**](RisorseApi.md#getMessageUsername) | **GET** /message/{username} | Restituisci i primi 10 messaggi del cliente
[**getMessageUsernameFromMessageid**](RisorseApi.md#getMessageUsernameFromMessageid) | **GET** /message/{username}/from/{messageid} | Restituisci 10 messaggi del cliente
[**getMessageUsernameMessageid**](RisorseApi.md#getMessageUsernameMessageid) | **GET** /message/{username}/{messageid} | Restituisci il messaggio del cliente
[**postAllmessage**](RisorseApi.md#postAllmessage) | **GET** /message | Restituisci tutti i messaggi
[**postCreatemessage**](RisorseApi.md#postCreatemessage) | **POST** /createmessage | Invia messaggio
[**postCreatemessages**](RisorseApi.md#postCreatemessages) | **POST** /createmessages | Invia messaggi
[**postLogin**](RisorseApi.md#postLogin) | **POST** /login | Autentica un cliente

<a name="getMessageUsername"></a>
# **getMessageUsername**
> getMessageUsername(username)

Restituisci i primi 10 messaggi del cliente

È la risorsa che, a fronte di una richiesta HTTP nella cui intestazione vi è un &#x60;token&#x60; identificativo valido, restituisce i primi 20 messaggi inviati al cliente il cui username è pari al parametro di query &#x60;username&#x60;.  In caso la richiesta HTTP abbia esito positivo, la risposta è un reindirizzamento alla risorsa &#x60;/message/{username}/from/0&#x60;

### Example
```java
// Import classes:
//import prontogramprovider.ApiClient;
//import prontogramprovider.ApiException;
//import prontogramprovider.Configuration;
//import prontogramprovider.auth.*;
//import prontogramprovider.prontogram_client.RisorseApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


RisorseApi apiInstance = new RisorseApi();
String username = "username_example"; // String | Username dell'utente
try {
    apiInstance.getMessageUsername(username);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#getMessageUsername");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **username** | **String**| Username dell&#x27;utente |

### Return type

null (empty response body)

### Authorization

[tokenSessione](../README.md#tokenSessione)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getMessageUsernameFromMessageid"></a>
# **getMessageUsernameFromMessageid**
> InlineResponse2001 getMessageUsernameFromMessageid(username, messageid)

Restituisci 10 messaggi del cliente

È la risorsa che, a fronte di una richiesta HTTP nella cui intestazione vi è un &#x60;token&#x60; identificativo valido, restituisce i 10 messaggi successivi a quello con id pari al parametro di query &#x60;messageId&#x60; e inviati al cliente il cui username è pari al parametro di query &#x60;username&#x60;.  Il body della richiesta e della risposta HTTP è di tipo &#x60;application/vnd.api+json&#x60; in quanto l&#x27;API prontogramAPI è al livello 3 di modello di maturità di Richardson: nella risposta sono presenti anche i campi &#x60;links&#x60; per permettere l&#x27;esplorazione dinamica, come richiesto da RMML3.

### Example
```java
// Import classes:
//import prontogramprovider.ApiClient;
//import prontogramprovider.ApiException;
//import prontogramprovider.Configuration;
//import prontogramprovider.auth.*;
//import prontogramprovider.prontogram_client.RisorseApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


RisorseApi apiInstance = new RisorseApi();
String username = "username_example"; // String | Username dell'utente
String messageid = "messageid_example"; // String | Id del messaggio
try {
    InlineResponse2001 result = apiInstance.getMessageUsernameFromMessageid(username, messageid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#getMessageUsernameFromMessageid");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **username** | **String**| Username dell&#x27;utente |
 **messageid** | **String**| Id del messaggio |

### Return type

[**InlineResponse2001**](InlineResponse2001.md)

### Authorization

[tokenSessione](../README.md#tokenSessione)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/vnd.api+json

<a name="getMessageUsernameMessageid"></a>
# **getMessageUsernameMessageid**
> InlineResponse2002 getMessageUsernameMessageid(username, messageid)

Restituisci il messaggio del cliente

È la risorsa che, a fronte di una richiesta HTTP nella cui intestazione vi è un &#x60;token&#x60; identificativo valido, restituisce il messaggio con id pari al parametro di query &#x60;messageid&#x60; inviato al cliente il cui username è pari al parametro di query &#x60;username&#x60;.  Il body della richiesta e della risposta HTTP è di tipo &#x60;application/vnd.api+json&#x60; in quanto l&#x27;API prontogramAPI è al livello 3 di modello di maturità di Richardson: nella risposta sono presenti anche i campi &#x60;links&#x60; per permettere l&#x27;esplorazione dinamica, come richiesto da RMML3.

### Example
```java
// Import classes:
//import prontogramprovider.ApiClient;
//import prontogramprovider.ApiException;
//import prontogramprovider.Configuration;
//import prontogramprovider.auth.*;
//import prontogramprovider.prontogram_client.RisorseApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


RisorseApi apiInstance = new RisorseApi();
String username = "username_example"; // String | Username dell'utente
String messageid = "messageid_example"; // String | Id del messaggio
try {
    InlineResponse2002 result = apiInstance.getMessageUsernameMessageid(username, messageid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#getMessageUsernameMessageid");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **username** | **String**| Username dell&#x27;utente |
 **messageid** | **String**| Id del messaggio |

### Return type

[**InlineResponse2002**](InlineResponse2002.md)

### Authorization

[tokenSessione](../README.md#tokenSessione)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/vnd.api+json

<a name="postAllmessage"></a>
# **postAllmessage**
> InlineResponse200 postAllmessage()

Restituisci tutti i messaggi

È la risorsa che, a fronte di una richiesta HTTP nella cui intestazione vi è un &#x60;token&#x60; identificativo valido, restituisce tutti i messaggi (in blocchi di cardinalità 20) accessibili al fruitore (utente o ACMESky) identificato da quel token: in dettaglio, l&#x27;utente ha accesso solo ai messaggi di cui è destinatario, mentre ACMESky ha accesso a tutti i messaggi che ha inoltrato ai suoi utenti mediante Prontogram (potrebbe richiederli per fini statistici, ad esempio).  Il body della risposta HTTP è di tipo &#x60;application/vnd.api+json&#x60; in quanto l&#x27;API prontogramAPI è al livello 3 di modello di maturità di Richardson: con la risposta sono restituiti anche i campi &#x60;links&#x60; per permettere l&#x27;esplorazione dinamica, come richiesto da RMML3.

### Example
```java
// Import classes:
//import prontogramprovider.ApiClient;
//import prontogramprovider.ApiException;
//import prontogramprovider.Configuration;
//import prontogramprovider.auth.*;
//import prontogramprovider.prontogram_client.RisorseApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


RisorseApi apiInstance = new RisorseApi();
try {
    InlineResponse200 result = apiInstance.postAllmessage();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#postAllmessage");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**InlineResponse200**](InlineResponse200.md)

### Authorization

[tokenSessione](../README.md#tokenSessione)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/vnd.api+json

<a name="postCreatemessage"></a>
# **postCreatemessage**
> postCreatemessage(body)

Invia messaggio

È la risorsa che, a fronte di una richiesta HTTP nella cui intestazione vi è un &#x60;token&#x60; identificativo valido, invia un messaggio il cui corpo e destinatario sono quelli specificati nel body della richiesta HTTP.

### Example
```java
// Import classes:
//import prontogramprovider.ApiClient;
//import prontogramprovider.ApiException;
//import prontogramprovider.Configuration;
//import prontogramprovider.auth.*;
//import prontogramprovider.prontogram_client.RisorseApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


RisorseApi apiInstance = new RisorseApi();
Message body = new Message(); // Message | Il body della richiesta HTTP è di tipo `application/vnd.api+json` in quanto l'API prontogramAPI è al livello 3 di modello di maturità di Richardson.
try {
    apiInstance.postCreatemessage(body);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#postCreatemessage");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Message**](Message.md)| Il body della richiesta HTTP è di tipo &#x60;application/vnd.api+json&#x60; in quanto l&#x27;API prontogramAPI è al livello 3 di modello di maturità di Richardson. | [optional]

### Return type

null (empty response body)

### Authorization

[tokenSessione](../README.md#tokenSessione)

### HTTP request headers

 - **Content-Type**: application/vnd.api+json
 - **Accept**: Not defined

<a name="postCreatemessages"></a>
# **postCreatemessages**
> postCreatemessages(body)

Invia messaggi

È la risorsa che, a fronte di una richiesta HTTP nella cui intestazione vi è un &#x60;token&#x60; identificativo valido, invia un messaggio il cui corpo e destinatario sono quelli specificati nel body della richiesta HTTP. A differenza di /createmessage, questo endpoint supporta l&#x27;invio di più messaggi alla volta

### Example
```java
// Import classes:
//import prontogramprovider.ApiClient;
//import prontogramprovider.ApiException;
//import prontogramprovider.Configuration;
//import prontogramprovider.auth.*;
//import prontogramprovider.prontogram_client.RisorseApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


RisorseApi apiInstance = new RisorseApi();
MessageList body = new MessageList(); // MessageList | Il body della richiesta HTTP è di tipo `application/vnd.api+json` in quanto l'API prontogramAPI è al livello 3 di modello di maturità di Richardson.
try {
    apiInstance.postCreatemessages(body);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#postCreatemessages");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**MessageList**](MessageList.md)| Il body della richiesta HTTP è di tipo &#x60;application/vnd.api+json&#x60; in quanto l&#x27;API prontogramAPI è al livello 3 di modello di maturità di Richardson. | [optional]

### Return type

null (empty response body)

### Authorization

[tokenSessione](../README.md#tokenSessione)

### HTTP request headers

 - **Content-Type**: application/vnd.api+json
 - **Accept**: Not defined

<a name="postLogin"></a>
# **postLogin**
> InlineResponse2003 postLogin()

Autentica un cliente

È la risorsa che permette al cliente o ad ACMESky di ottenere il token temporaneo tramite cui potranno essere identificati e autorizzati da Prontogram.

### Example
```java
// Import classes:
//import prontogramprovider.ApiClient;
//import prontogramprovider.ApiException;
//import prontogramprovider.Configuration;
//import prontogramprovider.auth.*;
//import prontogramprovider.prontogram_client.RisorseApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: basicAuth
HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
basicAuth.setUsername("YOUR USERNAME");
basicAuth.setPassword("YOUR PASSWORD");

RisorseApi apiInstance = new RisorseApi();
try {
    InlineResponse2003 result = apiInstance.postLogin();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RisorseApi#postLogin");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**InlineResponse2003**](InlineResponse2003.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

