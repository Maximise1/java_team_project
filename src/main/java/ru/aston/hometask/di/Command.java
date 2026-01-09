package ru.aston.hometask.di;

public class Command {
    public CommandType type;
    public String[] params;
    
    public Command(CommandType commandType, String[] commandParams) {
        this.type = commandType;
        this.params = commandParams;
    }
}