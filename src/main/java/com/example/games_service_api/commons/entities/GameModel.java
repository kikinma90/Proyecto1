package com.example.games_service_api.commons.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "games")
public class GameModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long gameId;
    private String name;
}
