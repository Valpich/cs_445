package iit.cs445.controllers.product.accessory;

import iit.cs445.models.products.Monitor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MonitorController {

    @RequestMapping(value = "/product/accessory/monitor", method = RequestMethod.GET)
    public String listAll(Model model) {
        List<Monitor> list = new Monitor().listAll();
        model.addAttribute("monitors", list);
        return "product";
    }

    @RequestMapping(value = "/product/accessory/monitor/add", method = RequestMethod.GET)
    public String showMonitorForm(Model model) {
        Monitor monitor = new Monitor();
        model.addAttribute("monitorForm", monitor);
        return "productForm";
    }

    @RequestMapping(value = "/monitor/{id}/update", method = RequestMethod.GET)
    public String showUpdateMonitoryForm(@PathVariable("id") Long id, Model model) {
        Monitor monitor = new Monitor().findById(id);
        model.addAttribute("monitorFormUpdate", monitor);
        return "productForm";
    }

    @RequestMapping(value = "/monitor/{id}/delete", method = RequestMethod.GET)
    public String deleteMonitory(@PathVariable("id") Long id, Model model) {
        Monitor monitor = new Monitor().findById(id);
        monitor.setDeleted(true);
        monitor.update();
        model.addAttribute("monitorForm", monitor);
        return "index";
    }

    @RequestMapping(value = "/monitor", method = RequestMethod.POST)
    public String checkoutPost(@RequestParam("description") String description ,
                               @RequestParam("size") String size,
                               @RequestParam("max_resolution")  String maxResolution,
                               @RequestParam("price") String price ) {
        saveMonitor(description, size, maxResolution, price);
        return "index";
    }

    @RequestMapping(value = "/monitorUpdate", method = RequestMethod.POST)
    public String checkoutPost(@RequestParam("id") String id,
                                @RequestParam("description") String description ,
                                @RequestParam("size") String size,
                                @RequestParam("max_resolution")  String maxResolution,
                                @RequestParam("price") String price ) {
        updateMonitor(id, description, size, maxResolution, price);
        return "index";
    }

    private void saveMonitor(String description, String size, String maxResolution, String price) {
        Monitor monitor = new Monitor();
        monitor.setPrice(Float.parseFloat(price));
        monitor.setSize(Float.parseFloat(size));
        monitor.setMaxResolution(maxResolution);
        monitor.setDescription(description);
        monitor.setDeleted(false);
        monitor.saveNew();
    }

    private void updateMonitor(String id, String description, String size, String maxResolution, String price) {
        Monitor monitorOld = new Monitor().findById(Long.parseLong(id));
        monitorOld.setDeleted(true);
        monitorOld.update();

        Monitor monitor = new Monitor();
        monitor.setPrice(Float.parseFloat(price));
        monitor.setSize(Float.parseFloat(size));
        monitor.setMaxResolution(maxResolution);
        monitor.setDescription(description);
        monitor.setDeleted(false);
        monitor.saveNew();
    }

}
