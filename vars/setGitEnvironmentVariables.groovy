def call() {
  env.GIT_COMMIT = sh([returnStdout: true, script: 'git rev-parse HEAD']).trim()
  env.GIT_COMMIT_SHORT = env.GIT_COMMIT.take(6)
  env.GIT_BRANCH = getGitBranch()
  try {
    env.GIT_TAG = sh([returnStdout: true, script: 'git describe --tags --exact-match $GIT_COMMIT']).trim()
  } catch(e) {
    echo 'git commit not tagged'
  }
}
