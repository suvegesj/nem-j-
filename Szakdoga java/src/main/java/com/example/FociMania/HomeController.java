package com.example.FociMania;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import javax.validation.Validation;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UzenetRepository uzenetRepo;

    @Autowired
    private FocistaRepo focistaRepo;

    @Autowired
    private KlubRepo klubRepo;

    @Autowired
    private PosztRepo posztRepo;

    @GetMapping("/")
    public String home() {

        return "index";
    }

    @GetMapping("/hiba")
    public String hiba() {

        return "uzenethiba";
    }

    @GetMapping("/home")
    public String user(Model model) {

        return "user";
    }

    @GetMapping("/admin/home")
    public String admin() {

        return "admin";
    }

    @GetMapping("/regisztral")
    public String greetingForm(Model model) {
        model.addAttribute("reg", new User());
        return "regisztral";
    }

    @GetMapping("/contact")
    public String Contact(Model model) {
        model.addAttribute("uzenetek", new Uzenetek());
        return "uzenet";
    }


    @GetMapping("/admin/messages")
    public String uzik(Model model) {
        String targy = targyolvas();
        model.addAttribute("targy",targy);

        String uzenet = uzenetolvas();
        model.addAttribute("uzenet", uzenet);

        return "uzenetekadmin";
    }


    @PostMapping("/beregisztral")
    public String Regisztráció(@ModelAttribute User user, Model model) {
        for(User felhasznalo2: userRepo.findAll())
            if(felhasznalo2.getEmail().equals(user.getEmail())){
                model.addAttribute("uzenet", "Hiba! Válassz másik felhasználónevet kérlek, ez már foglalt!");
                return "reghiba";
            }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = new Role();

        role.setId(3); role.setName("ROLE_USER");
        List<Role> rolelist = new ArrayList<Role>();
        rolelist.add(role);
        user.setRoles(rolelist);
        userRepo.save(user);
        model.addAttribute("id", user.getId());
        return "sikeresreg";
    }



    @PostMapping(value = "/uzenetkuld")
    public String Üzenetküldés(@Valid @ModelAttribute Validation validation, Uzenetek uzenet , Uzenetek targy) {
        if (uzenet.getUzenet().length() < 10 || uzenet.getTargy().length() < 5){
            return "uzenethiba";
        }
        uzenetRepo.save(targy);
        uzenetRepo.save(uzenet);
        return "uzenetjo";
    }


    @GetMapping("/focistak")
    public String focistak(Model model) {

        String klubja=klubja();
        model.addAttribute("klubja", klubja());

        String neve = neve();
        model.addAttribute("neve", neve());

        String szuletett=szuletett();
        model.addAttribute("szuletett", szuletett());

        String meze=meze();
        model.addAttribute("meze", meze());

        String posztja=posztja();
        model.addAttribute("posztja", posztja());

        String nemzetiseg=posztja();
        model.addAttribute("nemzetiseg", nemzetiseg());

        String erteke=erteke();
        model.addAttribute("erteke", erteke());

        return "focistak";
    }


    String neve(){
        String neve="";
        for(Focistak focistak: focistaRepo.findAll()){
            neve+=focistak.getVezeteknev() +" "+focistak.getUtonev();
            neve+="<br>";
        }
        return neve;
    }

    String klubja(){
        String klubja="";
        int temp=0;

        for(Focistak focistak: focistaRepo.findAll()){
              temp+=focistak.getKlubid();

            for(Klub klub : klubRepo.findAll()){
                if(klub.getId()==temp)
                {
                    klubja+= klub.getCegnev();
                    klubja+="<br>";
                    temp=0;
                }
            }
        }
        return klubja;
    }

    String szuletett(){
        String szuletett="";
        for(Focistak focistak: focistaRepo.findAll()){
            szuletett+=focistak.getSzulido();
            szuletett+="<br>";
        }
        return szuletett;
    }

    String meze(){
        String meze="";
        for(Focistak focistak: focistaRepo.findAll()){
            meze+=focistak.getMezszam();
            meze+="<br>";
        }
        return meze;
    }

    String posztja(){
        String posztja="";
        int temp=0;

        for(Focistak focistak: focistaRepo.findAll()){
            temp+=focistak.getPosztid();

            for(Poszt poszt : posztRepo.findAll()){
                if(poszt.getId()==temp)
                {
                   posztja+= poszt.getPosztnev();
                   posztja+="<br>";
                    temp=0;
                }
            }
        }
        return posztja;
    }

    String nemzetiseg(){
        String nemzetiseg="";

        for(Focistak focistak: focistaRepo.findAll()){
            if(focistak.getKulfoldie()==0)
            {
                nemzetiseg+="Működőképes";
                nemzetiseg+="<br>";
            }
            else
            {
             nemzetiseg+="Javításra szorul";
             nemzetiseg+="<br>";
            }
        }
        return nemzetiseg;
    }

    String erteke(){
        String erteke="";
        for(Focistak focistak: focistaRepo.findAll()){
            erteke+=focistak.getErteke()+". HUF";
            erteke+="<br>";
        }
        return erteke;
    }

    String targyolvas(){
        String targy="";
        for(Uzenetek uzenetek: uzenetRepo.findAll()){
            targy+= uzenetek.getTargy();
            targy+="<br>";
        }
        return targy;
    }

    String uzenetolvas(){
        String targy="";
        for(Uzenetek uzenetek: uzenetRepo.findAll()){
            targy+= uzenetek.getUzenet();
            targy+="<br>";
        }
        return targy;
    }



}
