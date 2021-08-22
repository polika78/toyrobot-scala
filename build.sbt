import Dependencies._

ThisBuild / scalaVersion     := "2.13.6"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.toyrobot"
ThisBuild / organizationName := "toyrobot"

lazy val root = (project in file("."))
  .settings(
    name := "toyrobot",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0",
    libraryDependencies += "org.typelevel" %% "cats-effect" % "2.0.0"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
