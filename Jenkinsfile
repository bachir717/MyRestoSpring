   pipeline {
    agent any
    tools{
        maven 'maven'
    }
    
     environment {
        String branchName = "CherifBachir"
        String repoUrl = "https://github.com/bachir717/MyRestoSpring.git"
        registry = "youness/shop"
        registryCredential = 'dockerhub'
        app = ''
    }
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/CherifBachir']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/bachir717/MyRestoSpring.git']]])
                bat('dir C:\\formationmaven\\outils\\apache-maven-3.6.3\\bin')
            }
        }
        
        stage('Compile') {
            steps {
                script {
                    echo 'compiling'
                    bat("C:\\formationmaven\\outils\\apache-maven-3.6.3\\bin\\mvn compile")
                    echo 'compile success'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    echo 'starting spring boot application unit test .....'
                    bat("C:\\formationmaven\\outils\\apache-maven-3.6.3\\bin\\mvn test")
                    echo 'spring boot application unit tests passed'
                }
            }
        }
        stage('Javadoc') {
            steps {
                script {
                    echo 'generating doc'
                    bat("C:\\formationmaven\\outils\\apache-maven-3.6.3\\bin\\mvn javadoc:javadoc")
                    echo 'doc generated'
                }
            }
        }
     }
     post {
    failure  
    {
        mail to: 'bachir_c@etna-alternance.net',               
                    subject: "Job $JOB_NAME failed" ,
                    body: """Build $BUILD_NUMBER failed.    
Go to $BUILD_URL for more info."""
    }
    }
}
   
   
 
