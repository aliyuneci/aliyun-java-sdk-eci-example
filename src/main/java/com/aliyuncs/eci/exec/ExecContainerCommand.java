package com.aliyuncs.eci.exec;

import com.aliyuncs.common.EciBaseHelper;
import com.aliyuncs.eci.model.v20180808.ExecContainerCommandRequest;
import com.aliyuncs.eci.model.v20180808.ExecContainerCommandResponse;

public class ExecContainerCommand {
    public static ExecContainerCommandResponse execContainerCommand(String regionId, String containerGroupId,
                                                                    String containerName, String command) {
        ExecContainerCommandRequest execContainerCommandRequest = new ExecContainerCommandRequest();
        execContainerCommandRequest.setRegionId(regionId);
        execContainerCommandRequest.setContainerGroupId(containerGroupId);
        execContainerCommandRequest.setContainerName(containerName);
        execContainerCommandRequest.setCommand(command);
        return EciBaseHelper.doAction(execContainerCommandRequest);

    }
}
