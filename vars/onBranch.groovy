def call(String branchName, Closure body) {
  on(["branch": branchName], body)
}
