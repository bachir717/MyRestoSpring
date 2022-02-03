package eu.ensup.MyResto.model;

public enum Types {
    ENTREE,
    PLAT,
    DESSERT,
    BOISSON;

    public static Types getTypeByName(String name)
    {
        if(name==null || name.length()==0) return null;
        for(Types types : Types.values())
        {
            if(name.toUpperCase().equals(types.name()))
                return types;
        }
        return null;
    }
}
