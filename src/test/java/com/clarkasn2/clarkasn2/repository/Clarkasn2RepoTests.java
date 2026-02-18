package com.clarkasn2.clarkasn2.repository;

import com.clarkasn2.clarkasn2.models.Users;
import com.clarkasn2.clarkasn2.models.UserRepository;
import com.clarkasn2.clarkasn2.models.ProfessorProfile;
import com.clarkasn2.clarkasn2.models.Role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class Clarkasn2RepoTests {
    @Autowired
    private UserRepository userRepo;

    @Test
    public void SaveRoleTypes_ReturnRoleTypes(){
        //Arrange
        Users user = new Users();
        user.setName("Simon Fraser");
        user.setEmail("simonf@sfu.ca");
        user.setRoleType(Role.INSTRUCTOR);
        user.setClarity(10);
        user.setNiceness(10);
        user.setKnowledgeableScore(10);
        //Act

        Users saved = userRepo.save(user);

        //Assert
        assertThat(saved.getRoleType()).isEqualTo(Role.INSTRUCTOR);
    }

    //Let's test my OOP functionality
    @Test 
    public void DisplayName_RoleProf_returnProfTag(){
        Users u = new Users();
        u.setName("John Mayer");
        u.setRoleType(Role.PROF);

        String expected = new ProfessorProfile().displayName("John Mayer");
        assertThat(u.getDisplayName()).isEqualTo(expected);
    }

    @Test 
    public void DisplayName_RoleInstructor_returnInstructorTag(){
        Users u = new Users();
        u.setName("Eric Clapton");
        u.setRoleType(Role.INSTRUCTOR);

        String expected = new InstructorProfile().displayName("John Mayer");
        assertThat(u.getDisplayName()).isEqualTo(expected);
    }


}
