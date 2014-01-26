name := "brainiak"

version := "0.1"

scalaVersion := "2.10.2"

unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/lib/jfxrt.jar"))

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

libraryDependencies += "junit" % "junit" % "4.10" % "test"

libraryDependencies += "org.scalafx" % "scalafx_2.10" % "1.0.0-M7"

libraryDependencies += "com.netflix.rxjava" % "rxjava-scala" % "0.16.1"

