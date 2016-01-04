import com.beust.kobalt.*
import com.beust.kobalt.plugin.kotlin.*
import com.beust.kobalt.plugin.packaging.*
import com.beust.kobalt.plugin.publish.*
import java.lang.System

val repos = repos("https://dl.bintray.com/levelmoney/maven")

val p = kotlinProject {

    name = "bismarck4-android"
    group = "com.levelmoney"
    artifactId = name
    version = "0.2"

    sourceDirectories {
        path("src/main/java")
        path("src/main/resources")
        path("src/main/res")
    }

    sourceDirectoriesTest {
        path("src/test/java")
        path("src/test/resources")
        path("src/test/res")
    }

    dependencies {
        compile("com.levelmoney:bismarck4:0.2")
        provided("com.google.android:android:4.1.1.4")
    }

    dependenciesTest {
        compile("junit:junit:4.12")
        compile("org.jetbrains.kotlin:kotlin-test:1.0.0-beta-4584")

    }

    assemble {
        mavenJars {
        }
    }

    jcenter {
        publish = true
    }
}
