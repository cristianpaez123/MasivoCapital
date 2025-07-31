# 🛒 Aplicación de Búsqueda y Visualización de Productos

Esta aplicación Android permite gestionar tareas de forma offline y sincronizarlas automáticamente con un servidor REST cuando se detecta conexión a Internet. Está diseñado para integrarse fácilmente a una aplicación Android mayor, siguiendo los principios de **Clean Architecture**.

---

### 🏗️ Stack Tecnológico y Dependencias

| Categoría                | Tecnologías / Librerías                                                                                           |
|--------------------------|------------------------------------------------------------------------------------------------------------------|
| Lenguaje / Corrutinas    | Kotlin + Kotlin Coroutines                                                                                        |
| Arquitectura             | Clean Architecture + MVVM — ViewModel → UseCase → Repository                                        |
| Inyección de dependencias| Hilt (dagger-hilt-android, hilt-viewmodel, kapt)                                                                  |
| UI / Layout              | XML Layouts, RecyclerView, StateFlow                                  |
| Red                      | Retrofit + OkHttp3 + Gson                                                                                         |
| Pruebas                  | JUnit4, Mockito-Kotlin, Coroutines Test                                                                    |

---

### 📌 Notas
- Esta app sigue principios de Clean Architecture y SOLID para garantizar un código mantenible y escalable.
  
---

## ✅ Requisitos Previos

- Android Studio Flamingo o superior  
- Java 11 o superior  
- Un emulador o dispositivo físico con Android 8.0 (API 24) o superior

---

## 🛠️ Instrucciones de Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/cristianpaez123/MasivoCapital

   Abre el proyecto en Android Studio

2. Sincroniza las dependencias de Gradle

3. Configura un emulador o conecta un dispositivo físico

4. Haz clic en el botón "Run" para ejecutar la aplicación

---

## 🧠 Estructura del Proyecto

El proyecto sigue una Clean Architecture con separación por capas y responsabilidades bien definidas:
```

com.example.offlinetasks
├── data                # Origen de datos, mapeadores
│ ├── dto               # Modelos API (llegarán más adelante)
│ ├── local             # Room (Entity, DAO, DB)
│ ├── mapper            # TaskEntity ⇆ Task / TaskDto
│ ├── remote            # Placeholder para Retrofit (futuro)
│ └── repository        # TaskRepositoryImpl, DataSources
│
├── di # Inyección de dependencias (Hilt)
│ ├── DatabaseModule.kt
│ ├── RepositoryModule.kt
│ ├── UseCaseModule.kt
│ └── WorkManagerModule.kt            # Factory personalizada (opcional)
│
├── domain                     # Lógica pura
│ ├── model                    # Task (business object)
│ ├── repository               # TaskRepository (interface)
│ └── usecase                  # AddTask, GetTasks, SyncTasks …
│
├── sync                       # Sincronización (WorkManager)
│ └── TaskSyncWorker.kt
│
├── ui # Presentación (MVVM)
│ ├── main
│ │ ├── MainViewModel.kt           # MainActivity / MainFragment
│ │ ├── model                      # UiState, UiEvent
│ │ └── mapper                     # Task ⇆ TaskUiModel
│ └── addtask                      # DialogFragment (si lo separas)
│
└── utils # Utilidades
└── ConnectivityObserver.kt

---

## 👤 Autor

Nombre: Cristian Paez

GitHub: @cristianpaez123

Correo: cristianpaezguerrero@gmail.com

Celular: +57 300 702 5600

---
