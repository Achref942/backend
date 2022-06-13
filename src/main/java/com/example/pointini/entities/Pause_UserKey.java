package com.example.pointini.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Pause_UserKey implements Serializable {
    @Column(name = "User_id")
    Long UserId;
    @Column(name = "Pause_id")
    Long PauseId;
}
