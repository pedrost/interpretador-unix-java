<h1 align="center">Welcome to Java Unix Proto-Shell 👋</h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-0.1-blue.svg?cacheSeconds=2592000" />
</p>

> This is a unix proto-shell java interpreter, build to @ufms Operational Systems discipline, made with 
:blue_heart: by @pedrost and @pedromihael.
## Documentation

This shell should be able to:
- [x]  Allow prompt redefinition by the variable ```$MYPS1```;
- [x]  Pipe commands using ```&&``` or ```|```;
- [x]  Change directories using ```cd```;
- [x]  List directory content using ```ls```;
- [x]  List the commands history using ```history```;
- [x]  Redirect outputs into files using ```>```;
- [x]  Redirect errors with ```2>```;
- [x]  Inject entries with ```<```;
- [x]  Echo contents using ```echo``` (including ```$PATH```);
- [x]  Clear the shell using ```clear```;
- [x]  Throws a message when the command is not found;
- [x]  Inject entries and redirect output simultaneosly;
- [x]  Prevent ```CTRL + C``` exists the shell;
- [x]  Exit the shell using ```exit```;

## Usage

```aidl
$ git clone https://github.com/pedrost/interpretador-unix-java.git
$ cd interpretador-unix-java
$ ./build.sh
```

## Run with Docker
If you don't have java (jdk and jre) installed, [here](https://hub.docker.com/_/openjdk) are the open jdk container for usage.

## Show your support

Give a ⭐️ if this project helped you!

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_