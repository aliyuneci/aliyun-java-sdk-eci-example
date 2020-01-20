package com.aliyuncs.slb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.slb.model.v20140515.AddBackendServersRequest;
import com.aliyuncs.slb.model.v20140515.AddBackendServersResponse;
import com.aliyuncs.slb.model.v20140515.SetBackendServersResponse;
import com.aliyuncs.slb.model.v20140515.SetBackendServersResponse.BackendServer;
import com.google.gson.Gson;

/**
 * @author diseng
 * @date 2020/1/20
 */
public class AddEciToSlb {

    private static final String REGION_ID = "cn-hangzhou";
    private static final String AK_ID = "xxx";
    private static final String AK_SECRET = "xxx";

    private static final String SLB_ID = "lb-xxx";
    private static final List<String> ECI_IDS = Arrays.asList("eci-xxx1", "eci-xxx1");

    public static void main(String[] args) {
        IClientProfile iClientProfile = DefaultProfile.getProfile(REGION_ID, AK_ID, AK_SECRET);
        DefaultAcsClient client = new DefaultAcsClient(iClientProfile);

        AddBackendServersRequest addBackendServersRequest = new AddBackendServersRequest();
        addBackendServersRequest.setLoadBalancerId(SLB_ID);
        List<BackendServer> backendServers = new ArrayList<>();

        ECI_IDS.forEach(eciId -> {
            SetBackendServersResponse.BackendServer slbBackendServer = new SetBackendServersResponse.BackendServer();
            slbBackendServer.setServerId(eciId);
            slbBackendServer.setWeight("100");
            slbBackendServer.setType("eci");
            backendServers.add(slbBackendServer);
        });

        addBackendServersRequest.setBackendServers(new Gson().toJson(backendServers));
        try {
            AddBackendServersResponse response = client.getAcsResponse(addBackendServersRequest);
            System.out.println(JSON.toJSONString(response));
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
