package com.aliyuncs.slb;

import com.alibaba.fastjson.JSON;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.slb.model.v20140515.DescribeHealthStatusRequest;
import com.aliyuncs.slb.model.v20140515.DescribeHealthStatusResponse;

/**
 * @author diseng
 * @date 2020/1/20
 */
public class DescribeEciStatusInSlb {

    private static final String REGION_ID = "cn-hangzhou";
    private static final String AK_ID = "xxx";
    private static final String AK_SECRET = "xxx";

    private static final String SLB_ID = "lb-xxx";

    public static void main(String[] args) {
        IClientProfile iClientProfile = DefaultProfile.getProfile(REGION_ID, AK_ID, AK_SECRET);
        DefaultAcsClient client = new DefaultAcsClient(iClientProfile);

        DescribeHealthStatusRequest describeHealthStatusRequest = new DescribeHealthStatusRequest();
        describeHealthStatusRequest.setLoadBalancerId(SLB_ID);
        try {
            DescribeHealthStatusResponse response = client.getAcsResponse(describeHealthStatusRequest);
            System.out.println(JSON.toJSONString(response));
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
