# Conversor de Monedas

Este proyecto es un conversor de monedas que permite obtener tipos de cambio en tiempo real usando la API de [ExchangeRate-API](https://www.exchangerate-api.com/).

---

## ⚠️ Restricciones de monedas en la API

* La API utilizada en este proyecto no proporciona datos de tipo de cambio para ciertas monedas debido a sanciones y restricciones en el comercio internacional. Actualmente, solo hay una moneda ampliamente conocida que no cuenta con soporte:

| Código de moneda | Nombre de la moneda    | País           |
|-----------------|----------------------|---------------|
| **KPW**        | Won norcoreano        | Corea del Norte |

Si intentas realizar una conversión con una moneda no admitida, la API podría devolver un error o datos inválidos. Es recomendable verificar la lista de monedas disponibles antes de realizar una conversión.

---

## 🛠 Tecnologías usadas

- **Lenguaje de Programación:** Java
- **Entorno de Desarrollo:** IntelliJ IDEA
- **API de Tasas de Cambio:** ExchangeRate-API
- **Bibliotecas:** Gson *(Para el análisis de respuestas JSON de la API)*

---

## 📦 Estructura del código

```
src/
├── Main.java                  # Punto de entrada del programa
├── ApiClient.java             # Cliente para interactuar con la API
├── ConfigLoader.java          # Carga de configuración desde el archivo properties
├── MonedasResponse.java       # Manejo de respuesta de monedas
├── MonedasDisponiblesDto.java # Mapeo de códigos de monedas
├── ConversionDto.java         # Modelado de la conversión
```

---

## 🚀 Instalación y configuración

### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/Clariveljn/conversor-de-monedas.git
cd conversor-de-monedas
````

### 2️⃣ Configuración de la API Key

Para obtener una API Key gratuita, visita [ExchangeRate-API](https://www.exchangerate-api.com/)

Crea un archivo `config.properties` en la raíz del proyecto y agrega tu clave de API:

```
api.key=TU_API_KEY
```

---

## ⚙️ Compilación y ejecución en IDEs

### 🔹 IntelliJ IDEA

1. Abre IntelliJ IDEA.
2. Ve a `File > Open` y selecciona la carpeta del proyecto.
3. Asegúrate de tener configurado el JDK 17 o superior.
4. Abre `Main.java` y haz clic en el botón **Run**.

### 🔹 Eclipse

1. Abre Eclipse y selecciona `File > Import`.
2. Escoge `Existing Projects into Workspace`.
3. Selecciona la carpeta del repositorio clonado.
4. Abre `Main.java` y selecciona `Run > Run As > Java Application`.

### 🔹 NetBeans

1. Abre NetBeans y selecciona `Open Project`.
2. Selecciona la carpeta del repositorio.
3. Haz clic derecho en `Main.java` y selecciona **Run File**.

---

## 📜 Descripción del funcionamiento

El conversor de monedas realiza las siguientes funciones:

✔️ Carga la clave de API desde `config.properties`  
✔️ Obtiene las monedas disponibles desde la API  
✔️ Ofrece un menú interactivo con opciones:

1. Ver monedas disponibles
2. Realizar conversión
3. Salir

---

## 🌍 Uso

1. Ejecuta el programa en IntelliJ IDEA (u otro IDE).
2. Selecciona la opción para ver las monedas disponibles.
3. Ingresa los códigos de moneda de origen y destino.
4. Introduce el monto a convertir.
5. Recibe el resultado con el monto equivalente.

---

## 📝 Notas

* La API Key debe ser válida para que las consultas funcionen correctamente.
* Asegúrate de contar con conexión a internet para realizar las conversiones.