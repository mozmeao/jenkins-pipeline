// Requires Jenkins Script approval for split()
def call(String commit="") {
  if (!commit) {
    commit = "HEAD"
  }
  def cmd = "git tag --points-at ${commit}"
  def tags = sh([returnStdout: true, script: cmd]).trim().split()
  return tags
}
