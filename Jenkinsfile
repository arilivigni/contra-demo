def containers = ['fedora': [privileged: false, tag: 'latest']]
def podName = "mytestpod"
deployOpenShiftTemplate(docker_repo_url: '172.30.1.1:5000', openshift_namespace: 'devconfcz-demo',
                        jenkins_slave_image: 'jenkins-contra-slave:latest', containersWithProps: containers,
                        podName: podName, openshift_service_account: 'jenkins') {
    ciPipeline(sendMetrics: true, buildPrefix: 'demo') {

        stage('stage1') {
            def cmd = """ 
            mkdir logs
            /bin/metrics.sh > logs/metrics.log &
            echo testing...
            stress --cpu 1 --timeout 30
            """ 
            executeInContainer(containerName: 'fedora', containerScript: cmd)

            def data = readFile file: 'stage1/logs/metrics.log'

            def measurement = "${env.JOB_NAME}_system_stats"
            def customDataMap = [:] 
            def customDataMapTags = [:] 
            data.split('\n').each { point ->
                point = point.split(',')
                customDataMap[measurement] = [mem_usage: point[0], cpu_user: point[1], cpu_system: point[2]]
                customDataMapTags[measurement] = [stage: 'stage1']
                writeToInflux(customDataMap: customDataMap,
                              customDataMapTags: customDataMapTags,
                              customPrefix: 'demo')
                sleep time: 10
            }   
        }   
    }
}

