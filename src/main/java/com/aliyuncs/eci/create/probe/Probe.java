package com.aliyuncs.eci.create.probe;


import com.aliyuncs.common.EciBaseHelper;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupRequest;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupResponse;

import java.util.Arrays;

public class Probe {
    public static CreateContainerGroupResponse createContainerGroup() {
        CreateContainerGroupRequest createContainerGroupRequest
                = EciBaseHelper.buildCreateContainerGroupRequest();

        createContainerGroupRequest.setContainerGroupName("eci-test");
        CreateContainerGroupRequest.Container container = new CreateContainerGroupRequest.Container();
        container.setName("nginx-liu");
        container.setImage("nginx");
        container.setMemory(4F);
        container.setCpu(2F);
        container.setImagePullPolicy("IfNotPresent");

        //probe
        CreateContainerGroupRequest.Container.ContainerProbe readinessProbe = new CreateContainerGroupRequest.Container.ContainerProbe();
        readinessProbe.setTcpSocketPort(80);
        readinessProbe.setInitialDelaySeconds(5);
        readinessProbe.setPeriodSeconds(3);
        readinessProbe.setFailureThreshold(3);
        readinessProbe.setSuccessThreshold(3);
        readinessProbe.setTimeoutSeconds(10);

        CreateContainerGroupRequest.Container.ContainerProbe livenessProbe = new CreateContainerGroupRequest.Container.ContainerProbe();
        livenessProbe.setTcpSocketPort(80);
        livenessProbe.setInitialDelaySeconds(5);
        livenessProbe.setPeriodSeconds(3);
        livenessProbe.setFailureThreshold(3);
        livenessProbe.setSuccessThreshold(1);
        livenessProbe.setTimeoutSeconds(10);

        container.setReadinessProbe(readinessProbe);
        container.setLivenessProbe(livenessProbe);

        createContainerGroupRequest.setContainers(Arrays.asList(container));

        return EciBaseHelper.getAcsResponse(createContainerGroupRequest);
    }
}
