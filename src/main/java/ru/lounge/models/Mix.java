package ru.lounge.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mixes")
@NamedEntityGraph(
        name = "mixes.with-tobaccos-and-brand",
        attributeNodes = {
                @NamedAttributeNode(value = "tobaccos", subgraph = "tobacco.with-brand")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "tobacco.with-brand",
                        attributeNodes = {
                                @NamedAttributeNode("brand")
                        }
                )
        }
)
public class Mix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "delete_fl")
    private char isDeleted;

    @Column(name = "day_fl")
    private char isMixOfDay;

    @Column(name = "likes")
    private int likesCount;

    @Column(name = "dislikes")
    private int dislikesCount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "mixes_tobaccos",
            joinColumns = @JoinColumn(name = "mix_id"),
            inverseJoinColumns = @JoinColumn(name = "tobacco_id")
    )
    private List<Tobacco> tobaccos = new ArrayList<>();

    public void addTobacco(Tobacco tobacco) {
        this.tobaccos.add(tobacco);
        tobacco.getMixes().add(this);
    }

    public void removeTobacco(Tobacco tobacco) {
        this.tobaccos.remove(tobacco);
        tobacco.getMixes().remove(this);
    }
}
