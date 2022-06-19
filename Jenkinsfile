pipeline {
  agent {
      kubernetes {
          containerTemplate {
              name 'sharkur-build'
              image 'openjdk:17-jdk-slim'
              ttyEnabled true
          }
      }
  }
  stages {
    stage('Pre Build') {
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
    post {
        success {
            archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
        }
    }
  }
}
