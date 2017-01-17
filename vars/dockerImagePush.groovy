def call(String tag, String registry, String credentials) {
  try {
    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: credentials,
                      usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {

      sh "docker login --username ${env.USERNAME} --password ${env.PASSWORD} ${registry}"
    }

    sh "docker push ${tag}"
  }
  finally {
    sh "docker logout ${registry}"
  }
}
