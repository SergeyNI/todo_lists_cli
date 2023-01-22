val scala3Version = "3.2.1"
// crossScalaVersions ++= Seq("2.13.6", "3.2.1")

lazy val root = project
  .in(file("."))
  .settings(
    name := "TODO lists board",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test",
    libraryDependencies += "org.scalatest" %% "scalatest-funspec" % "3.2.14" % "test",
    libraryDependencies += "org.scalatestplus" %% "scalacheck-1-17" % "3.2.14.0" % "test",
    libraryDependencies += "io.spray" %%  "spray-json" % "1.3.6",
    libraryDependencies += "org.typelevel" %% "cats-core" % "2.9.0"
  )
// lazy val doobieVersion = "1.0.0-RC2"

// libraryDependencies ++= Seq(
//   "org.tpolecat" %% "doobie-core"     % doobieVersion,
//   "org.tpolecat" %% "doobie-postgres" % doobieVersion,
//   "org.tpolecat" %% "doobie-specs2"   % doobieVersion
// )
