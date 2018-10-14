import org.centos.contra.jobdsl.PipelineJob


def job = new PipelineJob(this, 'metricsPipelineJob')
job.addGit([branch: 'master', repoUrl: 'https://github.com/joejstuart/contra-demo.git'])
job.logRotate()
