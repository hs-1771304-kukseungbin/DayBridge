import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @Autowired
    private FormDataRepository formDataRepository; // 데이터베이스 접근을 위한 레포지토리

    @RequestMapping("/form")
    public String showForm() {
        return "form"; // HTML 폼 페이지의 이름 (예: form.html)
    }

    @PostMapping("/submitForm")
    public String submitForm(@RequestParam("color") String color, // 작성 폼에 있는 입력 사항들
                             @RequestParam("furniture") String furniture, // 추후 html과 맞춰서 수정 필요
                             @RequestParam("window") String window,
                             @RequestParam("width") String width,
                             Model model) {
        // 여기서 데이터 처리 및 ChatGPT에게 전달하는 작업 수행
        // 데이터를 데이터베이스에 저장
        FormData formData = new FormData(color, furniture, window, width);
        formDataRepository.save(formData);

        // 파이썬 코드에 전송하기 위해 데이터를 전달할 수 있는 형태로 변환
        String dataToSend = color + "," + furniture + "," + window + "," + width;


        // 모델에 데이터 추가(사용자의 생성 기록에 포함 가능)
        model.addAttribute("color", color);
        model.addAttribute("furniture", furniture);
        model.addAttribute("window", window);
        model.addAttribute("width", width);

        return "result"; // 결과를 표시할 페이지의 이름 (예: result.html)
    }
}