// Using Groovy's regex matching is a pain due to Jenkins Script approval flow.
// Grep works great.
def call(String string, String expression) {
  cmd = "(echo -n '${string}' | grep -q -P  '${expression}')"
  def output = sh([returnStatus: true, script: cmd])
  return output == 0
}
