package com.aliyuncs.eci.describe;

import com.aliyuncs.common.EciBaseHelper;
import com.aliyuncs.eci.model.v20180808.DescribeRegionsRequest;
import com.aliyuncs.eci.model.v20180808.DescribeRegionsResponse;

public class DescribeRegions {

    private static DescribeRegionsResponse describeRegions(String regionId) {
        DescribeRegionsRequest describeRegionsRequest = new DescribeRegionsRequest();
        describeRegionsRequest.setRegionId(regionId);

        return EciBaseHelper.doAction(describeRegionsRequest);
    }
}