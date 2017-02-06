def deisLogin(server, username, password) {
  sh "deis auth:login --username '${username}' --password '${password}' '${server}'"
}

def call(String server, String credentials, Closure body) {
  try {
    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: credentials,
                      usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {

      deisLogin(server, env.USERNAME, env.PASSWORD)
    }
    body()
  }
  catch(e) {
    println e
    throw e
  }
  finally {
    deisLogout()
  }
}

def call(String server, String credentials) {
  withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: credentials,
                    usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
    deisLogin(server, env.USERNAME, env.PASSWORD)
  }
}
