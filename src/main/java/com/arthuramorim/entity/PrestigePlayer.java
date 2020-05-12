package com.arthuramorim.entity;

import java.util.UUID;

public class PrestigePlayer {

    private String name;
    private UUID uuid;
    private Integer prestige;
    private Integer points;

    public PrestigePlayer(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
        this.prestige = 0;
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Integer getPrestige() {
        return prestige;
    }
;
    public Integer getPoints() {
        return points;
    }

    public void setPrestige(Integer prestige) {
        this.prestige = prestige;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void addPoints(Integer points) {
        this.points = this.points + points;
    }

    public void removePoints(Integer points) {
        this.points = this.points - points;
    }

    public void resetPoints() {
        this.points = 0;
    }

    public void addPrestige(Integer prestige) {
        this.prestige = this.prestige+prestige;
    }

    public void removePrestige(Integer prestige) {
        this.prestige = this.prestige-prestige;
    }

    public void resetPrestige(Integer prestige) {
        this.prestige = 0;
    }

}
