package com.qtc.hospitalcore.domain.chongFuJianCe;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class JianKangDangAnShenFenZheng {
    @Id
    @GeneratedValue
    long id;

    @Column(unique = true)
    String shenFenZheng;

    public JianKangDangAnShenFenZheng(String shenFenZheng) {
        this.shenFenZheng = shenFenZheng;
    }
}
