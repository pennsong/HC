package com.qtc.hospitalcore.domain.query;

import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import lombok.Getter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PPEntity {
    @Getter
    private boolean deleted;

    public void delete() {
        this.deleted = true;
    }
}
