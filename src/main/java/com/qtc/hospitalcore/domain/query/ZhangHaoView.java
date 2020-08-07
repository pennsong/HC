package com.qtc.hospitalcore.domain.query;

import com.qtc.hospitalcore.domain.ZhangHao;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class ZhangHaoView {
    @Id
    UUID id;

    String username;
    String password;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<ZhangHao.JueSe> jueSeSet;
}