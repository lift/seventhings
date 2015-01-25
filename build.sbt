seq(webSettings :_*)

organization := "Lift"

name := "seventhings"

version := "0.3"

scalaVersion := "2.9.1"

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val liftVersion = "2.6"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion,
    "net.liftweb" %% "lift-wizard" % liftVersion,
    "org.mortbay.jetty" % "jetty" % "6.1.22" % "container; test",
    "junit" % "junit" % "4.10" % "test",
    "ch.qos.logback" % "logback-classic" % "1.0.6",
    "org.specs2" %% "specs2" % "1.11" % "test",
    "com.h2database" % "h2" % "1.3.167"
  )
}
