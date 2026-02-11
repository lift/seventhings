enablePlugins(JettyPlugin)

organization := "Lift"

name := "seventhings"

version := "0.5"

scalaVersion := "2.12.21"

scalacOptions ++= Seq("-deprecation", "-unchecked")

// Allow scala-xml version eviction for compatibility between Scala 2.12.21 and Lift 3.5.0
libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always

resolvers ++= Seq("snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
                  "staging"       at "https://oss.sonatype.org/content/repositories/staging",
                  "releases"      at "https://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= {
  val liftVersion = "3.5.0"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion,
    "net.liftmodules" %% "lift-jquery-module_3.3" % "2.10",
    "org.eclipse.jetty" % "jetty-webapp" % "9.4.56.v20240826" % "test",
    "junit" % "junit" % "4.13.2" % "test",
    "ch.qos.logback" % "logback-classic" % "1.5.16",
    "org.specs2" %% "specs2-core" % "4.20.9" % "test",
    "org.specs2" %% "specs2-matcher-extra" % "4.20.9" % "test",
    "org.specs2" %% "specs2-junit" % "4.20.9" % "test",
    "com.h2database" % "h2" % "1.4.200"
  )
}