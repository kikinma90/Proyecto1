package com.example.games_service_api.service.impl;

import com.example.games_service_api.commons.entities.GameModel;
import com.example.games_service_api.repository.GameRepository;
import com.example.games_service_api.service.GameService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameModel createGame(GameModel gameRequest) {
        return Optional.of(gameRequest)
                .map(this::mapToEntity)
                .map(gameRepository::save)
                .orElseThrow(() -> new RuntimeException("Error creating game"));
    }

    @Override
    public GameModel getGame(Long gameId) {
        return Optional.of(gameId)
                .flatMap(gameRepository::findById)
                .orElseThrow(() -> new RuntimeException("Error couldn't find game by id"));
    }

    @Override
    public GameModel updateGame(Long gameId, GameModel gameRequest) {
        deleteGame(gameId);
        return Optional.of(gameRequest)
                .map(this::mapToEntity)
                .map(gameRepository::save)
                .orElseThrow(() -> new RuntimeException("Error update game"));
    }

    @Override
    public GameModel deleteGame(Long gameId) {
        return Optional.of(gameId)
                .flatMap(gameRepository::findById)
                .map(game -> {
                    gameRepository.deleteById(gameId);
                    return game;
                })
                .orElseThrow(() -> new RuntimeException("Error couldn't delete game by id"));
    }

    private GameModel mapToEntity(GameModel gameRequest) {
        return GameModel.builder()
                .name(gameRequest.getName())
                .build();
    }
}
