package aston.JPDTeam6.SimulatorLibrary;

import aston.JPDTeam6.SimulatorLibrary.Model.Actor;

public class Event
{

    private String name;
    private String detail;
    private Actor  actor;
    private String type;

    public Event(String name, Actor actor, String type)
    {
        this(name, actor, type, "");
    }

    public Event(String name, Actor actor, String type, String detail)
    {
        this.name = name;
        this.actor = actor;
        this.detail = detail;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public Actor getActor()
    {
        return actor;
    }

    public String getDetail()
    {
        return detail;
    }
    
    public String getType()
    {
        return type;
    }
}
