def call(String tag) {
  cmd = "bash -c '(docker inspect ${tag} > /dev/null 2>&1 && echo found) || echo notfound'"
  found = sh([returnStdout: true, script: cmd]).trim()
  if (found == 'found') {
      return true
  }
  return false
}
