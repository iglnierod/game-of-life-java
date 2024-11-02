# Game of Life

Este proyecto es una implementación en Java del famoso *Game of Life*, un autómata celular diseñado por el matemático británico John Conway en 1970. El juego simula el comportamiento de poblaciones de células vivas y muertas en una cuadrícula, evolucionando en función de reglas simples en cada generación.

## ¿Qué es el *Game of Life*?

El *Game of Life* es un autómata celular en el que cada celda de una cuadrícula bidimensional puede estar en uno de dos estados:
- **Viva** (`✖`)
- **Muerta** (`·`)

El estado de cada celda en la siguiente generación depende del número de células vivas que tenga a su alrededor, de acuerdo a las siguientes reglas:

1. **Supervivencia**: Una célula viva con 2 o 3 células vivas vecinas continúa viva en la siguiente generación.
2. **Muerte por soledad o sobrepoblación**: Una célula viva con menos de 2 o más de 3 células vivas vecinas muere en la siguiente generación.
3. **Reproducción**: Una célula muerta con exactamente 3 células vivas vecinas "nace" y se convierte en una célula viva en la siguiente generación.

Este juego es un ejemplo clásico de sistemas complejos y emergencia, donde reglas simples pueden generar patrones complejos.

## Cómo ejecutar el proyecto

Para ejecutar el proyecto, asegúrate de tener **Java** y **Maven** instalados en tu sistema.

1. Clona este repositorio:
   ```bash
   git clone git@github.com:iglnierod/game-of-life-java.git
   cd game-of-life-java/
   ```
2. Compila el proyecto con Maven
   ```bash
   mvn clean package
   java -jar target/game-of-life-1.0.jar
   ```

> La ejecución del programa termina si:
>  - La matriz está vacía
>  - Se repite exactamente la matríz
> Para terminar la ejecución si ha entrado en bucle usar `Ctrl+C`


##### Hecho por [Rodrigo Iglesias Nieto](https://github.com/iglnierod)