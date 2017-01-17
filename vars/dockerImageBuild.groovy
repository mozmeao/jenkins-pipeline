def call(String tag) {
  sh "docker build -t ${tag} ."
}

def call(String tag, Map args) {
  cmd = "docker build -t ${tag}"
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
