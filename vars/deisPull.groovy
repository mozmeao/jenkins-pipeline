def call(String app, String image, String procfile=null) {
  def cmd = "deis builds:create '${image}' -a '${app}'"
  if (procfile) {
    cmd += " -p '${procfile}'"
  }
  sh cmd
}
