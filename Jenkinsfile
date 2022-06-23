pipeline {
    agent any
    tools {
        jdk 'JDK_1.8_221'
        maven 'MavenInstallation'
    }
    environment {
        String branchName = "CherifBachir"
        String repoUrl = "https://github.com/bachir717/MyRestoSpring.git"
    }
    stages {
        stage('Checkout') {
            steps {
                script {
                    echo 'Cloning the "'+branchName+'" branch from "'+repoUrl+'".'
                    echo 'Start cloning the github repository...'
                    git branch: branchName, url: repoUrl
                    echo 'repository clone on branch master done.'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    echo 'Execute all test...'
                    withMaven {
                        bat "mvn -f MyRestoSpring/pom.xml clean test"
                    }
                    echo 'End of the execution'
                }
            }
        }
        stage('generate javadoc') {
            steps {
                script {
                    echo 'Generation of the javadoc...'
                    withMaven {
                        bat "mvn -f MyRestoSpring/pom.xml javadoc:javadoc"
                    }
                    echo 'The javadoc have been generated !'
                }
            }

        }
        stage('confirmation email') {
            steps {
                emailext to: "bachir_c@etna-alternance..net", subject: "[Jenkins] MyRestoSrping", body: "MyRestoSrping jenkins pipeline build finished with success"
                echo 'An email have been send ! Consult your email adress !'
            }
        }
    }
}
