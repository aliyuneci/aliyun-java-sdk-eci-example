package com.aliyuncs.eci.create.hostAliase;

import com.aliyuncs.common.EciBaseHelper;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupRequest;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupResponse;

import java.util.Arrays;

public class HostAliase {
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
        createContainerGroupRequest.setContainers(Arrays.asList(container));

        CreateContainerGroupRequest.HostAliase hostAliase = new CreateContainerGroupRequest.HostAliase();
        hostAliase.setIp("196.128.0.10");
        hostAliase.setHostnames(Arrays.asList("test1.com", "test2.com"));

        createContainerGroupRequest.setHostAliases(Arrays.asList(hostAliase));

        return EciBaseHelper.doAction(createContainerGroupRequest);
    }
}
