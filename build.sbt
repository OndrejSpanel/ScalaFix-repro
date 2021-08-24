name := "ScalaFix-repro"

ThisBuild / scalafixDependencies += "org.scala-lang.modules" %% "scala-collection-migrations" % "2.5.0"

ThisBuild / scalafixDependencies += "org.scala-lang" %% "scala-rewrites" % "0.1.3"


addCompilerPlugin(scalafixSemanticdb)
scalacOptions ++= List("-Yrangepos", "-P:semanticdb:synthetics:on")

scalaVersion := "2.12.14"
scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Ywarn-adapted-args"
)

libraryDependencies += "org.scala-lang.modules" %% "scala-collection-compat" % "2.5.0"

val borerVersion = "1.6.3"

libraryDependencies ++= Seq(
  "io.bullet" %% "borer-core" % borerVersion,
  "io.bullet" %% "borer-derivation" % borerVersion
)