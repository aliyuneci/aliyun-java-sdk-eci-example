package com.aliyuncs.common;


public enum VolumeType {
    /**
     * 数据卷类型，公测阶段只支持emptyDirVolume、nfsVolume、configMapVolume
     */
    /**
     * emptyDirVolume
     */
    EmptyDirVolume,
    /**
     * nfsVolume
     */
    NFSVolume,
    /**
     * configMapVolume
     */
    ConfigFileVolume,

    /**
     * diskVolume
     */
    DiskVolume,

    /**
     * flexVolume
     */
    FlexVolume,
    ;

}