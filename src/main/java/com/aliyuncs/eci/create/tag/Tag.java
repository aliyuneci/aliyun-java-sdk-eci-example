package com.aliyuncs.eci.create.tag;


import com.aliyuncs.common.EciBaseHelper;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupRequest;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupResponse;

import java.util.Arrays;

public class Tag {
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

        //Tag
        CreateContainerGroupRequest.Tag tag = new CreateContainerGroupRequest.Tag();
        tag.setKey("java-sdk-eci");
        tag.setValue("v1");
        createContainerGroupRequest.setTags(Arrays.asList(tag));

        return EciBaseHelper.doAction(createContainerGroupRequest);
    }
}
