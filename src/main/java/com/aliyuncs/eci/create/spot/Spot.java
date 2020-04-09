package com.aliyuncs.eci.create.spot;


import com.aliyuncs.common.EciBaseHelper;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupRequest;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupResponse;

import java.util.Arrays;

public class Spot {
    public static CreateContainerGroupResponse createContainerGroup() {
        CreateContainerGroupRequest createContainerGroupRequest = EciBaseHelper.buildCreateContainerGroupRequest();

        createContainerGroupRequest.setContainerGroupName("eci-test");
        CreateContainerGroupRequest.Container container = new CreateContainerGroupRequest.Container();
        container.setName("nginx-liu");
        container.setImage("nginx");
        container.setMemory(4F);
        container.setCpu(2F);
        container.setImagePullPolicy("IfNotPresent");

        CreateContainerGroupRequest request = new CreateContainerGroupRequest();
        request.setSpotStrategy("SpotWithPriceLimit");
        request.setSpotPriceLimit(0.025f);

        createContainerGroupRequest.setContainers(Arrays.asList(container));
        return EciBaseHelper.doAction(createContainerGroupRequest);
    }
}
