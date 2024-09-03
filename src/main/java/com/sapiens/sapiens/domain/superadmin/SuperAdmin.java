package com.sapiens.sapiens.domain.superadmin;

import com.sapiens.sapiens.domain.secretariat.Secretariat;
import com.sapiens.sapiens.domain.user.User;
import com.sapiens.sapiens.domain.user.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SuperAdmin extends User {

    private String position;

    @OneToOne(fetch = FetchType.EAGER)
    private Secretariat secretariat;

    public SuperAdmin(String name, String email, String password, UserRole role) {
        super(name, email, password, role);
    }

}
