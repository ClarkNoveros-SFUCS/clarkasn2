package com.clarkasn2.clarkasn2.models;

public class ProfessorProfile extends StaffMemberProfile{
    
    @Override
    public String displayBadge(){
        return "Professor";
    }
}
