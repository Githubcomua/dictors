package reestratura.doctors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reestratura.doctors.Dao.DoctorDao;
import reestratura.doctors.models.Doctor;
import reestratura.doctors.models.User;
import reestratura.doctors.services.UserService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {


  @Autowired
    private DoctorDao doctorDao;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/saveDoctor")
    public String save (
            @RequestParam ("prizvushe") String prizvushe,
            @RequestParam ("name") String name,
            @RequestParam ("work") String work,
            @RequestParam MultipartFile image,
            Model model) throws IOException {

    Doctor doctor = new Doctor(prizvushe, name, work);

        String path= System.getProperty("user.home")
                + File.separator
                +"images"
                + File.separator
                + image.getOriginalFilename();

            image.transferTo(new File(path));

        doctor.setAvatar(image.getOriginalFilename());

        System.out.println(doctor);
        doctorDao.save(doctor);
        System.out.println(doctor);
        model.addAttribute("doctor", doctor);
        List<Doctor> doctorList = doctorDao.findAll();
        model.addAttribute("allDoctors", doctorList);
        return "greeting";
    }
    @GetMapping("/doctor/{idOfDoctor}")
    public String getSingleDoctor(
            @PathVariable int idOfDoctor,
            Model model
    ){
        Doctor doctor= doctorDao.findById(idOfDoctor).get();
        model.addAttribute("singleDoctor", doctor);
        return "singleDoctorPage";
    }

    @PostMapping("/updateDoctor")
    public String updateDoctor(
//            @RequestParam("id") int id,
//            @RequestParam("prizvushe") int prizvushe,
//            @RequestParam("name") int name,
//            @RequestParam("work") int work
            Doctor doctor,
            Model model
    ){
        System.out.println(doctor);
        doctorDao.save(doctor);
        model.addAttribute("singleDoctor", doctor);
        return"singleDoctorPage";
    }

//    @PostMapping("/deleteDoctor")
//    public String deleteDoctor(
//            Doctor doctor,
//            Model model
//    ){
//        System.out.println(doctor);
//        doctorDao.delete(doctor);
//        model.addAttribute("deleteDoctor", doctor);
//        return"showAllDoctors";
//    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/doctors/{id}")
    public String deleteDoctor(@RequestBody Doctor doctor, @PathVariable ("id")int id){
        doctorDao.delete(doctor);
        return"showAllDoctors";
    }


//    @PostMapping(value = "/delete")
//    public String deleteDoctor(@PathVariable("id")int id){
//        Doctor doctor = new Doctor();
//        System.out.println(doctor);
//        doctorDao.delete(doctor);
//        return"showAllDoctors";
//    }

//    @RequestMapping (value = "/doctors/{id}", method = RequestMethod.DELETE)
//    @ResponseBody
//    public String delete(@PathVariable("id") int id){
//        Doctor doctor = new Doctor();
//        doctorDao.delete(doctor);
//        return "showAllDoctors";
//    }

    @GetMapping("/showAllDoctors")
    public String showAllDoctors(Model model){
        List<Doctor> doctors = doctorDao.findAll();
        model.addAttribute("doctors", doctors);
            return "doctorList";

    }
    @PostMapping("/successURL")
    public String successURL(){
        return "index";
    }

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/saveUser")
    public String saveUser(User user){
    String encode = passwordEncoder.encode(user.getPassword())  ;
    user.setPassword(encode);
    userService.save(user);
        return "/login";
    }
}
