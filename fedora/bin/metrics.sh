#!/bin/bash

while true;
do
    mem=`cat /sys/fs/cgroup/memory/memory.usage_in_bytes`
    cpu_user=`cat /sys/fs/cgroup/cpu/cpuacct.stat |grep user |awk '{print $2}'`
    cpu_system=`cat /sys/fs/cgroup/cpu/cpuacct.stat |grep system |awk '{print $2}'`
    blkio=`cat /sys/fs/cgroup/blkio/blkio.io_serviced`
    echo "${mem}, ${cpu_user}, ${cpu_system}, ${blkio}"
    sleep 5
done

