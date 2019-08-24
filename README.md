# jenkins-course

Execute Profile:
SPRING_PROFILES_ACTIVE=dev gradle clean bootRun

This application was built for testing in jenkins course. 

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
