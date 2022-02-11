package com.aliyuncs.imc;

import com.aliyuncs.common.EciBaseHelper;
import com.aliyuncs.eci.model.v20180808.DescribeImageCachesRequest;
import com.aliyuncs.eci.model.v20180808.DescribeImageCachesResponse;

public class DescribeImageCaches {

    private static DescribeImageCachesResponse describeImageCaches(String regionId, String imageCacheId) {
        DescribeImageCachesRequest describeImageCachesRequest = new DescribeImageCachesRequest();
        describeImageCachesRequest.setRegionId(regionId);
        describeImageCachesRequest.setImageCacheId(imageCacheId);

        return EciBaseHelper.getAcsResponse(describeImageCachesRequest);
    }
}
