def deisLogin(String server, String username, String password, String deis_executable=null) {
  if (!deis_executable) {
    deis_executable = "deis"
  }

  sh "${deis_executable} auth:login --username '${username}' --password '${password}' '${server}'"
}

def call(String server, String credentials, String deis_executable=null, Closure body) {
  try {
    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: credentials,
                      usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {

      deisLogin(server, env.USERNAME, env.PASSWORD, deis_executable)
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

def call(String server, String credentials, String deis_executable=null) {
  withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: credentials,
                    usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
    deisLogin(server, env.USERNAME, env.PASSWORD, deis_executable)
  }
}
