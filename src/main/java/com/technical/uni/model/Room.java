package com.technical.uni.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

// Referenced classes of package com.technical.uni.HotelCMS.model:
//            RoomStatus
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "rooms")
@EqualsAndHashCode(exclude = {"roomStatus"})
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long beds;
    @Column
    private String layout;
    @ManyToOne
    @JoinColumn(name = "room_status_id", referencedColumnName = "id")
    private RoomStatus roomStatus;
    @Column
    private Boolean petFriendly;
    @Column
    private Integer rate;
}