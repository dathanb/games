/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java project to get you started.
 * For more details take a look at the Java Quickstart chapter in the Gradle
 * User Manual available at https://docs.gradle.org/5.4.1/userguide/tutorial_java_projects.html
 */

plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building an application
    application
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    implementation("com.google.guava:guava:27.0.1-jre")
    implementation("org.slf4j:slf4j-log4j12:1.7.28")
    implementation("io.dropwizard.metrics:metrics-core:4.1.2")

    // Use JUnit test framework
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
}

application {
    // Define the main class for the application
    mainClassName = "com.jedaway.games.App"
}
