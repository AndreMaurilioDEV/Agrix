[JAVA_BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[MONGO_BADGE]:https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white
[AWS_BADGE]:https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white



<h1 align="center" style="font-weight: bold;">Agrix 💻</h1>

![spring][SPRING_BADGE]
![java][JAVA_BADGE]
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)


<h2 id="routes">📍 API Endpoints</h2>


​
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>POST /farms</kbd>     | Cria uma nova fazenda [response details](#get-auth-detail)
| <kbd>GET /farms</kbd>     |  Lista de todas as fazendas [request details](#post-auth-detail)
| <kbd>GET /farms/{id}</kbd>     |  Fazenda pelo id [request details](#get-farm-id)
| <kbd>POST /{farmId}/crops</kbd>     |   Adiciona uma nova plantação (crop) à fazenda com o farmId especificado. [request details](#post-crop-farm-id)
| <kbd>GET /{farmId}/crops</kbd>     |   Lista de plantações associadas à fazenda com o farmId fornecido. [request details](#get-crop-farm-id)
| <kbd>GET /crops</kbd>     |   Lista de plantações. [request details](#get-crop)
| <kbd>GET /crops/{id}</kbd>     |  Plantação pelo id. [request details](#get-crop-id)
| <kbd>GET /crops/search?start={data}&end={data}</kbd>     |   Lista de com plantações que tenham a data de colheita dentro do intervalo especificado. [request details](#get-crop-search)
| <kbd>POST /fertilizers</kbd>     | Cria uma novo fertilizante [response details](#post-fertilizer)
| <kbd>GET /fertilizers</kbd>     |  Lista de todos fertilizantes [request details](#get-fertilizer)
| <kbd>GET /fertilizers/{id}</kbd>     |  Fertilizante pelo id [request details](#get-fertilizer-id)
| <kbd>POST /{cropId}/fertilizers/{fertilizerId}</kbd>     |  Associa um fertilizante a uma determinada plantação  [request details](#post-crop-fertilizer)
| <kbd>GET /{cropId}/fertilizers</kbd>     |   Obtém todos os fertilizantes associados a uma determinada plantação  [request details](#get-crop-fertilizer)

<h3 id="get-auth-detail">POST /farms</h3>

**REQUEST**
```json
{
  "name": "Fazenda",
  "size": 5
}
```

**RESPONSE**
```json
{
  "id": 1,
  "name": "Fazenda",
  "size": 5
}
```

<h3 id="post-auth-detail">GET /farms</h3>

**RESPONSE**
```json
[
  {
  "id": 1,
  "name": "Fazenda",
  "size": 5
  }
]
```

<h3 id="get-farm-id">GET /farms/10</h3>

**RESPONSE**
```json
[
  {
  "id": 10,
  "name": "Fazenda",
  "size": 5
  }
]
```

<h3 id="post-crop-farm-id">POST /farms/1/crops</h3>

**REQUEST**
```json
{
  "name": "Milho",
  "plantedArea": 120.5,
  "plantedDate": "2024-09-01",
  "harvestDate": "2025-01-15"
}
```

**RESPONSE**
```json
{
  "id": 1,
  "name": "Milho",
  "plantedArea": 120.5,
  "plantedDate": "2024-09-01",
  "harvestDate": "2025-01-15",
  "farmId": 1
}
```

<h3 id="get-crop-farm-id">GET /farms/1/crops</h3>

**RESPONSE**
```json
[
 {
  "id": 1,
  "name": "Milho",
  "plantedArea": 120.5,
  "plantedDate": "2024-09-01",
  "harvestDate": "2025-01-15",
  "farmId": 1
  }
]
```

<h3 id="get-crop">GET /crops</h3>

**RESPONSE**
```json
[
 {
  "id": 1,
  "name": "Milho",
  "plantedArea": 120.5,
  "plantedDate": "2024-09-01",
  "harvestDate": "2025-01-15",
  "farmId": 1
  }
]
```

<h3 id="get-crop-id">GET /crops/15</h3>

**RESPONSE**
```json

{
  "id": 15,
  "name": "Alface",
  "plantedArea": 10.5,
  "plantedDate": "2024-09-01",
  "harvestDate": "2025-01-15",
  "farmId": 1
}

```

<h3 id="get-crop-search">GET /crops/search?start=2024-01-01&end=2024-12-31</h3>

**RESPONSE**
```json
[
  {
    "id": 1,
    "name": "Wheat",
    "plantedArea": 120.5,
    "plantedDate": "2024-09-01",
    "harvestDate": "2025-01-15",
    "farmId": 10
  },
  {
    "id": 2,
    "name": "Corn",
    "plantedArea": 85.0,
    "plantedDate": "2024-03-15",
    "harvestDate": "2024-11-10",
    "farmId": 12
  }
]

```

<h3 id="post-fertilizer">POST /fertilizers</h3>

**REQUEST**
```json
{
  "name": "Fertilizante A",
  "brand": "Brand A",
  "composition": "Composition A"
}
```

**RESPONSE**
```json
{
  "id": 1,
  "name": "Fertilizante A",
  "brand": "Brand A",
  "composition": "Composition A"
}
```

<h3 id="get-fertilizer">GET /fertilizers</h3>

**RESPONSE**
```json
[
 {
  "id": 1,
  "name": "Fertilizante A",
  "brand": "Brand A",
  "composition": "Composition A"
  }
]
```

<h3 id="get-fertilizer-id">GET /fertilizers/2</h3>

**RESPONSE**
```json
{
  "id": 2,
  "name": "Fertilizante B",
  "brand": "Brand B",
  "composition": "Composition B"
}
```


<h3 id="post-crop-fertilizer">POST /1/fertilizers/1</h3>

**RESPONSE**
```json
{
  "message": "Fertilizante e plantação associados com sucesso!"
}
```

<h3 id="get-crop-fertilizer">GET /1/fertilizers/</h3>

**RESPONSE**
```json
[
  {
  "id": 1,
  "name": "Fertilizante A",
  "brand": "Brand A",
  "composition": "Composition A"
  }
]
```

<h2 id="colab">🤝 Colaboradores</h2>

Agradecimento a todos que colaboraram com o projeto.

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/117940329?v=4" width="100px;" alt="André Maurilio Profile Picture"/><br>
        <sub>
          <b>André Maurilio</b>
        </sub>
      </a>
    </td>
        <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/82593112?v=4" width="100px;" alt="Trybe Profile Picture"/><br>
        <sub>
          <b>Trybe</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

<h2 id="contribute">📫 Contribuição</h2>

Sinta-se à vontade para contribuir.

1. `git clone https://github.com/Fernanda-Kipper/text-editor.git`
2. `git checkout -b feature/NAME`
3. Siga os padrões de commit
4. Abra um Pull Request explicando o problema resolvido ou a funcionalidade implementada. Se houver modificações visuais, anexe uma captura de tela e aguarde a revisão!

<h3>Documentações que podem ajudar</h3>

[📝 How to create a Pull Request](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)

[💾 Commit pattern](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)
