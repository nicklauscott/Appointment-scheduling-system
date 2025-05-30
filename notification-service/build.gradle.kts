import com.google.protobuf.gradle.id

plugins {
	java
	id("org.springframework.boot") version "3.5.0"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.google.protobuf") version "0.9.4"
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

dependencies {
	// gRPC
	implementation("io.grpc:grpc-netty-shaded:1.69.0")
	implementation("io.grpc:grpc-protobuf:1.69.0")
	implementation("io.grpc:grpc-stub:1.69.0")

	// For Java 9+ compatibility
	compileOnly("org.apache.tomcat:annotations-api:6.0.53")

	// Protobuf
	implementation("com.google.protobuf:protobuf-java:4.29.1")

	// Spring Boot gRPC Integration
	implementation("net.devh:grpc-spring-boot-starter:3.1.0.RELEASE")


	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.kafka:spring-kafka")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	//testImplementation("org.springframework.grpc:spring-grpc-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:3.25.5"
	}
	plugins {
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:1.68.1"
		}
	}
	generateProtoTasks {
		all().forEach { task ->
			task.plugins {
				id("grpc")
			}
		}
	}
}



jib {
	to {
		image = "notification-service"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
