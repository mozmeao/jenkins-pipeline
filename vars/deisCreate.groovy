def call(string app) {
  sh "deis apps:create ${app} --no-remote"
}
