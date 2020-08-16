package com.qtc.hospitalcore.domain.chongFuJianCe;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class JianKangDangAnShenFenZhengHao {
    @Id
    @GeneratedValue
    long id;

    @Column(unique = true)
    String shenFenZhengHao;

    public JianKangDangAnShenFenZhengHao(String shenFenZhengHao) {
        this.shenFenZhengHao = shenFenZhengHao;
    }
}
