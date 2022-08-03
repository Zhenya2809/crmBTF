package com.example.crmbtf.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "t_role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Cascade({CascadeType.MERGE})
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        if (!Objects.equals(super.getId(), role.getId())) return false;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        int result = Math.toIntExact(super.getId());
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Role(Long id, String role_user) {
        super.setId(id);
        this.name = role_user;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id: " + super.getId() + ", " +
                "name: " + name + "}";
    }
}