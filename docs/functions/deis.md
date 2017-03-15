# Deis

Functions related to [Deis](https://deis.io).

## deisLogin
Login to Deis controller

#### Usage

```groovy
deisLogin(server, credentials, deis_executable)
```

```groovy
deisLogin(server, credentials, deis_executable) {
  body
}
```

server
: is the full URL of the deis controller (e.g. `https://deis.example.com`)

credentials
: is the ID (String) of a Jenkins Password Credentials Entry with username and
password for the Deis controller.

deis_executable
: Optional. Defaults to `deis`.

When used in combination with a Closure will execute `deisLogout` after
evaluating the Closure contents.

#### Example

```groovy
deisLogin("https://deis.example.com", "deis-credentials")
```

```groovy
deisLogin("https://deis.example.com", "deis-credentials", 'deis2') {
  deisCreate("example-app")
}
```

## deisLogout
Logout from Deis controller.

#### Usage
```groovy
deisLogout(deis_executable)
```

deis_executable
: Optional. Defaults to `deis`.

## deisCreate
Create Deis apps

#### Usage

```groovy
deisCreate(app_name, deis_executable)
```

app_name
: Name of the Deis app to be created.

deis_executable
: Optional. Defaults to `deis`.

## deisPull
Create a new Docker Image Build for an App.

#### Usage
```groovy
deisPull(app_name, docker_image, deis_executable)
```

app_name
: Name of the Deis app (e.g. `example-app`)

docker_image
: Docker image name and tag to deploy. (e.g.: `mozorg/bedrock:latest`)

deis_executable
: Optional. Defaults to `deis`.

```groovy
deisPull(app_name, docker_image, procfile)
```

procfile
: Procfile to use. Must exist in the current directory of the Jenkins node.

#### Example
```groovy
deisPull("example-app", "mozorg/example:latest")
```

```groovy
deisPull("example-app", "mozorg/example:latest", "Procfile")
```
