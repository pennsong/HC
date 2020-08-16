package com.qtc.hospitalcore.domain.wenzhen;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class WenZhenView {
    @Id
    UUID id;
}