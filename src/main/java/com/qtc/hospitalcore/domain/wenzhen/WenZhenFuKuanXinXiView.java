package com.qtc.hospitalcore.domain.wenzhen;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class WenZhenFuKuanXinXiView {
    @Id
    UUID id;
}