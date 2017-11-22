def call(String image_id, String new_image_id) {
  sh "docker ${image_id} ${new_image_id}"
}
