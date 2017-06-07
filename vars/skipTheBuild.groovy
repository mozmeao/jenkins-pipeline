/**
 * Return True if the build should be skipped because
 * the string "[ci skip]" appears in the HEAD commit message.
 */
def call() {
    def output = sh([script: 'git --no-pager show -s --format=%B', returnStdout: true])
    return output.contains('[ci skip]') || output.contains('[skip ci]')
}
