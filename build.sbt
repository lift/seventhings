enablePlugins(JettyPlugin)

organization := "Lift"

name := "seventhings"

version := "0.4"

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-deprecation", "-unchecked")

resolvers ++= Seq("snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
                  "staging"       at "https://oss.sonatype.org/content/repositories/staging",
                  "releases"      at "https://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= {
  val liftVersion = "2.6.2"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion,
    "net.liftweb" %% "lift-wizard" % liftVersion,
    "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container,test",
    "junit" % "junit" % "4.10" % "test",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "org.specs2" %% "specs2" % "2.3.11" % "test",
    "com.h2database" % "h2" % "1.3.167"
  )
}