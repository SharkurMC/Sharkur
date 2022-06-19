pipeline {
  agent any
  tools {
    jdk 'OpenJDK-17'
  }
  stages {
      stage('Pre-Build') {
          steps {
              sh 'chmod +x gradlew'
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
  post {
      success {
          archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
      }
  }
}
