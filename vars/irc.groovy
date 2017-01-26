def call(String server, String nickname, String channel, String message) {
  sh """
    (
      echo "NICK ${nickname}"
      echo "USER ${nickname} 8 * : ${nickname}"
      sleep 5
      echo "JOIN ${channel}"
      echo "NOTICE ${channel} : ${message}"
      echo "QUIT"
    ) | openssl s_client -connect "${server}" > /dev/null 2>&1
    """
}
