package com.yassir.bitbox.models.dblogger;

import com.yassir.bitbox.models.Item.Item;
import com.yassir.bitbox.models.user.User;
import jakarta.persistence.*;
import lombok.*;


//---lombok---
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//-------------
@Entity
@Table(name="Action_Logger")
@Builder // inicialize all
public class DbLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "itemCode")
    private Item item;
    @Column(name = "reason_of_action", nullable = false)
    private String reasonMSG;
}
