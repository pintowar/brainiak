name := "brainiak"

version := "0.1"

scalaVersion := "2.11.2"

unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar"))

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.3-SNAP1"

libraryDependencies += "junit" % "junit" % "4.10" % "test"

libraryDependencies += "org.scalafx" % "scalafx_2.11" % "8.0.20-R6"

libraryDependencies += "com.netflix.rxjava" % "rxjava-scala" % "0.20.4"
