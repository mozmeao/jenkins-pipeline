# Services

## newRelicDeployment

Send a Deployment to NewRelic.

#### Usage
```groovy
newRelicDeployment(app, revision, user, credentials)
```

app
: Newrelic app name to send deployment to.

revision
: App revision to log

user
: User who deploys

credentials
: is the ID (String) of a Secret text Jenkins Password Credentials Entry with an API Key for NewRelic

#### Example

```
newRelicDeployment("example-app", "fe93bd", "jenkins", "newrelic")
```
