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
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer currentSessionId;
    private Integer userId;
    private String uuid;
    private LocalDateTime dateTime;

    public CurrentUserSession(Integer userId, String uuid, LocalDateTime dateTime) {
        super();
        this.userId = userId;
        this.uuid = uuid;
        this.dateTime = dateTime;
    }

}
