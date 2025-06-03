package com.ams.tenant_service.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Map;

@Embeddable
@Data
@NoArgsConstructor
public class NotificationPreferences {

    @Column(name = "sender_email")
    private String senderEmail = "";

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "notification_templates", joinColumns = @JoinColumn(name = "tenant_id"))
    @MapKeyColumn(name = "template_type")
    @Column(name = "template_content")
    @Lob
    private Map<String, String> templates = Collections.emptyMap();

}