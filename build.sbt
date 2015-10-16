name := "rock-paper-scissors"
organization := "com.fabiocognigni"

version := "1.0"

scalaVersion := "2.11.7"

val scalatestVersion = "2.2.4"

//test
libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % scalatestVersion % "test")

coverageMinimum := 95
coverageFailOnMinimum := true