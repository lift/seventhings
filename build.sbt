seq(webSettings :_*)

organization := "Lift"

name := "seventhings"

version := "0.1"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq(
    "net.liftweb" %% "lift-webkit" % "2.4",
    "net.liftweb" %% "lift-wizard" % "2.4",
    "org.mortbay.jetty" % "jetty" % "6.1.22" % "container",
    "org.mortbay.jetty" % "jetty" % "6.1.22" % "test",
    "junit" % "junit" % "4.10" % "test",
    "ch.qos.logback" % "logback-classic" % "1.0.6",
    "org.specs2" %% "specs2" % "1.11" % "test",
    "com.h2database" % "h2" % "1.3.167"
)
