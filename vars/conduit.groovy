def call(Closure body) {
  try {
    timestamps {
      body()
    }
    currentBuild.result = 'SUCCESS'
  }
  catch (e) {
    currentBuild.result = 'FAILURE'
    throw (e)
  }
  finally {
    node {
      sendNotifications()
    }
  }
}
