enablePlugins(SbtWar)

organization := "Lift"

name := "seventhings"

version := "0.5"

scalaVersion := "2.13.18"

scalacOptions ++= Seq("-deprecation", "-unchecked")

resolvers ++= Seq("snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
                  "staging"       at "https://oss.sonatype.org/content/repositories/staging",
                  "releases"      at "https://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= {
  val liftVersion = "4.0.0-M4"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion,
    "org.webjars.bower" % "jquery" % "3.7.1",
    "org.eclipse.jetty" % "jetty-webapp" % "11.0.25" % "test",
    "junit" % "junit" % "4.13.2" % "test",
    "ch.qos.logback" % "logback-classic" % "1.5.16",
    "org.specs2" %% "specs2-core" % "4.21.0" % "test",
    "org.specs2" %% "specs2-matcher-extra" % "4.21.0" % "test",
    "org.specs2" %% "specs2-junit" % "4.21.0" % "test",
    "com.h2database" % "h2" % "1.4.200"
  )
}
