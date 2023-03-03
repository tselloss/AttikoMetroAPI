package com.example.attikometroapi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer currentSessionId;


    private Integer customerId;
    private String uuid;
    private LocalDateTime dateTime;

    public CurrentUser(Integer customerId, String uuid, LocalDateTime dateTime) {
        super();
        this.customerId = customerId;
        this.uuid = uuid;
        this.dateTime = dateTime;
    }

}
