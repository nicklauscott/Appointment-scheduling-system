package com.ams.tenant_service.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collections;
import java.util.Map;

@Embeddable
@Data
public class NotificationPreferences {

    @Column(name = "sender_email")
    private String senderEmail;

    // ---------------------------  Remove later  --------------------------------------------
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "notification_templates", joinColumns = @JoinColumn(name = "tenant_id"))
    @MapKeyColumn(name = "template_type")
    @Column(name = "template_id") // Increase templates character length and change column name from 'template_id' to template_content
    private Map<String, String> templates = Collections.emptyMap();

}