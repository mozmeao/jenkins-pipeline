def docker_command(String tag, Map args) {
  def output = [];
  def cmd = "docker run"

  if (args.docker_args) {
    cmd += " ${args.docker_args}"
  }

  cmd += " ${tag}"

  if (args.cmd) {
    if (args.bash_wrap == false) {
      cmd += " ${args.cmd}"
    }
    else {
      cmd += " sh -c '${args.cmd}'"
    }
  }
  if (!args.copies) {
    args.copies = 1
  }
  for (int i=1; i <= args.copies; i++) {
    output.add(sh([returnStdout: true, script: cmd]))
  }

  return output
}

def call(String tag, String cmd) {
  return docker_command(tag, ["cmd": cmd, "bash_wrap": true])
}

def call(String tag, Map args) {
  return docker_command(tag, args)
}

def call(String tag, Map args, Closure body) {
  if (!args.docker_args) {
    args.docker_args = ""
  }
  args.docker_args = "-d " + args.docker_args
  try {
    containers = docker_command(tag, args)
    body()
  }
  finally {
    for (container in containers) {
      dockerStop(container)
    }
  }
}
