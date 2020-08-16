package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import lombok.Getter;

public class PPAggregate {

    @Getter
    private boolean deleted;

    protected void checkDeleted() {
        if (this.deleted) {
            throw new PPBusinessException("记录已删除, 不可操作");
        }
    }

    protected void delete() {
        this.deleted = true;
    }
}
