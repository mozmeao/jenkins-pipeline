# Pipeline

## conduit
Wrap all Pipeline steps with default settings.
Enable [`timestamps`](https://wiki.jenkins-ci.org/display/JENKINS/Timestamper)
and send notifications
using [`sendNotifications`](notifications.md#sendnotifications) before Pipeline
exit.

#### Usage
```groovy
conduit {
  // code
}
```

#### Example

```groovy
conduit {
  node {
    stage("Checkout") {
      checkout scm
    }
    stage("Test") {
      sh 'make tests'
    }
  }
}

```

## getCurrentBuildStatus
Get current build status.

Returns Groovy String `SUCCESS` or `FAILURE`.

#### Usages
```groovy
def status = getCurrentBuildStatus()
```

## optional
Execute optional steps.

Executes steps that can fail without affecting the status of the Pipeline.

#### Usage
```groovy
optional {
  // code
}

```

#### Example

```groovy
optional {
  sh "bash -c 'false'"
}
```

## setConfigEnvironmentVariables
Set environment variables specified in your `jenkins.yml` file.

Add an `environment` section to your `jenkins.yml` file and those variables will be loaded into
the build environment when you call this function, optionally passing to it the loaded config.
It will attempt to load `jenkins.yml` if no config is passed in.

#### Usage
```groovy
setConfigEnvironmentVariables()
```

#### Example
```yaml
# jenkins.yml
environment:
  SELENIUM_VERSION: 2.52.0
  DUDE_STATUS: abiding
```

```groovy
// Jenkinsfile
config = readYaml file: 'jenkins.yml'
setConfigEnvironmentVariables(config)
```
