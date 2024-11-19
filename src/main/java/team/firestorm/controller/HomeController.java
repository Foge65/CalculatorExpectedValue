package team.firestorm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import team.firestorm.model.DesiredProfitModel;
import team.firestorm.model.HaveHoursPerMonthModel;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final DesiredProfitModel desiredProfitModel;
    private final HaveHoursPerMonthModel haveHoursModel;

    @GetMapping()
    public String home(Model uiModel) {
        uiModel.addAttribute("desiredProfitModel", desiredProfitModel);
        uiModel.addAttribute("haveHoursModel", haveHoursModel);

        return "index";
    }
}
