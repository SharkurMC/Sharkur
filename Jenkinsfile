pipeline {
  agent any
  tools {
      jdk 'jdk-17'
  }

  environment {
      JAVA17_HOME = "${tool 'jdk-17'}"
      JAVA_HOME = "${tool 'jdk-17'}"
  }
  stages {
      stage('Pre-Build') {
          steps {
              sh 'chmod +x gradlew'
          }
      }
      stage('Build') {
          steps {
              sh './gradlew build'
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
