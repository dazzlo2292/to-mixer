package ru.lounge.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tobaccos")
@NamedEntityGraph(
        name = "tobacco-brand-mixes-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("brand"),
                @NamedAttributeNode("mixes")
        }
)
public class Tobacco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(targetEntity = Brand.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Brand brand;

    @Column(name = "strength", nullable = false)
    private int strength;

    @Column(name = "base_fl")
    private char isBase;

    @Column(name = "delete_fl")
    private char isDeleted;

    @ManyToMany(mappedBy = "tobaccos", fetch = FetchType.LAZY)
    private List<Mix> mixes = new ArrayList<>();
}
