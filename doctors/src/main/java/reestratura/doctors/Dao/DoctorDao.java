package reestratura.doctors.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import reestratura.doctors.models.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Integer> {
}
