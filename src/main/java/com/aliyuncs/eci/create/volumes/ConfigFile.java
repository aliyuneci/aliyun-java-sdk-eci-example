package com.aliyuncs.eci.create.volumes;


import com.aliyuncs.common.EciBaseHelper;
import com.aliyuncs.common.VolumeType;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupRequest;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupResponse;
import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;
import java.util.Collections;

public class ConfigFile {
    public static CreateContainerGroupResponse createContainerGroup() {
        CreateContainerGroupRequest createContainerGroupRequest
                = EciBaseHelper.buildCreateContainerGroupRequest();

        createContainerGroupRequest.setContainerGroupName("eci-test");

        CreateContainerGroupRequest.Volume volume = new CreateContainerGroupRequest.Volume();
        volume.setName("default-volume-config");
        volume.setType(VolumeType.ConfigFileVolume.name());
        CreateContainerGroupRequest.Volume.ConfigFileVolumeConfigFileToPath configFileToPath = new CreateContainerGroupRequest.Volume.ConfigFileVolumeConfigFileToPath();
        configFileToPath.setPath("eci/container-config");
        configFileToPath.setContent(Base64.encodeBase64String("content".getBytes()));
        volume.setConfigFileVolumeConfigFileToPaths(Collections.singletonList(configFileToPath));
        createContainerGroupRequest.setVolumes(Arrays.asList(volume));

        CreateContainerGroupRequest.Container container = new CreateContainerGroupRequest.Container();
        container.setName("nginx-liu");
        container.setImage("nginx");
        container.setMemory(4F);
        container.setCpu(2F);
        container.setImagePullPolicy("IfNotPresent");

        CreateContainerGroupRequest.Container.VolumeMount volumeMount = new CreateContainerGroupRequest.Container.VolumeMount();
        volumeMount.setName("default-volume-config");
        volumeMount.setMountPath("/pod-data");
        volumeMount.setReadOnly(false);
        container.setVolumeMounts(Arrays.asList(volumeMount));

        createContainerGroupRequest.setContainers(Arrays.asList(container));

        return EciBaseHelper.getAcsResponse(createContainerGroupRequest);
    }
}
