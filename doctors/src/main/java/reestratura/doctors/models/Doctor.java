package reestratura.doctors.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String prizvushe;
    private String name;
    private String work;
    private String avatar;

    public Doctor(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Doctor() {

    }

    public Doctor(String prizvushe, String name, String work) {
        this.prizvushe = prizvushe;
        this.name = name;
        this.work = work;
    }
}
