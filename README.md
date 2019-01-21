# devconfcz-demo

## Overview

## Setup your system
Deployment of the deconfcz-demo is done vi an ansible script, which you can 
run locally to install devconfcz-demo on a remote OpenShift cluster or locally (Minishift).
Before deploying the devconfcz-demo environment from your local system,
you will need to install ansible and git.

Install ansible and git by running the following command:

```
sudo dnf install ansible git tar unzip wget
```
Prepare the devoncfcz-demo and contra-env-setup files:
* For pure testing purposes, use 'master' for \<branch\>
* If already forked, use any testing branch

The following commands will copy the repos to your local machine and pull
the proper versions of the code. Any changes you make when deploying devconfcz-demo
can be made locally to these files; your changes do not need to be pushed
back to the repos.

```
git clone https://github.com/arilivigni/devconfcz-demo
pushd service; git checkout origin/<branch -b <branch>; popd

git clone https://github.com/CentOS-PaaS-SIG/contra-env-setup
```

### Run Deployment

The following command will deploy devconfcz-demo using your configuration:
```
ansible-playbook -vv -i "localhost," contra-env-setup/playbooks/setup.yml \
    -e user=$USER \
    -e @devconfcz-demo/config/contra-env-setup.yml -K -k
```

### Starting and Stopping Minishift

The deployment should start up the instance when the deployment is complete.
If you are running Minishift, you'll need to shut down and start up the instance
when you shutdown and start up your system.

To shut down a Minishift instance:
```
~/.contra-env-setup/minishift/minishift stop
```

To start up a Minishift instance:
```
~/.contra-env-setup/minishift/minishift start --iso-url file:///home/$USER/.contra-env-setup/minishift/minishift.iso
```

#### Troubleshooting Minishift Start ####

If starting Minishift fails with the following error:
> Checking if Libvirt default network is present ... FAIL

Run the following command:
```
sudo virsh net-start default
```

It will likely produce the following error:
> error: Failed to start network default
> error: error creating bridge interface virbr0: File exists

If this is the case, the following commands will clean it up and allow you to
start Minishift properly:
```
sudo ifconfig virbr0 down
sudo brctl delbr virbr0
sudo virsh net-start default
```

Now you should be able to start Minishift successfully.

### Minishift Console

Open a Minishift console via the command line:
```
~/.contra-env-setup/minishift/minishift console
```

You can also open a Minishift console in an existing web browser by cut-n-pasting
the URL displayed in the command line window into a web browser.
