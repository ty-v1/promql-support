import org.jetbrains.intellij.platform.gradle.TestFrameworkType

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.10"
    id("org.jetbrains.intellij.platform") version "2.3.0"
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
}

group = "io.github.tyv1.idea.promql"
version = "0.0.1"

kotlin {
    jvmToolchain(21)
}

sourceSets {
    main {
        java {
            srcDirs("src/main/gen")
        }
    }
}

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // logging
    implementation("ch.qos.logback:logback-classic:1.5.18")
    implementation("org.slf4j:slf4j-api:2.0.17")

    // unit test
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    intellijPlatform {
        intellijIdeaCommunity("2024.3.5")

        bundledPlugins("org.jetbrains.kotlin")

        pluginVerifier()
        zipSigner()

        testFramework(TestFrameworkType.Platform)
        testFramework(TestFrameworkType.Plugin.Java)
        testFramework(TestFrameworkType.Metrics)
    }
}

// Configure Gradle IntelliJ Plugin
intellijPlatform {

    pluginConfiguration {
        ideaVersion {
            sinceBuild = "241"
            untilBuild = "243.*"
        }
    }

    signing {
        certificateChain = System.getenv("CERTIFICATE_CHAIN")
        privateKey = System.getenv("PRIVATE_KEY")
        password = System.getenv("PRIVATE_KEY_PASSWORD")
    }

    publishing {
        token = System.getenv("PUBLISH_TOKEN")
    }

    pluginVerification {
        ides {
            recommended()
        }
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    compileJava {
        dependsOn(generateLexer)
    }
    
    compileKotlin {
        dependsOn(generateLexer)
    }

    processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    processTestResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    // GrammarKit configuration for parser and lexer generation
    generateLexer {
        sourceFile.set(file("src/main/kotlin/io/github/tyv1/idea/promql/language/PromQL.flex"))
        targetOutputDir.set(file("src/main/gen/io/github/tyv1/idea/promql/language"))
        purgeOldFiles.set(true)
    }

//    generateParser {
//        sourceFile.set(file("src/main/kotlin/io/github/tyv1/idea/promql/language/PromQL.bnf"))
//        targetRootOutputDir.set(file("src/main/gen"))
//        purgeOldFiles.set(true)
//        pathToParser.set("/io/github/tyv1/idea/promql/language/parser/PromQLParser.java")
//        pathToPsiRoot.set("/io/github/tyv1/idea/promql/language/psi")
//        purgeOldFiles.set(true)
//    }
}
