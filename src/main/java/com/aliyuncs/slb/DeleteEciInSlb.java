package com.aliyuncs.slb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.slb.model.v20140515.RemoveBackendServersRequest;
import com.aliyuncs.slb.model.v20140515.RemoveBackendServersResponse;
import com.aliyuncs.slb.model.v20140515.SetBackendServersResponse;
import com.google.gson.Gson;

/**
 * @author diseng
 * @date 2020/1/20
 */
public class DeleteEciInSlb {

    private static final String REGION_ID = "cn-hangzhou";
    private static final String AK_ID = "xxx";
    private static final String AK_SECRET = "xxx";

    private static final String SLB_ID = "lb-xxx";
    private static final List<String> ECI_IDS = Arrays.asList("eci-xxx1", "eci-xxx1");

    public static void main(String[] args) {
        IClientProfile iClientProfile = DefaultProfile.getProfile(REGION_ID, AK_ID, AK_SECRET);
        DefaultAcsClient client = new DefaultAcsClient(iClientProfile);

        RemoveBackendServersRequest removeBackendServersRequest = new RemoveBackendServersRequest();
        removeBackendServersRequest.setLoadBalancerId(SLB_ID);

        List<SetBackendServersResponse.BackendServer> backendServers = new ArrayList<>();
        ECI_IDS.forEach(eciId->{
            SetBackendServersResponse.BackendServer slbBackendServer = new SetBackendServersResponse.BackendServer();
            slbBackendServer.setServerId(eciId);
            slbBackendServer.setWeight("100");
            backendServers.add(slbBackendServer);
        });

        removeBackendServersRequest.setBackendServers(new Gson().toJson(backendServers));
        try {
            RemoveBackendServersResponse response = client.getAcsResponse(removeBackendServersRequest);
            System.out.println(JSON.toJSONString(response));
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }

}
