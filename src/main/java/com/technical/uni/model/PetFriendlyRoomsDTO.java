package com.technical.uni.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class PetFriendlyRoomsDTO {
    private long roomsCount;
    private long petFriendlyRooms;
    private long petFriendlyPercent;
}
