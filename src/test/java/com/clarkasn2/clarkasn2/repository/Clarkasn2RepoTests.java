package com.clarkasn2.clarkasn2.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.clarkasn2.clarkasn2.models.InstructorProfile;
import com.clarkasn2.clarkasn2.models.ProfessorProfile;
import com.clarkasn2.clarkasn2.models.Role;
import com.clarkasn2.clarkasn2.models.UserRepository;
import com.clarkasn2.clarkasn2.models.Users;

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

        String expected = new InstructorProfile().displayName("Eric Clapton");
        assertThat(u.getDisplayName()).isEqualTo(expected);
    }

    //Let's test getting the average
    @Test
    public void GetAverage_ReturnCorrectAverage(){
        Users u = new Users();
        u.setClarity(5);
        u.setNiceness(4);
        u.setKnowledgeableScore(5);

        assertThat(u.getAverageScore()).isEqualTo(4.7);

    }


}

