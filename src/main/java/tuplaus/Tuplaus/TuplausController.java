package tuplaus.Tuplaus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TuplausController {

    @PostMapping("tuplaus")
    public Integer tuplaus(@RequestBody Integer numero) {

        return numero + 1;
    }

    @GetMapping("/")
    public String root() {

        return "root";
    }

    @GetMapping("tuplaus")
    public String tuplaus() {

        return "get";
    }
}
