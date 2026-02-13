package com.clarkasn2.clarkasn2.models;

public class InstructorProfile extends StaffMemberProfile {
    
    @Override
    public String displayName(String name){
        return "Instructor " + name;
    }
}
