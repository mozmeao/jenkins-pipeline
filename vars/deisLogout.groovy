def call(String deis_executable=null) {
  if (!deis_executable) {
    deis_executable = "deis"
  }

  sh "${deis_executable} auth:logout"
}
