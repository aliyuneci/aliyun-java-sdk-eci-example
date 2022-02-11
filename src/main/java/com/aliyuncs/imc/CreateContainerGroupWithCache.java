package com.aliyuncs.imc;


import com.aliyuncs.common.EciBaseHelper;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupRequest;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupResponse;
import com.aliyuncs.common.EciTestConstant;

import java.util.Arrays;

public class CreateContainerGroupWithCache {
    public static CreateContainerGroupResponse createContainerGroup() {
        CreateContainerGroupRequest createContainerGroupRequest = new CreateContainerGroupRequest();
        createContainerGroupRequest.setRegionId(EciTestConstant.REGION_ID);
        createContainerGroupRequest.setSecurityGroupId(EciTestConstant.SECURITY_GROUP_ID);
        createContainerGroupRequest.setVSwitchId(EciTestConstant.VSWITCH_ID);
        createContainerGroupRequest.setContainerGroupName("eci-test");

        CreateContainerGroupRequest.Container container = new CreateContainerGroupRequest.Container();
        container.setName("nginx-liu");
        container.setImage("nginx");
        container.setMemory(4F);
        container.setCpu(2F);
        container.setImagePullPolicy("IfNotPresent");
        createContainerGroupRequest.setContainers(Arrays.asList(container));

        createContainerGroupRequest.setAutoMatchImageCache(true);

        return EciBaseHelper.getAcsResponse(createContainerGroupRequest);
    }

}
