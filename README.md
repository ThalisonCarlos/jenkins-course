# jenkins-course

Jenkins Jobs Execution

This application was built for testing in jenkins course. 

    • Pipeline Build Validation:

        Source Code Management:
       
            SSHGIT

        Build Triggers:

            Poll SCM – Schedule: * * * * *
      
        Build:

            ./gradlew clean build
             ./gradlew test

         Docker Build and Publish:

             DeployImageGitHub

         Post-build Actions

             BuildQA

        SlackNotification

• Pipeline Build QA/DEV:

Execute Profile:
SPRING_PROFILES_ACTIVE=dev gradle clean bootRun

Pipeline Declarative Script dev:

pipeline{
    
    environment{
        profile = "${profile}"
    }
    
    agent any
    
    stages{
        stage('Remove Old Container'){
            steps{
               sh 'docker rm -f jenkins-course-'+profile
            }
        }
        stage('Start new Container'){
            steps{
                
             
             sh 'docker run -d -p 8081:8081 -e "SPRING_PROFILES_ACTIVE="'+profile+' --name jenkins-course-'+profile+' thalisoncarlos/jenkins-course --stacktrace' 
                    
                    
            }
        }
        stage('Notify Success'){
            steps{
                slackSend(color:'good', message: 'Build Success.', tokenCredentialId: 'slack-notification')
            }
        }
        stage('Deploy PROD'){
            steps{
                script{
                    slackSend(color:'good', message: 'Aguardando confirmação de deploy em Produção', tokenCredentialId: 'slack-notification')
                    timeout(time:2, unit: 'MINUTES'){
                        input(id:"Deploy Gate", message: "Realizar deploy prod?", ok: "Deploy")
                    }
                }
            }
        }
        stage(deploy){
            steps{
                script{
                    try{
                        build job: 'jenkins-course-prod' 
                    }catch(Exception e){
                        slackSender(color:'error', message:'Falha na chamada da execução de prod', tockerCredencialId: 'slack-notification')
                        sh 'echo $e'
                        currentBuild.result = 'ABORTED'
                        error('Erro')
                    }
                }
            }
        }
    }
}


• Pipeline Build PROD:

pipeline{
    
    agent any
    
    stages{
        stage('Remove Old Container'){
            steps{
                script{
                    try{
                        sh 'docker rm -f jenkins-course-prod'
                    }catch(Exception e){
                        sh 'echo '+ e
                    }
                }
                
            }
        }
        stage('Start new Container'){
            steps{
                
             
             sh 'docker run -d -p 80:8081 -e "SPRING_PROFILES_ACTIVE=prod" --name jenkins-course-prod thalisoncarlos/jenkins-course --stacktrace' 
                    
                    
            }
        }
    }
}
