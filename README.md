# EasyShared

### A Library For Using Shared Preferences Easier

## Usage:

```kotlin
    var token:String by savable("auth_token")
    var age:Int by savable() /* savable("age") */
    var loggedIn:Boolean by savable() /* savable("loggedIn") */

    var phone by savableInt()
    var name by savableString()
    var timestamp by savableLong()
```

#### Note: you can pass variable key to save in shared preferences, but if you don't, it will save in variable name, for exmaple:

```kotlin
    var username:String by savable()
```
#### Here, that username will save in 'username' key, and:
```kotlin
    var username:String by savable("name")
```
#### Here, that username will save in 'user' key

### Updating
#### For update and re-save variable value in shared prefrences, just update that savable value, that's it! , for example:
```kotlin
  var token:String by savable("auth_token")

  token = "xxxxx"
```
```kotlin
  var userId by savableInt()

  userId = 43
```
#### No need to do anything else.


## Final Sample In Jetpack Compose:

```kotlin
  var token:String by savable("auth_token")

  Button(onClick = {
      token = UUID.randomUUID().toString()
  }) {
     Text(text = "Set")
  }
  Button(onClick = {
      Toast.makeText(context, token, Toast.LENGTH_SHORT).show()
  }) {
      Text(text = "Get")
  }
```

<hr/>

## Dependency

### Add the JitPack repository to your build file

#### Add Jitpack Maven in your settings.gradle file:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

#### Kotlin DSL(build.gradle.kts):

```kotlin
    repositories {
        ...
        maven("https://jitpack.io")
    }   
```

## Add the dependency

[![](https://jitpack.io/v/ehsannarmani/EasyShared.svg)](https://jitpack.io/#ehsannarmani/EasyShared)

#### Groovy:
```groovy
dependencies {
      implementation 'com.github.ehsannarmani:EasyShared:latest_version'
}
```
#### Kotlin DSL (build.gradle.kts):
```groovy
dependencies {
    implementation("com.github.ehsannarmani:EasyShared:latest_version")
}
```

