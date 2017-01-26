// Using Groovy's regex matching is a pain due to Jenkins Script approval flow.
// Grep works great.
def call(String string, String expression) {
  cmd = "(echo -n '${string}' | grep -q -P  '${expression}') ; echo \$?"
  def output = sh([returnStdout: true, script: cmd]).trim()
  return output == "0"
}
