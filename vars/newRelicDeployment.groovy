def call(String app, String revision, String user, String credentials) {
  withCredentials([string(credentialsId: credentials, variable: 'api_key')]) {
    def cmd = ("curl -s -H \"x-api-key:${api_key}\" " +
               "-d \"deployment[app_name]=${app}\" " +
               "-d \"deployment[revision]=${revision}\" " +
               "-d \"deployment[user]=${user}\" " +
               "https://api.newrelic.com/deployments.xml")

    sh([script: cmd])
  }
}
