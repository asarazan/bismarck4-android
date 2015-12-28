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
    version = "0.1"

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
//        compile("com.beust:jcommander:1.48")
        compile("com.levelmoney:bismarck4:0.1")
        provided("com.google.android:android:4.1.1.4")
    }

    dependenciesTest {
//        compile("org.testng:testng:6.9.5")

    }

    assemble {
        mavenJars {
        }
    }
}
