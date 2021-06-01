def push(String image_id, String registry, String credentials) {
  try {
    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: credentials,
                      usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {

      sh "docker login --username '${env.USERNAME}' --password '${env.PASSWORD}' '${registry}'"
    }

    sh "docker push ${image_id}"
  }
  catch(e) {
    println e
    throw e
  }
  finally {
    sh "docker logout '${registry}'"
  }
}


def call(String image_id, String registry, String credentials) {
  push(image_id, registry, credentials)
}


def call(String image_id, String credentials) {
  push(image_id, "", credentials)
}
