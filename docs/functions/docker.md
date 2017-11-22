# Docker

Functions related to [Docker](https://docker.io/).

## dockerImageBuild

Build Docker images

#### Usage
```groovy
dockerImageBuild(image_id)
```

Build an image tagged with `image_id` using Dockerfile from the current directory.

image_id
: Tag to use for resulting image

```groovy
dockerImageBuild(image_id, args)
```

args
: Groovy Map array with additional build tags.

args.pull
: Default `true`. Always pull base images from registry.

args.no_cache
: Default `false`. Build without layer caching.

args.dockerfile
: Default `Dockerfile`. Define Dockerfile to use.

args.cache_from
: Use an existing image as cache.

args.extra_docker_args
: Extra docker args that will be supplied to docker command directly.


#### Example

```groovy
dockerImageBuild("example:foo", ["pull": true,
                                 "no_cache": false",
                                 "dockerfile": "DockerfileTest"])
```

Build an image tagged `example:foo` using `DockefileTest` from the current
directory with extra `--pull=true` and `--no-cache=false` docker arguments.

## dockerImageExists
Check if a Docker Image exists locally.

Returns `true` if image exists locally, `false` otherwise

#### Usage
```groovy
dockerImageExists(image_id)
```

image_id
: Docker image to check for

## dockerImagePull
Pull a Docker Image from a Registry

#### Usage
```groovy
dockerImagePull(image_id)
```

image_id
: Docker image to pull

#### Examples

```groovy
dockerImagePull("mozorg/bedrock:latest")
```

## dockerImagePush
Push a Docker Image to a Registry

Logins to registry, pushes the image and logs out.

#### Usage
```groovy
dockerImagePush(image_id, registry, credentials)
```

image_id
: Docker Image to push to Registry. Image must be already tagged for this
Registry. Consider using `dockerImageTag`.

registry
: Full URL of the registry to login to.

credentials
: is the ID (String) of a Jenkins Password Credentials Entry with username and
password for the Docker Registry.

#### Example

```groovy
dockerImagePush("mozilla/bedrockkuma:643fe5",
                "https://registry.hub.docker.com",
                "jenkins-docker-hub")
```

## dockerImageSquash
Squash a docker image.

Uses [docker-squash](https://github.com/goldmann/docker-squash/) so squash the
layers of an image.

#### Usage
```groovy
dockerImageSquash(image_id)
```
```groovy
dockerImageSquash(image_id, squashed_image_id)
```

image_id
: Docker Image to squash

squashed_image_id
: Optional. Name of the squashed image. If undefined will use `image_id`.

## dockerImageTag
Tag a Docker Image

#### Usage
```groovy
dockerImageTag(image_id, new_image_id)
```

image_id
: Docker Image to tag

new_image_id
: New tag for Docker Image.

## dockerRun
Run a command in a Docker Container

#### Usage
```groovy
dockerRun(image_id, cmd)
```

```groovy
dockerRun(image_id, args)
```

image_id
: Foo

args
: bar

args.cmd
: Optional. Command to run, overrides CMD.

args.bash_wrap
: Default `true`. Wrap `cmd` in a bash shell. Useful when you're running multiple commands in a run, e.g. `touch foo && echo "File created"`

args.docker_args
: Optional. Append to docker arguments.

args.copies
: Default `1`. Run multiple containers with this command. Useful when you're
launching workers.


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
dockerRun("mariadb:10.0",
          ["docker_args": "--name db " +
                          "-e MYSQL_ALLOW_EMPTY_PASSWORD=yes " +
                          "-e MYSQL_DATABASE=test"]) {
    dockerRun("giorgos/takis", ["docker_args": "--link db:db " +
                                               "-e CHECK_PORT=3306 " +
                                               "-e CHECK_HOST=db"])
 }
```

Will start a container based off `mariadb` in the background and then will run
another container based off `giorgos/takis` in the foreground and execute the
default image command. After the second command returns either successfully or
with an error will stop the `mariadb` container.

```groovy
dockerRun("selenium/hub:2.48.2",
          ["docker_args": "--name selenium-hub-${BUILD_TAG}"]) {
 dockerRun("selenium/node-firefox:2.48.2",
           ["docker_args": "--link selenium-hub-${BUILD_TAG}:hub",
            "copies": 5]) {
   // Run integration tests
 }
}
```

Will start a container based off `selenium/hub` in the background and then will
start 5 containers based off `selenium/node-firefox` in the background. Will
stop everything before returning.

## dockerStop
Stop a Docker container

#### Usage
```groovy
dockerStop(container_id)
```

container_id
: Docker container ID to stop (e.g. `snippets-mariadb-10`
)
