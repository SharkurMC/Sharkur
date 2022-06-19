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
  environment {
    WEBHOOK_URL = 'https://canary.discord.com/api/webhooks/988180646117658664/5CniPrZarLrXwawp23Q4R4cKK-fP-meUvQD6f5pIJmM32yUJPQ5KaLIlbTRBOhIqscYA'
  }
  post {
    success {
      archiveArtifacts(artifacts: 'build/libs/**/*.jar', fingerprint: true)
    }

  }
}