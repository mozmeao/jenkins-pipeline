def call(String image_id) {
  cmd = "bash -c '(docker inspect ${image_id} > /dev/null 2>&1 && echo found) || echo notfound'"
  found = sh([returnStdout: true, script: cmd]).trim()
  if (found == 'found') {
      return true
  }
  return false
}
