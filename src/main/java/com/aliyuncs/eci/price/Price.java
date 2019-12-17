package com.aliyuncs.eci.price;

import com.aliyuncs.common.EciBaseHelper;
import com.aliyuncs.eci.model.v20180808.DescribeContainerGroupPriceRequest;
import com.aliyuncs.http.HttpResponse;

public class Price {
    public static HttpResponse describeContainerGroupPrice(String regionId, Float cpu, Float mem) {
        DescribeContainerGroupPriceRequest describeContainerGroupPriceRequest
                = new DescribeContainerGroupPriceRequest();

        describeContainerGroupPriceRequest.setRegionId(regionId);
        describeContainerGroupPriceRequest.setCpu(cpu);
        describeContainerGroupPriceRequest.setMemory(mem);
        return EciBaseHelper.doAction2(describeContainerGroupPriceRequest);
    }
}
