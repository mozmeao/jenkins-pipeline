def call(String image_id, String squashed_image_id) {
    sh "docker-squash -t ${squashed_image_id} ${image_id}"
}

def call(String image_id) {
    sh "docker-squash ${image_id}"
}
