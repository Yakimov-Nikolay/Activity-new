package activity_new.activity_new.web;

import activity_new.activity_new.service.ActivityService;
import activity_new.activity_new.service.QRCodeService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private QRCodeService qrCodeService;

    private final ActivityService activityService;

    public HomeController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @RequestMapping("/")
    public String indexPage() {

        return "index";
    }

    @PostMapping("/showQRCode")
    public String showQRCode(String qrContent, Model model) {

        model.addAttribute("qrCodeContent", "/generateQRCode?qrContent=" + qrContent);

        return "show_qr_code";
    }

    @GetMapping("/generateQRCode")
    public void generateQRCode(String qrContent, HttpServletResponse response) throws IOException, WriterException {
        response.setContentType("image/png");
        byte[] qrCode = qrCodeService.generateQRCode(qrContent, 500, 500);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(qrCode);
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        model.addAttribute("user_home", activityService.findActivityEntitiesByAuthorUsername(principal.getName()));
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

}
