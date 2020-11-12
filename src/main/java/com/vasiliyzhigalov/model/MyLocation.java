package com.vasiliyzhigalov.model;

import lombok.*;
import org.telegram.telegrambots.meta.api.objects.Location;

import javax.persistence.*;


@Data
@Entity
@Embeddable
@NoArgsConstructor
public class MyLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float longitude;
    private Float latitude;
    public MyLocation(Location location) {
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
    }
}
