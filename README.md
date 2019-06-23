# Sample App

This is sample Kotlin cross-platform starter app using [libGDX](https://libgdx.badlogicgames.com).

You **must** edit `local.properties` to point to your own Android SDK.

It is written using the new Kotlin DSL for Gradle. 
The standard libGDX Groovy scripts have been rewritten for Kotlin.

The iOS support is via the [Mobi fork](http://robovm.mobidevelop.com) of RoboVM, due to the lack of response from [Intel/Migeran's MOE](https://multi-os-engine.org).

This project was created from scratch, borrowing liberally from libGDX's usual starter buildscripts and the Gradle/Kotlin community at large.

To build and run the desktop app, just use:

`./gradlew desktop:run`
