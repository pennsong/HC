package com.qtc.hospitalcore.domain.jiankangdangan;

import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
public class JianKangDangAnView {
    @Id
    UUID id;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXi;
}