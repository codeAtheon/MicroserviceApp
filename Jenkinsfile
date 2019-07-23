pipeline {
  agent {
    node {
      label 'master'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh '''chmod +x gradlew
./gradlew clean build -x test -x check'''
      }
    }
    stage('Code Test') {
      parallel {
        stage('Code Test') {
          steps {
            sh '''chmod +x gradlew
./gradlew test

./gradlew check
'''
          }
        }
        stage('Coverage Report') {
          steps {
            sh '''chmod +x gradlew
./gradlew jacocoTestReport
'''
            jacoco()
            checkstyle(pattern: '**/*.xml')
            pmd(pattern: '**/*.xml')
            findbugs(pattern: '**/*.xml')
          }
        }
        stage('Code quality') {
          steps {
            echo 'Sonarqube code result : ${env.currentBuild.number}                                 Build number:${env.BUILD_NUMBER}'
          }
        }
      }
    }
    stage('Deploy') {
      steps {
        sh '''docker build --tag mkarthikdevops/microserviceimage:$BUILD_NUMBER .

 '''
      }
    }
    stage('Docker Push') {
      agent any
      steps {
        withCredentials(bindings: [usernamePassword(credentialsId: 'docker-hub-creds', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
          sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
          sh 'docker push mkarthikdevops/microserviceimage:$BUILD_NUMBER'
        }

      }
    }
    stage('Deploy test environment') {
      steps {
        withCredentials(bindings: [usernamePassword(credentialsId: 'docker-hub-creds', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
          sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
          sh 'docker pull mkarthikdevops/microserviceimage:$BUILD_NUMBER'
          sh 'docker run -d --name microservicepoc-$BUILD_NUMBER -p 8099:80 mkarthikdevops/microserviceimage:$BUILD_NUMBER'
          sh 'docker container inspect microservicepoc-$BUILD_NUMBER'
          sh 'ibmcloud'
        }

      }
    }
  }
  environment {
    gradle = '5.5-rc3'
  }
  post {
    always {
      junit '**/*.xml'

    }

  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '7'))
  }
  triggers {
    pollSCM('* * * * *')
  }
}