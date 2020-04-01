package com.example.tm01_sistemavotacionessms;

public class Competitor {
    private String id;
    private String name;
    private String surname;
    private String nickname;
    private int votes;

    public Competitor(String newName, String newSurname, String newNickname) {
        name = newName;
        surname = newSurname;
        nickname = newNickname;
        votes = 0;
    }

    public Competitor(String id, String name, String surname, String nickname, int votes) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.votes = votes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
