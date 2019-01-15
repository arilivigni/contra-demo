import org.centos.contra.jobdsl.PipelineJob

def jobName = 'metricsPipelineJob'
def job = new PipelineJob(this, jobName)
job.addGit(repo_branch: 'master', repo_url: 'https://github.com/joejstuart/contra-demo.git')
job.logRotate()

queue(jobName)

