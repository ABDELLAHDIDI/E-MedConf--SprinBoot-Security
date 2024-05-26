package com.EMedConf.demo.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Medecin")
public class Medecin {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medecinId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 100)
    private String specialite;

    @Column(name = "profil_image", length = 255)
    private String profilImage;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String bio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    // Getters and setters


    public Long getMedecinId() {
        return medecinId;
    }

    public void setMedecinId(Long medecinId) {
        this.medecinId = medecinId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getProfilImage() {
        return profilImage;
    }

    public void setProfilImage(String profilImage) {
        this.profilImage = profilImage;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Medecin{" +
                "medecinId=" + medecinId +
                ", user=" + user +
                ", specialite='" + specialite + '\'' +
                ", profilImage='" + profilImage + '\'' +
                ", bio='" + bio + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
