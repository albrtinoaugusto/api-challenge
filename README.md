# developer-challenge
API RESTful Challenge

API feita com java e hospedada no Heroku

#Adicionar pais
Metodo: [POST] https://challenge-rest-api.herokuapp.com/add-country

Exemplo:
{
    "name" : "Brazil",
    "capital" : "Brasília",
    "region" : "America do Sul",
    "subRegion" : "...",
    "area" : "8.516 million km quadrados",
}

Resposta: 
{
    "mensagem": "País adicionado com sucesso."
}

#Listar paises
Metodo: [GET] https://challenge-rest-api.herokuapp.com/get-countries

Resposta: 
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


#Actualizar o pais
Metodo: [PUT] https://challenge-rest-api.herokuapp.com/update-country

Exemplo:
{
        "area": "1000 100 km²",
        "capital": "Kerlim Mil",
        "subRegion": "... Mil",
        "name": "Alemanha",
        "region": "Sudoeste Mil",
        "id":"SomIDGenCounTrY3kEy374658"
}

Nota: Nao é obrigatorio mandar todos campos, pode amndar somente os que precisa actualizar.

Resposta: 
{
    "mensagem": "País actualizado com sucesso."
}


#Eliminar um pais
Metodo: [DELETE] https://challenge-rest-api.herokuapp.com/delete-country

Exemplo:
{
    "id": "SomIDGenCounTrY3kEy374658"
}

Resposta: 
{
    "mensagem": "País eliminado com sucesso com sucesso."
}


#Ordenar a lista dos países por qualquer uma das suas propriedades
Metodo: [GET] https://challenge-rest-api.herokuapp.com/get-countries

É so acrescentar o parametro (order) o valor respectivo a a propriedade pela pretende ordenar os paises

Exemplo: 
https://challenge-rest-api.herokuapp.com/get-countries?order=region
https://challenge-rest-api.herokuapp.com/get-countries?order=name
ou https://challenge-rest-api.herokuapp.com/get-countries?order=region

Resposta: 
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
