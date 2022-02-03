plugins {
    `version-catalog`
    `maven-publish`
    signing
}

catalog {
    versionCatalog {
        alias("appCompat").to("androidx.appcompat", "appcompat").version("1.4.1")
        alias("core").to("androidx.core", "core").version("1.7.0")
        alias("constraintLayout").to("androidx.constraintlayout", "constraintlayout").to("2.1.3")
    }
}

val mavenCentralRepositoryUsername: String by project
val mavenCentralRepositoryPassword: String by project

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "dev.assemblage"
            artifactId = "androidx-catalog"
            version = "0.0.2"
            from(components["versionCatalog"])
            pom {
                name.set("Androidx Catalog")
                description.set("Catalog for commonly used androidx dependencies.")
                url.set("https://umang91.github.io")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("umang91")
                        name.set("Umang Chamaria")
                    }
                }
                scm {
                    // version control meta data
                    connection.set("scm:git@github.com:umang91/androidx-version-catalog.git")
                    developerConnection.set("scm:git@github.com:umang91/androidx-version-catalog.git")
                    url.set("http://github.com/umang91/androidx-version-catalog")
                }
            }
        }
        repositories {
            maven {
                credentials {
                    username=mavenCentralRepositoryUsername
                    password=mavenCentralRepositoryPassword
                }
                url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            }
        }
    }
}

signing {
    sign(publishing.publications["maven"])
}