seq(webSettings :_*)

organization := "Lift"

name := "seventhings"

version := "0.3"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-deprecation", "-unchecked")

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= {
  val liftVersion = "2.6.2"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion,
    "net.liftweb" %% "lift-wizard" % liftVersion,
    "javax.servlet"     %  "servlet-api"        % "2.5" % "provided",
    "org.eclipse.jetty" % "jetty-webapp"        % "9.2.3.v20140905"  % "compile,container",
    "junit" % "junit" % "4.10" % "test",
    "ch.qos.logback" % "logback-classic" % "1.0.6",
    "org.specs2" %% "specs2-core" % "3.5" % "test",
    "com.h2database" % "h2" % "1.3.167"
  )
}
