pipeline {
  agent any
  environment {
    DISCORD_WEBHOOK = credentials('jenkins-discord-webhook')
    REPOSILITE_USER = credentials('REPOSILITE_USER')
    REPOSILITE_PASSWORD = credentials('REPOSILITE_PASSWORD')
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

    stage('Reposilite') {
      steps {
        sh "./gradlew publish -PsharkurUsername=$REPOSILITE_USER -PsharkurPassword=$REPOSILITE_PASSWORD"
      }
    }

  }
  tools {
    jdk 'OpenJDK-18'
  }
  post {
    success {
      archiveArtifacts(artifacts: 'build/libs/**/sharkur-paperclip-*.jar', fingerprint: true)
    }
    always {
      discordSend(description: "**Build:** ${env.BUILD_ID}\n**Status:** ${currentBuild.currentResult}", link: env.BUILD_URL, result: currentBuild.currentResult, title: "Sharkur #${env.BUILD_ID}", webhookURL: "$DISCORD_WEBHOOK")
    }
  }
}
