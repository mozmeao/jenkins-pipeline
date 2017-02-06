def call(String image_id) {
  sh "docker build -t ${image_id} ."
}

def call(String image_id, Map args) {
  cmd = "docker build -t ${image_id}"
  if (args.pull) {
    cmd += " --pull=${args.pull}"
  }
  if (args.no_cache) {
    cmd += " --no-cache=${args.no_cache}"
  }
  if (args.dockerfile) {
    cmd += " -f ${args.dockerfile}"
  }
  cmd += " ."

  sh cmd
}
