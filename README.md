# Minesweeper Game &nbsp;&nbsp;<img src="https://github.com/user-attachments/assets/2762adae-196d-4411-8c7c-1b437297eca3" width="50">

Recreación del tradicional juego Buscaminas, desarrollada en lenguaje java con una interfaz gráfica atractiva, buscando proporcionar diversión y comodidad.

<p align="center">
  <img src="https://github.com/user-attachments/assets/02d31e2f-52db-4117-8ae2-db6a3bf03c57" width="550"/>
</p>

## Cómo Empezar a Jugar

En la sección de lanzamientos (**_releases_**) encontrarás el **archivo ejecutable del juego** (_.exe_) perteneciente a la versión correspondiente.

Se recomienda descargar la última versión (v7) para disfrutar de las nuevas funcionalidades en su totalidad.

## Estética y opciones de celda

Las minas aparecen representadas con un símbolo de **bomba** 💣 y las celdas marcadas como seguras con un símbolo de **bandera** 🚩.

Para **facilitar el avance** de la partida en situaciones complejas, existe la opción de marcar las celdas como dudosas, estas se mostraran con un símbolo de **interrogación** "**?**".

Los números de las celdas indicando la **cantidad de minas** a su alrededor serán de **diferente color** según corresponda. Al finalizar una partida las minas marcadas como seguras y la mina detonante son destacadas con un color distinto a las demás.

<p align="center">
  <img src="https://github.com/user-attachments/assets/5b946aea-49e4-441e-b4b0-c3bbfcf84187" width="200"/>
</p>

## Modos de dificultad

Hay 6 modos de dificultad predefinidos:

- **Easy** - 8 × 8 celdas y 10 minas (Densidad del 15.6 %)

- **Medium** - 16 × 16 celdas y 40 minas (Densidad del 15.6 %)

- **Difficult** - 16 × 30 celdas y 99 minas (Densidad del 20.6 %)

- **Hardcore** - 24 × 30 celdas y 200 minas (Densidad del 27.8 %)

- **Insane** - 50 × 50 celdas y 500 minas (Densidad del 20.0 %)

- **Lunatic** - 100 × 100 celdas y 2000 minas (Densidad del 20.0 %)

Además de las configuraciones predefinidas de dificultad, también existe la opción de **crear un tablero personalizado**. Se puede elegir entre crearlo indicando el **número de minas o la densidad de minas**.

- **Custom** - máximo de 100 × 100 celdas (Densidad entre 0 - 100.0 %)

_El número máximo de minas corresponderá al número de celdas totales menos 9, siendo estas 9 celdas el espacio mínimo de celdas libres para iniciar la partida tras el primer clic._

<p align="center">
  <img src="https://github.com/user-attachments/assets/24ea12cb-8c37-4ec6-b7c7-4f674158eccf" width="240"/>
</p>

_Al crear una partida personalizada **es recomendable basar la dificultad en la densidad de minas** en lugar del número de minas, de este modo podrás elegir el nivel de dificultad con mayor precisión, ya sea que busques una partida relajante (densidad < 16 %) o un reto desafiante (densidad > 23 %)._

## Funcionalidades clave

### Finalización por victoria

Para que el **azar influya lo menos posible**, se han implementado dos modos de finalizar la partida por victoria:

1. Marcar como seguras (🚩) todas las celdas donde haya minas

2. Descubrir todas las celdas sin minas, de modo que las únicas celdas que falten por descubrir sean celdas que contienen minas

En la opción 2 la partida finalizará sin que estén todas las celdas con minas marcadas como seguras. En este caso las posiciones de minas restantes se marcarán automáticamente como celdas seguras al finalizar la partida.

_Para finalizar la partida por victoria **NO puede haber celdas marcadas como dudosas "?"**._

### Despeje automático

Este es un comportamiento adicional en el que, al hacer **clic en una celda con un número**, se verifica si el número de minas marcadas a su alrededor coincide con el número mostrado. Si coinciden, se **abrirán automáticamente todas las demás celdas no marcadas** a su alrededor, ya que se consideran seguras.

## Timer

Las **dificultades predefinidas cuentan con un timer** que representa la puntuación (score) de la partida.

_Si se prefiere jugar sin la presión del tiempo, es posible **ocultar el timer haciendo doble clic sobre este**. La puntuación final de la partida seguirá siendo usada en el panel de puntuaciones aunque el timer esté oculto._

## Panel de puntuaciones

<p align="center">
  <img src="https://github.com/user-attachments/assets/e42d75c5-ebcb-4f48-b683-11891a102056" width="564"/>
</p>

El juego cuenta con un panel de puntuaciones (scores) en el cual se muestran las **5 mejores partidas de la dificultad seleccionada** en orden de mejor puntuación (menor tiempo), excluyendo las partidas de tablero personalizado.

_En caso de haber múltiples partidas con la misma puntuación (empate), estas se ordenarán según la fecha, dando prioridad a la más reciente._

**Para cada dificultad**, los datos de puntuaciones incluyen:

- El número total de partidas jugadas
- El número de victorias
- La racha de victorias más larga
- La racha de victorias actual

_Las puntuaciones son guardadas en un fichero de texto llamado "minesweeper_scores", el cual es creado automáticamente en el directorio donde se ejecute el programa._

## Tabla de información de dificultades

<p align="center">
  <img src="https://github.com/user-attachments/assets/40b43943-cb45-42d0-b675-64647bf378c6" width="490"/>
</p>

La información de las dificultades disponibles se presenta en una tabla que contiene los **campos relevantes**. En caso de tener activa una partida personalizada, la **información del tablero personalizado** también se mostrará en la tabla.

## Mensajes de finalización de partida

Al finalizar la partida se muestran **mensajes personalizados** según si ha sido derrota o victoria.

En caso de victoria, además del tiempo obtenido, también se informa de si este ha sido el **mejor tiempo** conseguido en la dificultad actual o si está **entre los 5 mejores**.

<p align="center">
  <img src="https://github.com/user-attachments/assets/1fabc2cc-2a74-44f3-8f82-fc7abd3b9d86" width="800"/>
</p>

## Comodidad

La **ventana principal** del juego se **posiciona en el centro** de la pantalla cuando es redimensionada.

_Este cambio de posición ocurre cuando se cambia de dificultad y se detecta que el número de filas o columnas es diferente al anterior._

El **estilo de la fuente de texto** utilizada en la aplicación (incluidos textos en mensajes y botones) ha sido adaptado para ser consistente, asegurando su **coherencia en tipo y tamaño** en toda la aplicación. El tamaño de letra se ha establecido buscando que sea legible con mayor facilidad.
