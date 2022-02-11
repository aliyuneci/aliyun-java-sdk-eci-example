package com.aliyuncs.imc;


import com.aliyuncs.common.EciBaseHelper;
import com.aliyuncs.eci.model.v20180808.CreateImageCacheRequest;
import com.aliyuncs.eci.model.v20180808.CreateImageCacheResponse;
import com.aliyuncs.common.EciTestConstant;

import java.util.Arrays;

public class CreateImageCache {

    private static CreateImageCacheResponse createImageCache(String regionId, String imageCacheName) {
        CreateImageCacheRequest createImageCacheRequest = new CreateImageCacheRequest();
        createImageCacheRequest.setRegionId(regionId);
        createImageCacheRequest.setImageCacheName(imageCacheName);
        createImageCacheRequest.setVSwitchId(EciTestConstant.VSWITCH_ID);
        createImageCacheRequest.setSecurityGroupId(EciTestConstant.SECURITY_GROUP_ID);
        createImageCacheRequest.setImages(Arrays.asList("nginx", "busybox"));
        createImageCacheRequest.setImageCacheSize(25);
        createImageCacheRequest.setRetentionDays(7);

        return EciBaseHelper.getAcsResponse(createImageCacheRequest);
    }
}
