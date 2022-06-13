package com.example.pointini.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pause_User implements Serializable {
    @EmbeddedId
    private  Pause_UserKey id=new Pause_UserKey();

    @ManyToOne
    @MapsId("UserId")
    @JoinColumn(name = "User_id")
    private User user;

    @ManyToOne
    @MapsId("PauseId")
    @JoinColumn(name = "Pause_id")
    private Pause pause;

    private LocalDateTime date;

}
