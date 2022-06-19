pipeline {
  agent any
  stages {
    stage('Pre-Build') {
      steps {
        sh 'chmod +x gradlew'
      }
    }

    stage('Setup') {
      steps {
        sh './gradlew applyPatches'
      }
    }

    stage('Build') {
      steps {
        sh './gradlew createReobfPaperclipJar'
      }
    }

    stage('Test') {
      steps {
        sh './gradlew test'
      }
    }

  }
  tools {
    jdk 'OpenJDK-17'
  }
  post {
    success {
      archiveArtifacts(artifacts: 'build/libs/**/sharkur-paperclip-*.jar', fingerprint: true)
    }

  }
}
