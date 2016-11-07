package fi.jamk.sgkygolfcourses;

/**
 * Created by niels on 6/11/2016.
 */

public class GolfCourse
{
    String title;
    String address;
    String email;
    String phone;
    int photo;

    public GolfCourse(String title, String address, String phone, String email, String photo) {
        this.title = title;
        this.address = address;
        this.email = email;
        this.phone = phone;
        switch(photo){
            case "kuvat/kulta.jpg" :
                break;
            case "kuvat/aurinkogolf.jpg" :
                break;
            case "kuvat/farmigolf.jpg" :
                break;
            case "kuvat/golftalma.jpg" :
                break;
            case "kuvat/hangolf.jpg" :
                break;
            case "kuvat/harjatula.jpg":
                break;
            case "kuvat/ikaalisten.jpg":
                break;
            case "kuvat/kaakon.jpg":
                break;
            case "kuvat/kanava.jpg":
                break;


        }

    }
}
