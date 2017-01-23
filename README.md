# Jenkins Pipeline Functions

This repository contains a list of global functions and libraries for Jenkins
Pipelines developed and used by [MozMEAO](https://mozilla.github.io/meao/).


## List of functions

### dockerImageBuild

Build Docker images

#### Usage
```groovy
dockerImageBuild(image_tag)
```
Builds an image tagged with `image_tag` using Dockerfile from the current directory.

```groovy
dockerImageBuild(image_tag, ["pull": true, "no_cache": false", "dockerfile": "DockerfileTest"])
```

Build an image tagged with `image_tag` using `DockefileTest` from the current
directory with extra `--pull=true` and `--no-cache=false` docker arguments.

All the extra arguments (`pull`, `no_cache`, `dockerfile`) are *optional*.

### dockerImageExists
Check if a Docker Image exists locally.

Returns `true` if image exists locally, `false` otherwise

#### Usage
```groovy
dockerImageExists(image_tag)
```


### dockerImagePull
Pull a Docker Image from a registry

#### Usage
```groovy
dockerImagePull(image_tag)
```

### dockerImagePush
Push a Docker Image to a Registry

Logins to registry, pushes the image and logs out.

#### Usage
```groovy
dockerImagePush(image_tag, registry, credentials)
```

credentials is the ID (String) of a Jenkins Userwith with Password Credentials
Entry.

#### Example

```groovy
dockerImagePush("mozilla/kuma:643fe5", "https://registry.hub.docker.com", "kuma-creds")
```

### dockerImageSquash
Squash a docker image.

Uses [docker-squash](https://github.com/goldmann/docker-squash/) so squash the
layers of an image.

#### Usage
```groovy
dockerImageSquash(image_tag)
```
```groovy
dockerImageSquash(image_tag, squashed_image_tag)
```

### dockerImageTag
Tag a Docker Image

#### Usage
```groovy
dockerImageTag(image_tag, new_image_tag)
```

### dockerRun
Runs command in Docker Container

#### Usage
```groovy
dockerRun(image_tag, cmd)
```
```groovy
dockerRun(image_tag, ["cmd": "ls", "bash_wrap": false, "docker_args": "-e foo -v `pwd`:/data", "copies": 5])
```

All arguments are optional

#### Examples

```groovy
dockerRun("debian:latest", "ls")
```

Will run `ls` in a container based off `debian:latest` image

```groovy
dockerRun("debian:latest", "ls && echo foo")
```

Will run `ls && echo foo` in a container based on `debian:latest` image because
the default mode is to wrap the command with a bash shell using `sh -c cmd`.


```groovy
dockerRun("mariadb:10.0", ["docker_args": "--name db -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_DATABASE=test"]) {
    dockerRun("giorgos/takis", ["docker_args": "--link db:db -e CHECK_PORT=3306 -e CHECK_HOST=db"])
 }
```

Will start a container based off `mariadb` in the background and then will run
another container based off `giorgos/takis` in the foreground and execute the
default image command. After the second command returns either successfully or
with an error will stop the `mariadb` container.

```groovy
dockerRun("selenium/hub:2.48.2", ["docker_args": "--name selenium-hub-${BUILD_TAG}"]) {
 dockerRun("selenium/node-firefox:2.48.2", ["docker_args": "--link selenium-hub-${BUILD_TAG}:hub", "copies": 5]) {
   // Run integration tests
 }
}
```

Will start a container based off `selenium/hub` in the background and then will
start 5 containers based off `selenium/node-firefox` in the background. Will
stop everything before returning.

### dockerStop
Stops a docker container

#### Usage
```groovy
dockerStop(image_tag)
```

### irc
Send an IRC message

Will read `jenkins.yml` file and send IRC message. Works with TLS enabled
servers only.

#### Usage
```groovy
irc(message)
```

#### Example `jenkins.yml`:
```yaml
irc:
  nickname: "jenkins-bot"
  channel: "#integration-tests"
  server: "irc.mozilla.org:6697"
```

### optional
Execute optional steps.

Executes steps that can fail without affecting the status of the Pipeline.

#### Example

```groovy
optional {
  sh "bash -c 'false'"
}
```

### setGitEnvironmentVariables

Set GIT_COMMIT and GIT_COMMIT_SHORT environment variables.

#### Example:

```groovy
setGitEnvironmentVariables()
echo env.GIT_COMMIT_SHORT
```

## See Also

* [Pipeline Shared Libraries](https://github.com/jenkinsci/workflow-cps-global-lib-plugin) Documentation
