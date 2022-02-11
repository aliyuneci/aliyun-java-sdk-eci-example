package com.aliyuncs.eci.delete;

import com.aliyuncs.common.AbstractApi;
import com.aliyuncs.common.EciBaseHelper;
import com.aliyuncs.common.EciTestConstant;
import com.aliyuncs.eci.model.v20180808.DeleteContainerGroupRequest;
import com.aliyuncs.eci.model.v20180808.DeleteContainerGroupResponse;
import com.aliyuncs.eci.model.v20180808.DescribeContainerGroupsRequest;
import com.aliyuncs.eci.model.v20180808.DescribeContainerGroupsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class BatchDelete extends AbstractApi {

    public static void main(String[] args) {

        BatchDelete batchDelete = new BatchDelete();
        try {
            batchDelete.batchDelete("Failed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据状态批量分页查询eci，并进行删除
     *
     * @param status
     */
    private void batchDelete(String status) {
        DescribeContainerGroupsRequest describeContainerGroupsRequest = new DescribeContainerGroupsRequest();
        describeContainerGroupsRequest.setRegionId(getRegion());
        describeContainerGroupsRequest.setStatus(status);
        String nextToken = null;
        do {
            describeContainerGroupsRequest.setNextToken(nextToken);
            DescribeContainerGroupsResponse response;
            try {
                response = EciBaseHelper.getAcsResponse(client, describeContainerGroupsRequest);
            } catch (ClientException e) {
                System.out.println(String.format("describe containerGroup failed %s", e.getMessage()));
                break;
            }

            System.out.println(String.format("batch delete containerGroups %s, nextToken %s",
                    response.getRequestId(), response.getNextToken()));

            innerBatchDelete(response.getContainerGroups());

            nextToken = response.getNextToken();

        } while (StringUtils.isNotBlank(nextToken));
    }

    private void innerBatchDelete(List<DescribeContainerGroupsResponse.ContainerGroup> containerGroups) {
        if (CollectionUtils.isEmpty(containerGroups)) {
            return;
        }

        containerGroups.forEach(item -> delete(item.getContainerGroupId()));
    }

    private void delete(String containerGroupId) {
        DeleteContainerGroupRequest deleteContainerGroupRequest = new DeleteContainerGroupRequest();
        deleteContainerGroupRequest.setRegionId(getRegion());
        deleteContainerGroupRequest.setContainerGroupId(containerGroupId);

        try {
            DeleteContainerGroupResponse response = EciBaseHelper.getAcsResponse(client, deleteContainerGroupRequest);
            System.out.println(String.format("delete containerGroup %s success %s", containerGroupId, response.getRequestId()));
        } catch (ClientException e) {
            System.out.println(String.format("delete containerGroup %s failed %s", containerGroupId, e.getMessage()));
        }
    }


    @Override
    protected String getProduct() {
        return "Eci";
    }

    @Override
    protected String getDomain() {
        return "eci.aliyuncs.com";
    }

    @Override
    protected String getRegion() {
        return EciTestConstant.REGION_ID;
    }

}