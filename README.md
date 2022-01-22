# developer-challenge
API RESTful Challenge

API feita com java e hospedada no Heroku

#Adicionar pais<br/>
Metodo: [POST] <br/>
https://challenge-rest-api.herokuapp.com/add-country

Exemplo:
```javascript
{
    "name" : "Brazil",
    "capital" : "Brasília",
    "region" : "America do Sul",
    "subRegion" : "...",
    "area" : "8.516 million km quadrados"
}
```

Resposta: 
```javascript
{
    "mensagem": "País adicionado com sucesso."
}
```

#Listar paises<br/>
Metodo: [GET] <br/>
https://challenge-rest-api.herokuapp.com/get-countries

Resposta: 
```javascript
[
    {
        "area": "801 537 kmÂ²",
        "capital": "Maputo",
        "subRegion": "...",
        "name": "Mozambique",
        "id": "SomIDGenCounTrY1kEy1886693",
        "region": "Sul"
    },
    {
        "area": "801 800 kmÂ²",
        "capital": "Berlim",
        "subRegion": "...",
        "name": "Alemanha",
        "id": "SomIDGenCounTrY3kEy374658",
        "region": "Sudoeste"
    }
]
```

#Actualizar o pais<br/>
Metodo: [PUT] <br/>
https://challenge-rest-api.herokuapp.com/update-country

Exemplo:
```javascript
{
        "area": "1000 100 km²",
        "capital": "Kerlim Mil",
        "subRegion": "... Mil",
        "name": "Alemanha",
        "region": "Sudoeste Mil",
        "id":"SomIDGenCounTrY3kEy374658"
}
```
Nota: não é obrigatorio mandar todos campos, pode amndar somente os que precisa actualizar.<br/>

Resposta:
```javascript
{
    "mensagem": "País actualizado com sucesso."
}
```

#Eliminar um pais<br/>
Metodo: [DELETE] <br/>
https://challenge-rest-api.herokuapp.com/delete-country

Exemplo:
```javascript
{
    "id": "SomIDGenCounTrY3kEy374658"
}
```
Resposta:
```javascript
{
    "mensagem": "País eliminado com sucesso com sucesso."
}
```

#Ordenar a lista dos países por qualquer uma das suas propriedades<br/>
Metodo: [GET] <br/>
https://challenge-rest-api.herokuapp.com/get-countries<br/>

É so acrescentar o parametro (order) e o valor respectivo à propriedade pela qual pretende ordenar os paises<br/>

Exemplo: <br/>
https://challenge-rest-api.herokuapp.com/get-countries?order=region<br/>
https://challenge-rest-api.herokuapp.com/get-countries?order=name<br/>
ou https://challenge-rest-api.herokuapp.com/get-countries?order=region<br/>

Resposta: 
```javascript
[
    {
        "area": "801 537 kmÂ²",
        "capital": "Maputo",
        "subRegion": "...",
        "name": "Mozambique",
        "id": "SomIDGenCounTrY1kEy1886693",
        "region": "Sul"
    },
    {
        "area": "801 800 kmÂ²",
        "capital": "Berlim",
        "subRegion": "...",
        "name": "Alemanha",
        "id": "SomIDGenCounTrY3kEy374658",
        "region": "Sudoeste"
    }
]
```
