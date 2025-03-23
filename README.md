CADASTRO DE PESSOAS

API de cadastro de pessoas

API com a proposta de simular um cadatro simples com algumas dependendias de uso cotidiano, atrelada a padroes de projetos.

para rodar a aplicação é necessário java-21 banco mysql

OBS: RestFull com aplicação do hiper-link(haeteos)
 {
                "rel": "self",
                "href": "http://localhost:8080/api/person/v1/1",
                "type": "GET"
            },
            {
                "rel": "findAll",
                "href": "http://localhost:8080/api/person/v1",
                "type": "GET"
            },
            {
                "rel": "create",
                "href": "http://localhost:8080/api/person/v1",
                "type": "POST"
            },
            {
                "rel": "update",
                "href": "http://localhost:8080/api/person/v1",
                "type": "PUT"
            },
            {
                "rel": "delete",
                "href": "http://localhost:8080/api/person/v1/1",
                "type": "DELETE"
            }
