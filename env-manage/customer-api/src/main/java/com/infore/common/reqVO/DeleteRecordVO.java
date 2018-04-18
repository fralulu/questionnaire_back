package com.infore.common.reqVO;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * Created by xuyao on 20/12/2017.
 */
public class DeleteRecordVO extends VideoBaseVO {

    @NotNull(message = "不能为空")
    private List<Integer> record_ids;

    public List<Integer> getRecord_ids() {
        return record_ids;
    }

    public void setRecord_ids(List<Integer> record_ids) {
        this.record_ids = record_ids;
    }
}
