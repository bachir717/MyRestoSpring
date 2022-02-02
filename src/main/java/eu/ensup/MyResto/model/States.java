package eu.ensup.MyResto.model;

public enum States {
    CREATED,
    DELIVERED,
    CANCELED;

    private States(){}

    public static States getStateByName(String name)
    {
        if(name==null || name.length()==0) return null;
        for(States state : States.values())
        {
            if(name.toUpperCase().equals(state.name()))
                return state;
        }
        return null;
    }
}
