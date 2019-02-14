package com.ladtor.workflow.task.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liudongrong
 * @date 2019/2/4 14:13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Key {
    private String serialNo;
    private Integer version;
    private Integer runVersion;
    private String nodeId;
}
