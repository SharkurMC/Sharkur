pipeline {
  agent any
  environment {
    DISCORD_WEBHOOK = credentials('jenkins-discord-webhook')
  }
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
    always {
      discordSend description: "Jenkins Pipeline Build", footer: "Footer Text", link: env.BUILD_URL, result: currentBuild.currentResult, title: "Sharkur", webhookURL: "$DISCORD_WEBHOOK"
    }
  }
}
