package iit.cs445.controllers.product.dvr;

import iit.cs445.models.products.DigitalDVR;
import iit.cs445.models.products.StorageType;
import iit.cs445.models.users.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DigitalDVRController {

    @RequestMapping(value = "/product/dvr/digital", method = RequestMethod.GET)
    public String listAll(Model model) {
        List<DigitalDVR> list = new DigitalDVR().listAll();
        model.addAttribute("digitalsDVR", list);
        return "product";
    }

    @RequestMapping(value = "/product/dvr/digital/add", method = RequestMethod.GET)
    public String showAddDigitalDVRForm(Model model) {
        DigitalDVR digitalDVR = new DigitalDVR();
        model.addAttribute("digitalDVRForm", digitalDVR);
        return "productForm";
    }

    @RequestMapping(value = "/digitalDVR/{id}/cart", method = RequestMethod.GET)
    public String addDigitalDVRToCart(HttpServletRequest request, @PathVariable("id") Long id, Model model) {
        DigitalDVR digitalDVR = new DigitalDVR().findById(id);
        saveCart(request, digitalDVR);
        List<DigitalDVR> list = new DigitalDVR().listAll();
        model.addAttribute("digitalsDVR", list);
        return "product";
    }

    private boolean saveCart(HttpServletRequest request, DigitalDVR digitalDVR) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.add(digitalDVR);
        return true;
    }

    @RequestMapping(value = "/digitalDVR/{id}/update", method = RequestMethod.GET)
    public String showUpdateDigitalDVRForm(@PathVariable("id") Long id, Model model) {
        DigitalDVR digitalDVR = new DigitalDVR().findById(id);
        model.addAttribute("digitalDVRFormUpdate", digitalDVR);
        return "productForm";
    }

    @RequestMapping(value = "/digitalDVR/{id}/delete", method = RequestMethod.GET)
    public String deleteDigitalDVR(@PathVariable("id") Long id, Model model) {
        DigitalDVR digitalDVR = new DigitalDVR().findById(id);
        digitalDVR.setDeleted(true);
        digitalDVR.update();
        List<DigitalDVR> list = new DigitalDVR().listAll();
        model.addAttribute("digitalsDVR", list);
        return "product";
    }

    @RequestMapping(value = "/digitalDVR", method = RequestMethod.POST)
    public String checkoutPost(@RequestParam("description") String description,
                               @RequestParam("storage_types") String storageTypes,
                               @RequestParam("price") String price,
                               Model model) {
        saveDigitalDVR(description, storageTypes, price);
        List<DigitalDVR> list = new DigitalDVR().listAll();
        model.addAttribute("digitalsDVR", list);
        return "product";
    }

    @RequestMapping(value = "/digitalDVRUpdate", method = RequestMethod.POST)
    public String checkoutPost(@RequestParam("id") String id,
                               @RequestParam("description") String description,
                               @RequestParam("storage_types") String storageTypes,
                               @RequestParam("price") String price,
                               Model model) {
        updateDigitalDVR(id, description, storageTypes, price);
        List<DigitalDVR> list = new DigitalDVR().listAll();
        model.addAttribute("digitalsDVR", list);
        return "product";
    }

    private void saveDigitalDVR(String description, String storage, String price) {
        DigitalDVR digitalDVR = new DigitalDVR();
        digitalDVR.setPrice(Float.parseFloat(price));
        List<StorageType> storageTypes = new ArrayList<>();
        if (storage.equals("DISK_DRIVE")) {
            storageTypes.add(StorageType.DISK_DRIVE);
        } else if (storage.equals("USB")) {
            storageTypes.add(StorageType.USB);
        } else if (storage.equals("SSD")) {
            storageTypes.add(StorageType.SSD);
        } else if (storage.equals("SD_MEMORY_CARD")) {
            storageTypes.add(StorageType.SD_MEMORY_CARD);
        } else {
            storageTypes.add(StorageType.OTHER);
        }
        digitalDVR.setStorageTypes(storageTypes);
        digitalDVR.setDescription(description);
        digitalDVR.setDeleted(false);
        digitalDVR.saveNew();
    }

    private void updateDigitalDVR(String id, String description, String storage, String price) {
        DigitalDVR digitalDVROld = new DigitalDVR().findById(Long.parseLong(id));
        digitalDVROld.setDeleted(true);
        digitalDVROld.update();

        DigitalDVR digitalDVR = new DigitalDVR();
        digitalDVR.setPrice(Float.parseFloat(price));
        List<StorageType> storageTypes = new ArrayList<>();
        if (storage.equals("DISK_DRIVE")) {
            storageTypes.add(StorageType.DISK_DRIVE);
        } else if (storage.equals("USB")) {
            storageTypes.add(StorageType.USB);
        } else if (storage.equals("SSD")) {
            storageTypes.add(StorageType.SSD);
        } else if (storage.equals("SD_MEMORY_CARD")) {
            storageTypes.add(StorageType.SD_MEMORY_CARD);
        } else {
            storageTypes.add(StorageType.OTHER);
        }
        digitalDVR.setStorageTypes(storageTypes);
        digitalDVR.setDescription(description);
        digitalDVR.setDeleted(false);
        digitalDVR.saveNew();
    }

}
