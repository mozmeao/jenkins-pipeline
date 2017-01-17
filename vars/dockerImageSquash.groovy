def call(String tag, String squashed_tag) {
    sh "docker-squash -t ${squashed_tag} ${tag}"
}

def call(String tag) {
    sh "docker-squash ${tag}"
}
