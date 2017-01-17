def call(String container_id) {
  sh "docker stop ${container_id}"
}
