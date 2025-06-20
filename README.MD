# Trabajo Práctico Final – Programación Concurrente 2025

## Descripción

Este proyecto implementa un sistema de procesamiento de datos basado en una red de Petri utilizando programación concurrente en Java. El sistema simula el flujo de datos a través de diferentes etapas de procesamiento (simple, media, alta), respetando condiciones de concurrencia, acceso a recursos compartidos y políticas de resolución de conflictos.

## Objetivos

- Modelar el sistema con una red de Petri.
- Implementar un monitor agnóstico que sincronice los disparos de transiciones.
- Utilizar hilos para maximizar el paralelismo sin generar condiciones de carrera ni deadlocks.
- Aplicar políticas de selección de modo de procesamiento (aleatoria y prioritaria).
- Analizar propiedades de la red (invariantes, seguridad, vivacidad).
- Implementar transiciones temporizadas.
- Registrar resultados y verificar el cumplimiento de los invariantes.

## Tecnologías

- Lenguaje: Java
- Herramienta de modelado: PIPE
- Análisis de expresiones regulares: regex.com, debuggex.com
- Logging: `java.util.logging`

## Estructura del Proyecto

- `Main.java`: punto de entrada de la aplicación.
- `Monitor.java`: implementación del monitor concurrente.
- `MonitorInterface.java`: interfaz pública expuesta.
- `Politica.java`: interfaz para las políticas de procesamiento.
- `PoliticaAleatoria.java` y `PoliticaPrioritaria.java`: implementaciones de política.
- `PetriNetExecutor.java`: lógica de ejecución de transiciones.
- `LoggerService.java`: registro de eventos en archivo de log.
- `diagrams/`: contiene los diagramas de clase y secuencia.
- `logs/`: archivos de log con resultados de ejecución.

## Funcionalidades

- Monitor sincronizado y agnóstico a la red.
- Gestión de hilos según el análisis de invariantes.
- Disparo concurrente de transiciones con semántica temporal.
- Registro automático de eventos y tiempos en logs.
- Análisis posterior mediante expresiones regulares.

## Políticas Implementadas

1. **Aleatoria**: los datos se procesan en cualquier modo (simple, medio, alto).
2. **Prioritaria**: prioridad al procesamiento simple, seguido por medio y alto.

## Análisis Realizado

- Verificación de invariantes de plaza y transición.
- Análisis temporal (ejecuciones entre 20–40 segundos).
- Comparación entre políticas de procesamiento.
- Gráfico de responsabilidades de hilos por color.
- Registro de ejecución con 200 invariantes completados por política.

## Ejecución

1. Compilar:
   ```bash
   javac src/*.java

