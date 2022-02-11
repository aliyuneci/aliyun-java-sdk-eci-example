package com.aliyuncs.eci.test;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.common.EciBaseHelper;
import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.common.EciTestConstant;
import com.aliyuncs.eci.create.hostAliase.HostAliase;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupRequest.*;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupRequest;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupRequest.DnsConfig.*;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupRequest.Volume.*;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupRequest.Container.*;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupResponse;
import com.aliyuncs.eci.model.v20180808.DescribeContainerGroupsRequest;
import com.aliyuncs.eci.model.v20180808.DescribeContainerGroupsResponse;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.regions.ProductDomain;

import java.util.Arrays;
import java.util.Collections;

public class EciTest20180808 {

    public static void main(String[] args) {

        CreateContainerGroupResponse response = HostAliase.createContainerGroup();
        System.out.println(response.getContainerGroupId());

    }

    private static CreateContainerGroupResponse createContainerGroup() {
        CreateContainerGroupRequest createContainerGroupRequest = new CreateContainerGroupRequest();
        createContainerGroupRequest.setActionName("CreateContainerGroup");//默认已经填充
        createContainerGroupRequest.setRegionId(EciTestConstant.REGION_ID);
        createContainerGroupRequest.setSecurityGroupId(EciTestConstant.SECURITY_GROUP_ID);
        createContainerGroupRequest.setVSwitchId(EciTestConstant.VSWITCH_ID);
        createContainerGroupRequest.setContainerGroupName("liu-test");

        /**
         * volumes
         * volume1: ConfigFileVolume
         */
        Volume volume1 = new Volume();
        volume1.setName("default-volume1-config");
        volume1.setType("ConfigFileVolume");
        ConfigFileVolumeConfigFileToPath configFileToPath = new ConfigFileVolumeConfigFileToPath();
        configFileToPath.setPath("eci/container-config");
        configFileToPath.setContent("content");
        volume1.setConfigFileVolumeConfigFileToPaths(Collections.singletonList(configFileToPath));


        //securityContext
        SecurityContext securityContext = new SecurityContext();
        securityContext.setReadOnlyRootFilesystem(true);
        securityContext.setRunAsUser(1337L);
        Capability capability = new Capability();
        capability.setAdds(Arrays.asList("NET_ADMIN"));
        securityContext.setCapability(capability);


        /**
         * initContainer busyBox
         */
        Container initContainer = new Container();
        initContainer.setName("centos-liu-init");
        initContainer.setImage("registry-vpc.cn-hangzhou.aliyuncs.com/liumi/busybox:1.0");
        initContainer.setMemory(2F);
        initContainer.setCpu(1.0F);
        initContainer.setImagePullPolicy("Always");
        //initContainer.setSecurityContext(securityContext);

        createContainerGroupRequest.setInitContainers(Arrays.asList(initContainer));

        /**
         * container1 share data with container2 through EmptyDirVolume
         */
        Container container = new Container();
        container.setName("nginx-liu");
        container.setImage("registry-vpc.cn-hangzhou.aliyuncs.com/liumi/nginx:alpine");
        container.setMemory(2F);
        container.setCpu(1F);
        container.setImagePullPolicy("Always");

        VolumeMount volumeMount = new VolumeMount();
        volumeMount.setName("default-volume3-empty");
        volumeMount.setMountPath("/usr/share/nginx/html");
        volumeMount.setReadOnly(false);

        container.setVolumeMounts(Collections.singletonList(volumeMount));

        //container.setSecurityContext(securityContext);

        /**
         * container 2 centOS mount both NFSVolume(NAS) and EmptyDirVolume
         */
        Container container2 = new Container();
        container2.setName("centos-liu");
        container2.setImage("registry-vpc.cn-hangzhou.aliyuncs.com/liumi/busybox:1.0");
        container2.setMemory(2F);
        container2.setCpu(1.0F);
        container2.setImagePullPolicy("Always");

        //dns config
        DnsConfig dnsConfig = new DnsConfig();
        dnsConfig.setNameServers(Arrays.asList("8.8.8.8"));
        dnsConfig.setSearches(Arrays.asList("ns.kubelete.local"));
        DnsConfigOption option = new DnsConfigOption();
        option.setName("name");
        option.setValue("value");
        dnsConfig.setOptions(Arrays.asList(option));


        createContainerGroupRequest.setDnsConfig(dnsConfig);
        createContainerGroupRequest.setVolumes(Arrays.asList(volume1));
        createContainerGroupRequest.setContainers(Arrays.asList(container, container2));

        return EciBaseHelper.getAcsResponse(createContainerGroupRequest);
    }

    public static DescribeContainerGroupsResponse describeContainerGroups(String[] containerGroupIds) {
        DescribeContainerGroupsRequest describeContainerGroupsRequest = new DescribeContainerGroupsRequest();
        fillParam(describeContainerGroupsRequest);
        describeContainerGroupsRequest.setActionName("DescribeContainerGroups");//默认已经填充
        describeContainerGroupsRequest.setRegionId(EciTestConstant.REGION_ID);
        describeContainerGroupsRequest.setStatus("Running");
        describeContainerGroupsRequest.setContainerGroupIds(JSONObject.toJSONString(containerGroupIds));

        return EciBaseHelper.getAcsResponse(describeContainerGroupsRequest);
    }


    /**
     * 默认请求参数的填充
     *
     * @param request RpcAcsRequest
     */
    private static void fillParam(RpcAcsRequest request) {
        request.setAcceptFormat(FormatType.JSON);
        request.setVersion(EciTestConstant.VERSION);
        ProductDomain productDomain = new ProductDomain(EciTestConstant.PRODUCT, EciTestConstant.PRODUCT_DOMAIN);
        request.setProductDomain(productDomain);
    }

}
