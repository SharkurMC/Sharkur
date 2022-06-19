pipeline {
  agent any
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
