package pl.robert.repos.app;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wtwo")
@AllArgsConstructor
public class WtwoController {

    @GetMapping("sum")
    public Integer sum(@RequestParam("num1") Integer num1, @RequestParam("num2") Integer num2) {
        return num1 + num2;
    }
}
