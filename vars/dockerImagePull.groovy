def call(String tag) {
  sh "docker pull ${tag}"
}
