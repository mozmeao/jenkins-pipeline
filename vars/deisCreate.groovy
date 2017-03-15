def call(String app, String deis_executable=null) {
  if (!deis_executable) {
    deis_executable = "deis"
  }
  sh "${deis_executable} apps:create ${app} --no-remote"
}
