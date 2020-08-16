package com.qtc.hospitalcore.domain.chongFuJianCe;

import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
public class YongHuShouJiHao {
    @Id
    @GeneratedValue
    long id;

    @Column(unique = true)
    String shouJiHao;

    public YongHuShouJiHao(String shouJiHao) {
        this.shouJiHao = shouJiHao;
    }
}
