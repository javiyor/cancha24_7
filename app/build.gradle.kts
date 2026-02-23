plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.cancha24_7"
    // ⚠️ Si tu Android Studio no tiene instalado API 35, bajalo a 34.
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.cancha24_7"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.play.services.location)
    // Dentro de tu archivo build.gradle.kts del módulo :app

    dependencies {

        // 1. AÑADE LA PLATAFORMA DEL COMPOSE BOM
        // La versión "2024.06.00" es la más reciente y estable.
        implementation(platform("androidx.compose:compose-bom:2024.06.00"))

        // 2. AHORA, TODAS LAS DEPENDENCIAS DE COMPOSE VAN SIN VERSIÓN EXPLÍCITA
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.activity:activity-compose")
        implementation("androidx.lifecycle:lifecycle-runtime-compose")

        // --- DEPENDENCIAS DE MATERIAL 3 (SIN VERSIÓN) ---
        // El BOM también seleccionará la versión correcta y compatible de Material3
        implementation("androidx.compose.material3:material3")

        // El resto de tus dependencias de Compose también deben ir sin versión
        implementation("androidx.compose.foundation:foundation")
        implementation("androidx.compose.animation:animation")
        implementation("androidx.compose.material:material-icons-core")
        implementation("androidx.compose.material:material-ripple")

        // Para las previews y testing
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")

        // --- LIBRERÍAS QUE CONSERVAN SU VERSIÓN (NO SON PARTE DEL BOM) ---
        implementation("androidx.navigation:navigation-compose:2.7.7")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose") // La versión la gestiona el BOM

        // --- DEPENDENCIAS NORMALES DE ANDROID Y OTRAS (Estas no cambian) ---
        implementation("androidx.core:core-ktx:1.13.1")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
        implementation("com.squareup.retrofit2:retrofit:2.11.0")
        implementation("com.squareup.retrofit2:converter-gson:2.11.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    }
}
