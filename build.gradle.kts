plugins {
    application
}

repositories {
    mavenCentral()
}

application {
    mainClass = "com.splunk.TestApp"
}


dependencies {
    implementation("software.amazon.awssdk:s3:2.30.7")
    implementation("io.opentelemetry.instrumentation:opentelemetry-instrumentation-annotations:2.12.0")
}