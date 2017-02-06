def call() {
  if (BRANCH_NAME) {
    // We're running in Multibranch Pipeline, Jenkins sets BRANCH_NAME
    return BRANCH_NAME
  }
  else {
    def cmd = "git symbolic-ref HEAD | sed 's!refs/heads/!!'"
    def branch = sh([returnStdout: true, script: cmd]).trim()
    return branch
  }
}
