package com.qtc.hospitalcore.domain.zhanghao;

import com.qtc.hospitalcore.domain.query.PPEntity;
import com.qtc.hospitalcore.domain.util.HashMapConverter;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@SqlResultSetMapping(
        name = "FridayEmployeeResult",
        columns = {@ColumnResult(name = "employeeId")}
)


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ZhangHaoView extends PPEntity {
    @Id
    @Column(columnDefinition = "varchar(128) not null")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    String username;
    String password;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<JueSe> jueSeSet;

    @Column(columnDefinition = "varchar(128)")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID yongHuId;

    @Column(columnDefinition = "varchar(128)")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID yiHuRenYuanId;
}