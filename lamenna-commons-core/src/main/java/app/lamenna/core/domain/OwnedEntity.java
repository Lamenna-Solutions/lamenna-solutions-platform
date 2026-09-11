package app.lamenna.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public abstract class OwnedEntity extends BaseEntity {
    @Column(nullable = false, updatable = false)
    private UUID ownerId;

    public UUID getOwnerId() { return ownerId; }
    public void setOwnerId(UUID ownerId) { this.ownerId = ownerId; }
}
