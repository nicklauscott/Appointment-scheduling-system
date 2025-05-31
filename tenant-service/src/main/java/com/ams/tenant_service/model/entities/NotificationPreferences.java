package com.ams.tenant_service.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Map;

@Embeddable
@Data
public class NotificationPreferences {

    @Column(name = "sender_email")
    private String senderEmail;

    @ElementCollection
    @CollectionTable(name = "notification_templates", joinColumns = @JoinColumn(name = "tenant_id"))
    @MapKeyColumn(name = "template_type")
    @Column(name = "template_id")
    private Map<String, String> templates;

}
