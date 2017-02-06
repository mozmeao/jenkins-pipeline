def call(String tagName, Closure body) {
  on(["tag": tagName], body)
}
