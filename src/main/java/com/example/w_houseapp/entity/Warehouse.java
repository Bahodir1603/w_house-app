package com.example.w_houseapp.entity;

import com.example.w_houseapp.entity.template.AbsNameEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Warehouse extends AbsNameEntity {

    private boolean active;
    @JsonIgnore
    @OneToMany(mappedBy = "warehouse",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public List<Employee> employeeList;


}
