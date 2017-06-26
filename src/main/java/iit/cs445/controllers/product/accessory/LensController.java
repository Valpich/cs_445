package iit.cs445.controllers.product.accessory;

import iit.cs445.models.products.Lens;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LensController {

    @RequestMapping(value = "/product/accessory/lens", method = RequestMethod.GET)
    public String listAll(Model model) {
        List<Lens> list = new Lens().listAll();
        model.addAttribute("lenses", list);
        return "product";
    }

    @RequestMapping(value = "/product/accessory/lens/add", method = RequestMethod.GET)
    public String showLensForm(Model model) {
        Lens lens = new Lens();
        model.addAttribute("lensForm", lens);
        return "productForm";
    }

    @RequestMapping(value = "/lens", method = RequestMethod.POST)
    public String checkoutPost(@RequestParam("description") String description,
                               @RequestParam("focal") String focal,
                               @RequestParam("magnification") String magnification,
                               @RequestParam("price") String price) {
        saveLens(description, focal, magnification, price);
        return "index";
    }

    private void saveLens(String description, String focal, String magnification, String price) {
        Lens lens = new Lens();
        lens.setPrice(Float.parseFloat(price));
        lens.setFocal(Float.parseFloat(focal));
        lens.setMagnification(Float.parseFloat(magnification));
        lens.setDescription(description);
        lens.saveNew();
    }

}
