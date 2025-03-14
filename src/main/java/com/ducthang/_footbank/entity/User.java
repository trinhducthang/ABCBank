package com.ducthang._footbank.entity;

import com.ducthang._footbank.entity.enum_.Gender;
import com.ducthang._footbank.entity.enum_.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String phone;
    private Date dob;
    private Role role;
    private boolean isDisabled;


    

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Lob
    private String address;

//    @OneToOne(mappedBy = "user")
//    private Loan loans;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    @JsonBackReference  // Ngừng serialize "loan" để tránh vòng lặp
    private Loan loan;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user",cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Set<AccountBank> accountBanks;


    @OneToOne
    @JoinColumn(name = "fastLoan_id") // Cột khóa ngoại trong bảng User, liên kết với Loan
    private FastLoan fastLoan;

}

