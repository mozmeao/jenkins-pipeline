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
