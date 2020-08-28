package co.hadwen.aphrodite.platform.user.entity;

import co.hadwen.aphrodite.platform.entity.PlatformEntity;
import co.hadwen.aphrodite.platform.user.entity.UserEntity.UserEntityId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(
        name = "platform_user",
        schema = "balenciaga"
)
@IdClass(UserEntityId.class)
@Getter
public class UserEntity implements Serializable {
    public UserEntity(@NonNull PlatformEntity platform, @NonNull String userId) {
        this.platform = platform;
        this.userId = userId;
    }

    @Column(name = "user_id", length = 36)
    @Id
    private String userId;

    @Id
    @JoinColumn(name = "platform_id", referencedColumnName = "platform_id")
    @ManyToOne(targetEntity = PlatformEntity.class,
            fetch= FetchType.LAZY,
            cascade={CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            })
    private PlatformEntity platform;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserEntityId implements Serializable {
        @NonNull
        private PlatformEntity platform;
        @NonNull
        private String userId;
    }
}
