package com.aliyuncs.common;

import com.aliyuncs.AcsRequest;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.eci.model.v20180808.CreateContainerGroupRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class EciBaseHelper {

    public static CreateContainerGroupRequest buildCreateContainerGroupRequest() {
        CreateContainerGroupRequest createContainerGroupRequest = new CreateContainerGroupRequest();
        createContainerGroupRequest.setRegionId(EciTestConstant.REGION_ID);
        createContainerGroupRequest.setSecurityGroupId(EciTestConstant.SECURITY_GROUP_ID);
        createContainerGroupRequest.setVSwitchId(EciTestConstant.VSWITCH_ID);

        return createContainerGroupRequest;
    }

    /**
     * IAcsClient send the request
     * aliyun-sdk-core 版本要求:4.2.5
     *
     * @param request
     * @param <T>
     * @return AcsResponse 返回请求对应的AcsResponse对象
     */
    public static <T extends AcsResponse> T doAction(AcsRequest<T> request) {
        DefaultAcsClient client = null;
        IClientProfile iClientProfile = DefaultProfile.getProfile(EciTestConstant.REGION_ID, EciTestConstant.AK_ID,
                EciTestConstant.AK_SECRET);

        client = new DefaultAcsClient(iClientProfile);

        AcsResponse response = null;
        try {
            response = client.getAcsResponse(request);

        } catch (ClientException e) {
            e.printStackTrace();
        }
        return (T) response;
    }


    /**
     * IAcsClient send the request
     *
     * @param request
     * @return 返回json格式
     */
    public static HttpResponse doAction2(RpcAcsRequest request) {
        IAcsClient iAcsClient = null;
        IClientProfile iClientProfile = DefaultProfile.getProfile(EciTestConstant.REGION_ID, EciTestConstant.AK_ID,
                EciTestConstant.AK_SECRET);

        try {
            DefaultProfile.addEndpoint("cn-hangzhou", EciTestConstant.REGION_ID, EciTestConstant.PRODUCT,
                    EciTestConstant.PRODUCT_DOMAIN);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        iAcsClient = new DefaultAcsClient(iClientProfile);

        HttpResponse response = null;
        try {
            response = iAcsClient.doAction(request);
            System.out.println("responseContent:" + new String(response.getHttpContent()));

        } catch (ClientException e) {
            e.printStackTrace();
        }
        return response;
    }
}
