package com.qtc.hospitalcore.domain.wenzhen;

import com.qtc.hospitalcore.domain.ZhangHao;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class WenZhenView {
    @Id
    UUID id;
}