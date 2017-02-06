// todo, decide what to return if undefined
def call() {
  if (currentBuild.result) {
    return currentBuild.result
  }
  else {
    return null
  }
}
