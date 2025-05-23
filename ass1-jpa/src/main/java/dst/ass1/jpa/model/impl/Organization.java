package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.IEmployment;
import dst.ass1.jpa.model.IOrganization;
import dst.ass1.jpa.model.IVehicle;
import dst.ass1.jpa.util.Constants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = Constants.T_ORGANIZATION)
public class Organization implements IOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Constants.M_ORGANIZATION_NAME)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Organization.class)
    @JoinTable(
        name = Constants.J_ORGANIZATION_PARTS,
        joinColumns = @JoinColumn(name = Constants.I_ORGANIZATION_PART_OF),
        inverseJoinColumns = @JoinColumn(name = Constants.I_ORGANIZATION_PARTS)
    )
    private Collection<IOrganization> parts = new ArrayList<>();

    @ManyToMany(mappedBy = "parts", targetEntity = Organization.class)
    private Collection<IOrganization> partOf = new ArrayList<>();

    @OneToMany(mappedBy = "id.organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Employment.class)
    private Collection<IEmployment> employments = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Vehicle.class)
    @JoinTable(
        name = Constants.J_ORGANIZATION_VEHICLE,
        joinColumns = @JoinColumn(name = Constants.I_ORGANIZATION),
        inverseJoinColumns = @JoinColumn(name = Constants.I_VEHICLE)
    )
    private Collection<IVehicle> vehicles = new ArrayList<>();

    public Organization() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<IOrganization> getParts() {
        return parts;
    }

    @Override
    public void setParts(Collection<IOrganization> parts) {
        this.parts = parts;
    }

    @Override
    public void addPart(IOrganization part) {
        this.parts.add(part);
    }

    @Override
    public Collection<IOrganization> getPartOf() {
        return partOf;
    }

    @Override
    public void setPartOf(Collection<IOrganization> partOf) {
        this.partOf = partOf;
    }

    @Override
    public void addPartOf(IOrganization partOf) {
        this.partOf.add(partOf);
    }

    @Override
    public Collection<IEmployment> getEmployments() {
        return employments;
    }

    @Override
    public void setEmployments(Collection<IEmployment> employments) {
        this.employments = employments;
    }

    @Override
    public void addEmployment(IEmployment employment) {
        this.employments.add(employment);
    }

    @Override
    public Collection<IVehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public void setVehicles(Collection<IVehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public void addVehicle(IVehicle vehicle) {
        this.vehicles.add(vehicle);
    }
}
