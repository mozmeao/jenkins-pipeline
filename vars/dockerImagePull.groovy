def call(String image_id) {
  sh "docker pull ${image_id}"
}
