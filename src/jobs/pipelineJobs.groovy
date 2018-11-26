import org.centos.contra.jobdsl.PipelineJob


def job = new PipelineJob(this, 'metricsPipelineJob')
job.addGit(branch: 'master', repoUrl: 'https://gitlab.sat.engineering.redhat.com/cpaas/pipeline.git')
job.gitlabTrigger(build_on_merge_request_events: true, build_on_push_events: true)
job.logRotate()
