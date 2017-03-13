def call(String app, String image, String procfile=null, String deis_executable=null) {
  if (!deis_executable) {
    deis_executable = "deis"
  }
  def cmd = "${deis_executable} builds:create '${image}' -a '${app}'"
  if (procfile) {
    cmd += " -p '${procfile}'"
  }
  sh cmd
}
