---

# Sistema Transaccional Banco De Los Andes

## Descripción General
Este proyecto implementa un sistema transaccional para el Banco De Los Andes, diseñado para gestionar eficientemente las operaciones bancarias, asegurando la integridad, seguridad y escalabilidad de los datos. Se desarrolló como parte del curso de Sistemas Transaccionales en la Universidad de los Andes.

## Integrantes
- Laura Valentina Ceron Pulgarin - 202214973 (l.ceronp@uniandes.edu.co)
- Franklin Smith Fernandez Romero - 202215103 (f.fernandezr@uniandes.edu.co)
- Andres Mateo Chilito Avella - 202214992 (a.chilitoa@uniandes.edu.co) 

## Características
- **Modelo Conceptual UML**: Diseño de entidades como Usuarios, Cuentas, Préstamos y Operaciones Bancarias, mostrando una arquitectura de sistema flexible e intuitiva.
- **Modelo Entidad-Relación (E/R)**: Representación detallada del esquema de base de datos, asegurando la integridad de los datos a través de relaciones y restricciones adecuadas.
- **Modelo de Datos**: Detalles de implementación para diversas entidades, incluyendo restricciones de clave primaria y extranjera.
- **Niveles de Normalización**: Se explica cómo el diseño de la base de datos se adhiere a estándares de normalización hasta la Forma Normal de Boyce-Codd (BCNF), asegurando un almacenamiento de datos eficiente sin redundancias.
- **Medidas de Seguridad y Privacidad**: Enfatiza la privacidad de los datos y la integridad operacional, con reglas específicas para roles de usuarios, manejo de cuentas y validaciones de transacciones.

## Prerrequisitos
- Sistema de Gestión de Base de Datos SQL (DBMS)

## Configuración e Instalación
1. Instalar un Sistema de Gestión de Base de Datos SQL compatible.
2. Configurar el esquema de base de datos utilizando los scripts proporcionados en el directorio `/db`.

## Uso
Para utilizar el sistema, si dispone del complemento de Spring en su entorno de desarrollo, le recomendamos acceder al archivo de propiedades de la aplicación y configurar sus credenciales según las especificaciones proporcionadas. Posteriormente, puede ejecutar la aplicación utilizando las herramientas proporcionadas por el entorno de desarrollo integrado.

En el caso de no contar con el complemento de Spring, puede ejecutar la clase 'AplicacionBanco' directamente desde su IDE o utilizando las herramientas de compilación proporcionadas, asegurándose de tener todas las dependencias necesarias y la configuración adecuada para su correcto funcionamiento.

Recuerde que es fundamental seguir las buenas prácticas de seguridad y gestión de configuraciones para garantizar un despliegue adecuado y seguro de la aplicación.

## Pruebas
Para asegurar la calidad tanto del código de la página web del banco como de su base de datos, se llevaron a cabo  pruebas a lo largo de todo el proceso de desarrollo. Estas pruebas abarcaron diferentes aspectos, incluyendo pruebas de unidad, pruebas de integración y pruebas de aceptación.

En las pruebas de unidad, se evaluaron individualmente cada componente del código para verificar su correcto funcionamiento según las especificaciones. Se utilizaron herramientas de pruebas automatizadas para garantizar una cobertura exhaustiva de todos los casos posibles. (Aunque existen algunos fallos pequeños que no se encuentran dentro de los RF).

Las pruebas de integración se centraron en la interacción entre los distintos componentes del sistema, asegurando que trabajaran de manera conjunta de manera eficiente y sin conflictos. Se realizaron pruebas de extremo a extremo para simular situaciones reales de uso y verificar la funcionalidad del sistema en su totalidad.

Además, se llevaron a cabo pruebas de rendimiento y seguridad para evaluar la capacidad del sistema para manejar cargas de trabajo elevadas y proteger los datos confidenciales de los usuarios.

Por último, se realizaron pruebas de aceptación con usuarios reales o simulados para validar que la aplicación cumplía con los requisitos del cliente y ofrecía una experiencia satisfactoria para los usuarios finales.

El proceso de pruebas se llevó a cabo de manera iterativa a lo largo del ciclo de desarrollo, permitiendo identificar y corregir cualquier problema o defecto de manera oportuna. Esto garantizó que tanto la página web del banco como su base de datos cumplieran con los RF necesarios.

## Contribuciones
Las contribuciones a este proyecto son bienvenidas. Por favor, lea nuestras pautas de contribución para más detalles sobre cómo proponer mejoras, informar errores o enviar solicitudes de extracción.

## Licencia
Este proyecto se publica bajo la licencia MIT, que permite la reutilización con pocas restricciones.

## Agradecimientos
Agradecemos a la Universidad de los Andes por proporcionar el marco académico y los recursos necesarios para el desarrollo de este proyecto, especialmente al profesor y monitora que fueron clave en el desarrollo.

---
