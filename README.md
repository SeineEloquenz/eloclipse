# jdt-codemining

Eclipse plugin for `JDT Java Code Mining` and `Git Code Mining` (Experimental)

JDT CodeMining is a an Eclipse plugin which requires `Eclipse Photon` based on JFace Text which provides [CodeMining support](https://www.eclipse.org/eclipse/news/4.8/M5/#Platform-Dev). 


## Requirements

`JDT Java CodeMining` requires `Eclipse Photon` (4.8) or above.

It is compatible with `Eclipse 2018-12` (4.10) in which [basic CodeMining features](https://www.eclipse.org/eclipse/news/4.10/jdt.php#jdt-codemining) are provided and enriched by `JDT Java CodeMining`.

# Install and activation

To install `JDT Java CodeMining`:

 * install last build of Eclipse Photon from https://www.eclipse.org/downloads/eclipse-packages/
 * install jdt-codemining with update site http://oss.opensagres.fr/jdt-codemining/snapshot/

By default minings are disabled, you must activate them with preferences:

## Show variable values inline while debugging

To show the result of the toString() method of an Object instead of for example `myObject= Integer  (id=20)` it is recommended to enable the following option:

Go to Preferences > Java > Debug > Detail Formatters. On the section `Show variable details ('toString()' value)` select `As the label for all variables`.

# Features

jdt-codemining provides several JDT Java CodeMining:

 * `General`
   * `Show references`
   * `Show implementations`
   * `Show method parameter names`
   * `Show method parameter types`
   * `Show end statement`
 * `JUnit`
   * `Show JUnit status`
   * `Show JUnit run`
   * `Show JUnit debug`
* `Debugging`
   * `Show variable values inline while debugging`
