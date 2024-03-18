package DayBridge.Daybridge.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Event {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String text;

    @Column(name = "e_start")
    LocalDateTime start;

    @Column(name = "e_end")
    LocalDateTime end;

    String color;
}
