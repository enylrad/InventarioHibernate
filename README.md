# InventarioHibernate
Practica de 2DAM con Hibernate

Requisitos:
 - SwingX 1.6.4 All - Binary (Calendario) https://java.net/projects/swingx/downloads/directory/releases
 - Cambio de apariencia - Guia: http://curiotecnology.blogspot.com.es/2012/08/cambiar-la-aparencia-de-un-jframe-java.html
 - Cambio de apariencia - Librerias: http://www.mediafire.com/download/dq2yr8zccqqb4p8/substance-4.3.jar
 - Librerias MySQL
 
Enunciado del ejercicio:
 
Se desea crear una aplicación para gestionar el inventario de una tienda de informática.
Básicamente, se deberá poder realizar las siguientes tareas con los componentes:

 - Añadir elementos
 - Actualizarlos
 - Eliminarlos
 - Consultar/listar a partir del tipo, nombre y/o código.
 
Para evitar que la práctica sea excesivamente larga para algunos, se ha dividido en las siguientes
partes con su correspondiente puntuación:

1. Implementación de las funcionalidades anteriores en modo consola. 6 puntos

2. En modo gráfico. +1.5 puntos

3. Añadir tabla subtipos. +1 puntos

4. Añadir imágenes a los componentes. +1.5 puntos

Detalles de la aplicación

De los componentes que se desean almacenar en la base de datos, nos interesan los siguientes
campos:
 - Código identificativo
 - Nombre del componente
 - Precio de venta al público
 - Precio coste
 - Stock
 - Descripción
 - Fecha compra
 - Estado (activo / baja)
 - Tipo de material. Clave ajena a la tabla tipos

De los tipos de materiales, es suficiente con el código del tipo y su nombre.Os podéis hacer una
idea consultando cualquier tienda online, p.e.:pccomponentes.com
