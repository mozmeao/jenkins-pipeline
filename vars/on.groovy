def call(Map args, Closure body) {
  def matches = 0
  def branchName = getGitBranch()
  def tagNames = getGitTags()

  if (args.branch && branchName && grepMatch(branchName, args.branch)) {
    matches++
  }

  if (args.tag && tagNames) {
    for(int i = 0; i < tagNames.length; i++) {
      if (grepMatch(tagNames[i], args.tag)) {
        matches++
        break
      }
    }
  }

  if (args.size() == matches) {
    body()
  }
}
