name := "RadisWithScalaApp"

version := "0.1"

scalaVersion := "2.13.10"
libraryDependencies ++= Seq("net.debasishg" %% "redisclient" % "3.41")
libraryDependencies ++= Seq("com.lihaoyi" %% "upickle" % "1.6.0")
libraryDependencies ++= Seq("com.lihaoyi" %% "ujson" % "0.9.6")
