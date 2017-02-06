# Deis

Functions related to [Deis](https://deis.io).

## deisLogin
Login to Deis controller

#### Usage

```groovy
deisLogin(server, credentials)
```

```groovy
deisLogin(server, credentials) {
  body
}
```

server
: is the full URL of the deis controller (e.g. `https://deis.example.com`)

credentials
: is the ID (String) of a Jenkins Password Credentials Entry with username and
password for the Deis controller.

When used in combination with a Closure will execute `deisLogout` after
evaluating the Closure contents.

#### Example

```groovy
deisLogin("https://deis.example.com", "deis-credentials")
```

```groovy
deisLogin("https://deis.example.com", "deis-credentials") {
  deisCreate("example-app")
}
```

## deisLogout
Logout from Deis controller.

#### Usage
```groovy
deisLogout()
```

## deisCreate
Create Deis apps

#### Usage

```groovy
deisCreate(app_name)
```

app_name
: Name of the Deis app to be created.

## deisPull
Create a new Docker Image Build for an App.

#### Usage
```groovy
deisPull(app_name, docker_image)
```

app_name
: Name of the Deis app (e.g. `example-app`)

docker_image
: Docker image name and tag to deploy. (e.g.: `mozorg/bedrock:latest`)

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
