// https://support.cloudbees.com/hc/en-us/articles/226419147-How-can-I-check-previous-build-status-in-a-Pipeline-Script-
// WARNING: Groovy sandbox has to be disabled for this to work because it is
// accessing the filesystem. RawBuild will also have to be whitelisted by the
// script security plugin once the Groovy sandbox is disabled.
def call() {
  if(hudson.model.Result.SUCCESS.equals(currentBuild.rawBuild.getPreviousBuild()?.getResult())) {
    return 'SUCCESS'
  }
  else {
    return 'FAILURE'
  }
}
