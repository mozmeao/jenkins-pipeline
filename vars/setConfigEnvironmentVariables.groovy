def call(LinkedHashMap config=null) {
  if (config == null) {
    try {
      config = readYaml file: 'jenkins.yml'
    }
    catch (e) {
      println "Environment variables skipped. Configuration not found."
      return
    }
  }
  for (var in config.environment) {
    env[var.key] = var.value
  }
}
