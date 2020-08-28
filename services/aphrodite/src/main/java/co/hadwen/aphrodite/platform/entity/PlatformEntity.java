package co.hadwen.aphrodite.platform.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
        name = "platform",
        schema = "balenciaga",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"platform_id"}),
                @UniqueConstraint(columnNames = {"host"}),
        },
        indexes = {
                @Index(name = "host_index", columnList = "host", unique = true)
        }
)
@Getter
public class PlatformEntity {
    @Column(name = "platform_id", length = 36)
    @Id
    private String platformId;
    @Column(name = "host")
    private String host;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;
}

