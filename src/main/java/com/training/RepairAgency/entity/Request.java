package com.training.RepairAgency.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Builder
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String request;
    String status;
    BigDecimal price;
    String reason;
    String creator;

  //  @OneToOne
   // @JoinColumn(name = "creator_id")
  //  private User creator;

    LocalDateTime data;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable
    private Collection<Oplata> oplatas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    User master;
}
