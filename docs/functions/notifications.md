# Notifications

## irc
Send an IRC message.

Works with TLS enabled servers only.

#### Usage
```groovy
irc(server, nickname, channel, message)
```

server
: Server to connect to. Supports only TLS enabled servers.

nickname
: Nickname to use.

channel
: Channel to connect to.

message
: Message to send.

#### Example

```groovy
irc("irc.mozilla.org:6697", "jenkins", "#example", "Hey ya!")
```

## sendNotifications
Send notifications about the build status. See
also [`conduit`](pipeline.md#conduit).

Currently supports IRC.

#### Example
```groovy
sendNotifications()
```

##### jenkins.yml
```yaml
notifications:
  irc:
    server: irc.mozilla.org:6697
    channel: "#careersweb"
    nickname: mozmeao-jenkins-careers
    on_success: always
    on_failure: change
```
