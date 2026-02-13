package com.clarkasn2.clarkasn2.models;

public class ProfessorProfile extends StaffMemberProfile{
    
    @Override
    public String displayName(String name){
        return "Professor " + name;
    }
}
