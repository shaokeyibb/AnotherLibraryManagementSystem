package io.hikarilan.anotherlibrarymanagementsystem.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "configurations")
public class Configuration {

    @Id
    @Column(name = "kay", nullable = false) // 故意的，key 在 h2db 是关键字不能用
    private String key;

    @Column(name = "velue", nullable = false) // 故意的，value 在 h2db 是关键字不能用
    private String value;
}
