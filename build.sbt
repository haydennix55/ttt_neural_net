import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "ttt_neural_net",
    libraryDependencies ++= Seq(
      "org.scala-js" %% "scalajs-test-interface" % "0.6.14",
      "org.scalatest" %% "scalatest" % "3.0.1", //version changed as these the only versions supported by 2.12
      "com.novocode" % "junit-interface" % "0.11",
      "org.scala-lang" % "scala-library" % scalaVersion.value
    )
  )
