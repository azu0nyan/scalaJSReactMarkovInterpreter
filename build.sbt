enablePlugins(ScalaJSBundlerPlugin)

name := "markovInterpreter"

scalaVersion := "2.13.6"

Compile / npmDependencies += "react" -> "16.13.1"
Compile / npmDependencies += "react-dom" -> "16.13.1"
Compile / npmDependencies += "react-proxy" -> "1.1.8"

Compile / npmDevDependencies += "file-loader" -> "6.2.0"
Compile / npmDevDependencies += "style-loader" -> "2.0.0"
Compile / npmDevDependencies += "css-loader" -> "5.2.6"
Compile / npmDevDependencies += "html-webpack-plugin" -> "4.5.1"
Compile / npmDevDependencies += "copy-webpack-plugin" -> "6.4.0"
Compile / npmDevDependencies += "webpack-merge" -> "5.8.0"

libraryDependencies += "me.shadaj" %%% "slinky-web" % "0.6.7"
libraryDependencies += "me.shadaj" %%% "slinky-hot" % "0.6.7"

libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.9" % Test

scalacOptions ++= Seq(
  "-encoding", "utf8", // Option and arguments on same line
//  "-Xfatal-warnings",  // New lines for each options
  "-Ymacro-annotations",
//  "-unchecked",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps"
)


webpack / version := "4.44.2"
startWebpackDevServer / version := "3.11.2"

webpackResources := baseDirectory.value / "webpack" * "*"

fastOptJS / webpackConfigFile := Some(baseDirectory.value / "webpack" / "webpack-fastopt.config.js")
fullOptJS / webpackConfigFile := Some(baseDirectory.value / "webpack" / "webpack-opt.config.js")
Test / webpackConfigFile := Some(baseDirectory.value / "webpack" / "webpack-core.config.js")

fastOptJS / webpackDevServerExtraArgs := Seq("--inline", "--hot", "--host:0.0.0.0")
fastOptJS / webpackBundlingMode := BundlingMode.LibraryOnly()

Test / requireJsDomEnv := true

addCommandAlias("dev", ";fastOptJS::startWebpackDevServer;~fastOptJS")

addCommandAlias("build", "fullOptJS::webpack")
