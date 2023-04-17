import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.4"
	id("io.spring.dependency-management") version "1.1.0"
	id("jacoco")
	id("org.jmailen.kotlinter") version "3.13.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"

}

group = "com.shopping.optimization"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	//Spring
	//implementation("org.springframework.boot:spring-boot-starter-actuator")
	//implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework:spring-jdbc")
	//implementation("org.springframework.cloud:spring-cloud-starter-config")
	// https://mvnrepository.com/artifact/io.r2dbc/r2dbc-postgresql
	//implementation("io.r2dbc:r2dbc-postgresql:0.8.13.RELEASE")


	// Kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

	// Database
	implementation("org.flywaydb:flyway-core")
	runtimeOnly("org.postgresql:postgresql")
	runtimeOnly("org.postgresql:r2dbc-postgresql")


	// Metric
	//implementation("io.micrometer:micrometer-registry-prometheus")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")

	//testImplementation("org.springframework.boot:spring-boot-starter-test")
	//testImplementation("org.springframework.cloud:spring-cloud-contract-wiremock")
	//testImplementation("io.projectreactor:reactor-test")
	//testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
	//testImplementation("net.datafaker:datafaker:1.5.0")

	// Mac Support
	runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.81.Final:osx-aarch_64")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.1")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootRun {
	doFirst {
		systemProperty("database.endpoint", System.getenv("DATABASE_ENDPOINT") ?: "localhost:5432")
		systemProperty("database.name", System.getenv("DATABASE_NAME") ?: "db_grad")
		systemProperty("database.password", System.getenv("DATABASE_PASSWORD") ?: "db_pass")
		systemProperty("database.user", System.getenv("DATABASE_USER") ?: "elifarikan_valensas")
	}
}