package com.aliyuncs.common;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public abstract class AbstractApi {

    protected IAcsClient client;

    public AbstractApi() {
        initCrClient();
    }

    protected void initCrClient() {
        try {
            DefaultProfile.addEndpoint(getRegion(), getRegion(), getProduct(), getDomain());
            IClientProfile profile = null == getAkToken() ? DefaultProfile.getProfile(getRegion(), getAkId(), getAkSecret()) :
                    DefaultProfile.getProfile(getRegion(), getAkId(), getAkSecret(), getAkToken());
            DefaultAcsClient defaultAcsClient = new DefaultAcsClient(profile);
            defaultAcsClient.setAutoRetry(false);
            client = defaultAcsClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    abstract protected String getProduct();

    abstract protected String getDomain();

    abstract protected String getRegion();

    protected String getAkId() {
        return EciTestConstant.AK_ID;
    }

    protected String getAkSecret() {
        return EciTestConstant.AK_SECRET;
    }

    protected String getAkToken() {
        return null;
    }
}