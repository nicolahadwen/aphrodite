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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NoArgsConstructor
@Entity
@Table(
        name = "platform_user",
        schema = "balenciaga",
        indexes = {
                @Index(name = "usernameIndex", columnList = "username")
        }
)
@IdClass(UserEntityId.class)
@Getter
public class UserEntity implements Serializable {
    public UserEntity(@NonNull PlatformEntity platform, @NonNull String username) {
        this.platform = platform;
        this.username = username;
    }

    @Column(name = "username", length = 36)
    @Id
    private String username;

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
        private String username;
    }
}
