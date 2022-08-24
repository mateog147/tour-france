
# Proyecto práctico para validación y mejoramiento de habilidades en Programación Backend

### Enunciado

Tenemos el siguiente escenario: uno de los eventos más importantes del ciclismo a nivel mundial es el Tour de Francia. Como parte del equipo de tecnología que apoya a la competición, se te ha encargado la tarea de desarrollar una aplicación o servicio que permita el registro de los equipos y sus respectivos ciclistas.

Como requerimientos del sistema, se establecen las siguientes reglas:
- Cada equipo debe tener como datos principales: nombre de equipo, un código abreviado único (alfanumérico, máximo 3 caracteres), y un país asociado.
- Cada ciclista debe tener como datos principales: nombre completo, un número de competidor único (máximo 3 dígitos), estar asociado a un equipo y un país de procedencia (nacionalidad).
- Un equipo de ciclismo estará conformado por un máximo de 8 corredores.

### Resumen de la solución.

- Para la solución se realizó una API con spring boot usando Maven y usando el marco de Spring MVC.
- La estructura del proyecto se abordo con una arquitectura por capas basica pero que cumple las caracteristicas necesarias para el proyecto.
- Se realizaron test unitarios para la capa de servicios.


## Correr localmente

Clone el proyecto

```bash
  git clone https://github.com/mateosofka/tour-france.git
```

Go to the project directory

```bash
  cd tour-france
```

Instanle las dependecias del proyecto, usando el IDE de su preferencia

```bash
  pom.xml
```

Corra el servidor

```bash
  TourApplication.java
```


## Referencia de la API

#### Get all teams

```http
  GET /api/team
```

#### Get team

```http
  GET /api/team/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | id del equipo |

#### Create a team
```http
  POST /api/team
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `Team` | json con nombre, codigo y pais |

#### Get all teams by country

```http
  GET /api/team/${country}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `country`      | `string` | pais que se quiere buscar |

#### Delete team

```http
  DELETE /api/team/${id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | id del equipo |

#### Update basic information of a team
```http
  PUT /api/team/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `Team` | json con nombre, codigo y pais |
| `id`      | `string` | id del equipo |

#### Get all riders

```http
  GET /api/cyclist
```

#### Get riders by team code

```http
  GET /api/cyclist/team/${code}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `code`      | `string` | codigo del equipo a buscar |

#### Create a rider
```http
  POST /api/team/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id`      | `string` | id del equipo |
| `body` | `Cyclist` | json con nombre, numero de corredor y nacionalidad |

#### Get all riders by country

```http
  GET /api/cyclist/country/${country}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `country`      | `string` | pais que se quiere buscar |

#### Delete rider

```http
  DELETE /api/cyclist/${id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | id del corredor |

#### Update basic information of a rider
```http
  PUT /api/cyclist/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `Team` | json con nombre, numero de corredor y nacionalidad |
| `id`      | `string` | id del corredor |

#### Transfer a rider
```http
  POST /api/team/transfer
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `Transfer` | json con id de equipo actual, id equipo nuevo y id corredor|

## Autor

- [@mateosofka](https://github.com/mateosofka)

