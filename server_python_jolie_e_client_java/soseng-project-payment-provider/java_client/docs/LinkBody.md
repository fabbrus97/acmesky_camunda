# LinkBody

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**amount** | [**LinkAmount**](LinkAmount.md) |  |  [optional]
**offerCode** | **String** | Codice dell&#x27;offerta che il cliente intende acquistare: in dettaglio, questo codice non è quello identificante la coppia offerta - cliente, bensì è quello assegnato, in principio, all&#x27;offerta dalla compagnia aerea che l&#x27;ha attivata, ancor prima di comunicarla ad ACMESky.  Quando il fornitore dei servizi bancari inoltrerà la quota del pagamento del cliente alla compagnia aerea, le invierà anche questo codice per comunicarle l&#x27;offerta di cui le sta inviando il pagamento. |  [optional]
**airline** | **String** | Compagnia aerea che ha attivato l&#x27;offerta per il cui pagamento si chiede la generazione del link: servirà al fornitore dei servizi bancari per inoltrarle la quota spettante. |  [optional]
