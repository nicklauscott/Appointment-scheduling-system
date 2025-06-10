plugins {
	java
	id("org.springframework.boot") version "3.5.0"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.google.cloud.tools.jib") version "3.4.0"
}

group = "com.ams"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2025.0.0"

dependencies {

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	implementation("org.springframework.cloud:spring-cloud-starter-gateway-server-webflux")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

jib {
	from {
		image = "eclipse-temurin:21-jre"
	}
	to {
		image = "api-gateway:latest"
	}
	container {
		ports = listOf("4040")
		jvmFlags = listOf("-Dspring.profiles.active=default")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
