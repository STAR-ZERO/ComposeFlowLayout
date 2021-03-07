/**
 * Copyright 2021 Kenji Abe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.dokka")
    id("signing")
    id("maven-publish")
}

val composeVersion: String by project

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFile("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
        freeCompilerArgs = listOf("-Xexplicit-api=strict")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
}

dependencies {
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}

val sonatypeUsername = if (rootProject.hasProperty("sonatypeUsername")) {
    rootProject.property("sonatypeUsername") as String
} else {
    ""
}
val sonatypePassword = if (rootProject.hasProperty("sonatypePassword")) {
    rootProject.property("sonatypePassword") as String
} else {
    ""
}

tasks.dokkaJavadoc.configure {
    outputDirectory.set(buildDir.resolve("javadoc"))
    dokkaSourceSets {
        named("main") {
            noAndroidSdkLink.set(false)
        }
    }
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

val javadocJar by tasks.registering(Jar::class) {
    dependsOn(tasks.dokkaJavadoc)
    archiveClassifier.set("javadoc")
    from(buildDir.resolve("javadoc"))
}

tasks.create("myTask") {
    doLast {
        println("Property: ${project.extra["artifactId"]}")
    }
}

group = project.extra["groupId"] as String
version = project.extra["versionName"] as String

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                artifactId = project.extra["artifactId"] as String
                from(components["release"])
                artifact(javadocJar)
                artifact(sourcesJar)
                pom {
                    name.set("Compose FlowLayout")
                    description.set("A FlowLayout for Jetpack Compose")
                    url.set("https://github.com/STAR-ZERO/ComposeFlowLayout")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("star_zero")
                            name.set("Kenji Abe")
                            email.set("kenji.01.star@gmail.com")
                        }
                    }
                    scm {
                        connection.set("https://github.com/STAR-ZERO/ComposeFlowLayout.git")
                        developerConnection.set("https://github.com/STAR-ZERO/ComposeFlowLayout.git")
                        url.set("https://github.com/STAR-ZERO/ComposeFlowLayout")
                    }
                }
            }

            repositories {
                maven {
                    val releasesRepoUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
                    val snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots")
                    url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
                    credentials {
                        username = sonatypeUsername
                        password = sonatypePassword
                    }
                }
            }
        }
    }
}

signing {
    sign(publishing.publications)
}
