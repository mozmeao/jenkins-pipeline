# Conditionals

## on
Define a set of criteria to match for the Closure to execute. See also
convenience functions [`onBranch`](#onbranch) and [`onTag`](#ontag).
#### Usage
```groovy
on(args) {
 // code
}
```

args
: Map Array of criteria to match. If all criteria match or empyt list will
execute code.

args.branch
: Branch to match

args.tag
: Tag to match


## onBranch
Execute Closure block only when current branch matches.

#### Usage
```groovy
onBranch(branch) {
 // code
}
```

branch
: Branch to match. Perl like regular expressions are supported. It's better to
use `//` instead of `""` to define Groovy Strings when using regular expressions
to avoid character escaping.

#### Example
```groovy
onBranch("master") {
  deisPull("example-app", "mozorg/example-app:$GIT_COMMIT_SHORT")
}
```

Will only run `deisPull` when Pipeline is running on the `master` branch.



## onTag
Execute Closure block only when commit has a matching git tag.

#### Usage
```groovy
onTag(tag) {
 // code
}
```

tag
: Tag to match. Perl like regular expressions are supported. It's better to use
`//` instead of `""` to define Groovy Strings when using regular expressions to
avoid character escaping.

#### Example
```groovy
onTag(/201\d\d{2}\d{2}.\d{1,2}/) {
  deisPull("example-app", "mozorg/example-app:$GIT_COMMIT_SHORT")
}
```

Will only run `deisPull` when commit has a git tag matching the regular
expression. This will match tags like `20170101.1`.
