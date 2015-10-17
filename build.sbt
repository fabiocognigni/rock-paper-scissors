name := "rock-paper-scissors"
organization := "com.fabiocognigni"

version := "1.0"

scalaVersion := "2.11.7"

val scalatestVersion = "2.2.4"

/**
 * Dependencies for tests
 */
libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % scalatestVersion % "test")

/**
 * Coverage settings
 */
coverageMinimum := 95
coverageFailOnMinimum := true
//Excluding main class since meant to drive the manual testing and it's not observable (it only prints to stdout)
//coverageExcludedFiles := ".*PlayGame"

/**
 * Assembly settings
 */
mainClass in assembly := Some("com.fabiocognigni.rock_paper_scissors.PlayGame")
assemblyJarName in assembly := "rock-paper-scissors-" + version.value + ".jar"
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}