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
  if (args.cache_from) {
    cmd += " --cache-from ${args.cache_from}"
  }
  if (args.extra_docker_args) {
    cmd += " ${args.docker_args}"
  }

  cmd += " ."

  sh cmd
}
