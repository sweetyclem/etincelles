package com.etincelles.enumeration;

public enum Type {
    Carriere( "Carrière" ), DataMarketing( "Carrière Data Marketing" ), Startup(
            "Startup" ), DigitalBuisnessDevelopment( "Digital Business Development" ), SalesforceDatabaseAdmin(
                    "Administration Base de données Salesforce" );

    private final String name;

    private Type( String s ) {
        name = s;
    }

    public boolean equalsName( String otherName ) {
        // (otherName == null) check is not needed because name.equals(null)
        // returns false
        return name.equals( otherName );
    }

    public String toString() {
        return this.name;
    }
}
