package com.qtc.hospitalcore.domain.yihurenyuan;

import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YiHuRenYuanView {
    @Id
    UUID id;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<YiHuRenYuan.QuanXian> quanXianSet;

    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXiMap;
}