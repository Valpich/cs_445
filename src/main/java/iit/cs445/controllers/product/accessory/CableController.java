package iit.cs445.controllers.product.accessory;

import iit.cs445.models.products.Cable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CableController {

    @RequestMapping(value = "/product/accessory/cable", method = RequestMethod.GET)
    public String listAll(Model model) {
        List<Cable> list = new Cable().listAll();
        model.addAttribute("cables", list);
        return "product";
    }

    @RequestMapping(value = "/product/accessory/cable/add", method = RequestMethod.GET)
    public String showCableForm(Model model) {
        Cable cable = new Cable();
        model.addAttribute("cableForm", cable);
        return "productForm";
    }

    @RequestMapping(value = "/cable/{id}/update", method = RequestMethod.GET)
    public String showUpdateCableForm(@PathVariable("id") Long id, Model model) {
        Cable cable = new Cable().findById(id);
        model.addAttribute("cableFormUpdate", cable);
        return "productForm";
    }

    @RequestMapping(value = "/cable/{id}/delete", method = RequestMethod.GET)
    public String deleteCable(@PathVariable("id") Long id, Model model) {
        Cable cable = new Cable().findById(id);
        cable.setDeleted(true);
        cable.update();
        model.addAttribute("cableForm", cable);
        return "index";
    }

    @RequestMapping(value = "/cable", method = RequestMethod.POST)
    public String checkoutPost(@RequestParam("description") String description ,
                               @RequestParam("length") String length,
                               @RequestParam("price") String price ) {
        saveCable(description, length, price);
        return "index";
    }

    @RequestMapping(value = "/cableUpdate", method = RequestMethod.POST)
    public String checkoutPost(@RequestParam("id") String id,
                                @RequestParam("description") String description ,
                                @RequestParam("length") String length,
                                @RequestParam("price") String price ) {
        updateCable(id, description, length, price);
        return "index";
    }

    private void saveCable(String description, String length, String price) {
        Cable cable = new Cable();
        cable.setPrice(Float.parseFloat(price));
        cable.setLength(Float.parseFloat(length));
        cable.setDescription(description);
        cable.setDeleted(false);
        cable.saveNew();
    }

    private void updateCable(String id, String description, String length, String price) {
        Cable cableOld = new Cable().findById(Long.parseLong(id));
        cableOld.setDeleted(true);
        cableOld.update();

        Cable cable = new Cable();
        cable.setPrice(Float.parseFloat(price));
        cable.setLength(Float.parseFloat(length));
        cable.setDescription(description);
        cable.setDeleted(false);
        cable.saveNew();
    }

}
