def call(String tag, String new_tag) {
  sh "docker tag ${tag} ${new_tag}"
}
