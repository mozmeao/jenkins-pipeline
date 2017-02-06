def call(LinkedHashMap config=null) {
  if (config == null) {
    try {
      config = readYaml file: 'jenkins.yml'
    }
    catch (e) {
      println "Notifications skipped. Configuration not found."
      return
    }
  }
  def statusChanged = getPreviousBuildStatus() != getCurrentBuildStatus()
  def status = getCurrentBuildStatus()

  if (config && config.notifications) {
    if (config.notifications.irc) {
      def msg

      if (!config.notifications.irc.on_success) {
        config.notifications.irc.on_success = "change"
      }
      if (!config.notifications.irc.on_failure) {
        config.notifications.irc.on_failure = "change"
      }

      if (config.notifications.irc.on_success == "always" &&
          status == "SUCCESS") {
        msg = "Project ${JOB_NAME} build #${BUILD_NUMBER}: ${status}: ${BUILD_URL}"
      }
      if (config.notifications.irc.on_success == "change" &&
          status == "SUCCESS" && statusChanged) {
        msg = "Project ${JOB_NAME} build #${BUILD_NUMBER}: ${status}: ${BUILD_URL}"
      }

      if (config.notifications.irc.on_failure == "always" &&
          status == "FAILURE") {
        msg = "Project ${JOB_NAME} build #${BUILD_NUMBER}: ${status}: ${BUILD_URL}"
      }
      if (config.notifications.irc.on_failure == "change" &&
          status == "FAILURE" && statusChanged) {
        msg = "Project ${JOB_NAME} build #${BUILD_NUMBER}: ${status}: ${BUILD_URL}"
      }

      if (msg) {
        irc(config.notifications.irc.server,
            config.notifications.irc.nickname,
            config.notifications.irc.channel,
            msg)
      }
    }
  }
}
