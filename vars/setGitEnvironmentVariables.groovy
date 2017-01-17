def call() {
  env.GIT_COMMIT = sh([returnStdout: true, script: 'git rev-parse HEAD']).trim()
  env.GIT_COMMIT_SHORT = env.GIT_COMMIT.take(6)
}
