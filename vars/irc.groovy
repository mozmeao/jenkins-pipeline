def call(message) {
  def config = readYaml file: 'jenkins.yaml'
  sh """
    (
      echo "NICK ${config.irc.nickname}"
      echo "USER ${config.irc.nickname} 8 * : ${config.irc.nickname}"
      sleep 5
      echo "JOIN ${config.irc.channel}"
      echo "NOTICE ${config.irc.channel} : ${message}"
      echo "QUIT"
    ) | openssl s_client -connect "${config.irc.server}" > /dev/null 2>&1
    """
}
