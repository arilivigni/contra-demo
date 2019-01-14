import org.centos.contra.jobdsl.PipelineJob


def job = new PipelineJob(this, 'metricsPipelineJob')
job.addGit(repo_branch: 'master', repo_url: 'https://github.com/joejstuart/contra-demo.git')
job.logRotate()

