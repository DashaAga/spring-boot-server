package net.codejava.CafeManager.controller;

import net.codejava.CafeManager.model.*;
import net.codejava.CafeManager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Контроллер кафе
 */

@Controller
public class CafeController {

    private final CafeService cafeService;
    private final CoffeTeaService coffeTeaService;
    private final BreakfastService breakfastService;
    private final LunchService lunchService;
    private final DeliveryService deliveryService;

    @Autowired
    public CafeController(CafeService cafeService, CoffeTeaService coffeTeaService, BreakfastService breakfastService, LunchService lunchService, DeliveryService deliveryService) {
        this.cafeService = cafeService;
        this.coffeTeaService = coffeTeaService;
        this.breakfastService = breakfastService;
        this.lunchService = lunchService;
        this.deliveryService = deliveryService;
    }

    /**
     * Контроллер GET запроса по ссылке "/login"
     * для перехода на страницу регистрации
     * по ссылке "/login"
     * @return возвращает страницу регистрации
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * Контроллер GET запроса по ссылке "/about"
     * для перехода на страницу регистрации
     * по ссылке "/about"
     * @return возвращает страницу информации о программе
     */
    @GetMapping("/about")
    public String about(){
        return "about";
    }

    /**
     * Контроллер GET запроса по ссылке "/cafe"
     * для получения информации о всех десертах
     * @return возвращает страницу со всеми десертами из базы данных
     */
    @GetMapping("/cafe")
    public String findAll(Model model){
        List<Cafe> cafe = cafeService.findAll();
        model.addAttribute("type", "desserts");
        model.addAttribute("cafe", cafe);
        return "cafe-list";
    }

    /**
     * Контроллер GET запроса по ссылке "/cafe/{table}"
     * для получения информации из таблицы, которая запрашивается
     * @param table параметр таблицы для отображения
     * @return возвращает страницу с запрашиваемой таблицей из базы данных
     */
    @GetMapping("/cafe/{table}")
    public String findAll(Model model, @PathVariable("table") String table){
        switch (table) {
            case "desserts": {
                List<Cafe> cafe = cafeService.findAll();
                model.addAttribute("cafe", cafe);
                model.addAttribute("type", table);
                break;
            }
            case "coffe":
            {
                List<Coffe> coffe = coffeTeaService.findAll();
                model.addAttribute("cafe", coffe);
                model.addAttribute("type", table);
                break;
            }
            case "lunch": {
                List<Lunch> lunch = lunchService.findAll();
                model.addAttribute("cafe", lunch);
                model.addAttribute("type", table);
                break;
            }
            case "breakfast": {
                List<Breakfast> breakfast = breakfastService.findAll();
                model.addAttribute("cafe", breakfast);
                model.addAttribute("type", table);
                break;
            }
            case "delivery": {
                List<Delivery> delivery = deliveryService.findAll();
                model.addAttribute("cafe", delivery);
                model.addAttribute("type", table);
                break;
            }
        }
        return "cafe-list";
    }

    /**
     * Контроллер GET запроса по ссылке "//cafe/{table}/{column}"
     * для полученя информации с применением сортировки
     * @param table параметр таблицы для отображения
     * @param column параметр для выбора столбца сортировки
     * @return возвращает страницу с запрашиваемой таблицей из базы данных,
     * применяя к ней сортировку
     */
    @GetMapping("/cafe/{table}/{column}")
    public String findAll(Model model, @PathVariable("table") String table, @PathVariable("column") String column){
        switch (table) {
            case "desserts":
                List<Cafe> cafe = cafeService.findAll(column);
                model.addAttribute("type", table);
                model.addAttribute("cafe", cafe);
                break;
            case "coffe":
                List<Coffe> coffe = coffeTeaService.findAll(column);
                model.addAttribute("type", table);
                model.addAttribute("cafe", coffe);
                break;
            case "lunch":
                List<Lunch> lunch = lunchService.findAll(column);
                model.addAttribute("type", table);
                model.addAttribute("cafe", lunch);
                break;
            case "breakfast":
                List<Breakfast> breakfast = breakfastService.findAll(column);
                model.addAttribute("type", table);
                model.addAttribute("cafe", breakfast);
                break;
            case "delivery":
                List<Delivery> delivery = deliveryService.findAll(column);
                model.addAttribute("type", table);
                model.addAttribute("cafe", delivery);
                break;
        }
        return "cafe-list";
    }

    /**
     * Контроллер POST запроса по ссылке "/find/{table}"
     * для полученя определенных строк из таблицы
     * @param table параметр таблицы для отображения
     * @return возвращает страницу с запрашиваемой таблицей,
     * проводя поиск по строке
     */
    @PostMapping("/find/{table}")
    public String search(Model model, @PathVariable("table") String table, @RequestParam("searchline") String searchline){
        switch (table) {
            case "desserts":
                List<Cafe> cafe = cafeService.searchCafe(searchline);
                model.addAttribute("type", table);
                model.addAttribute("cafe", cafe);
                break;
            case "coffe":
                List<Coffe> coffe = coffeTeaService.searchCoffe(searchline);
                model.addAttribute("type", table);
                model.addAttribute("cafe", coffe);
                break;
            case "lunch":
                List<Lunch> lunch = lunchService.searchLunch(searchline);
                model.addAttribute("type", table);
                model.addAttribute("cafe", lunch);
                break;
            case "breakfast":
                List<Breakfast> breakfast = breakfastService.searchBreakfast(searchline);
                model.addAttribute("type", table);
                model.addAttribute("cafe", breakfast);
                break;
            case "delivery":
                List<Delivery> delivery = deliveryService.searchDelivery(searchline);
                model.addAttribute("type", table);
                model.addAttribute("cafe", delivery);
                break;
        }
        return "cafe-list";
    }

    /**
     * Контроллер REQUEST запроса по ссылке "/dessert-create"
     * для возвращения на страницу с таблицей после добавления данных
     * @return возвращает страницу с таблицей дессертов из базы данных,
     * в которую добавлен новый дессерт
     */
    @RequestMapping("/dessert-create")
    public String newDessertForm(Map<String, Object> model) {
        Cafe dessert = new Cafe();
        model.put("dessert", dessert);
        return "new_dessert";
    }

    /**
     * Контроллер REQUEST запроса по ссылке "/dessert-create"
     * для добавления нового десерта в базу данных
     * @return возвращает страницу с таблицей дессертов из базы данных,
     * в которую добавлен новый дессерт
     */
    @RequestMapping(value = "/dessert-create", method = RequestMethod.POST)
    public String saveDessert(@ModelAttribute("dessert") Cafe dessert) {
        cafeService.saveCafe(dessert);
        return "redirect:/cafe";
    }

    /**
     * Контроллер REQUEST запроса по ссылке "/coffe-create"
     * для возвращения на страницу с таблицей после добавления данных
     * @return возвращает страницу с таблицей напитков из базы данных,
     * в которую добавлен новый напиток
     */
    @RequestMapping("/coffe-create")
    public String newCoffeForm(Map<String, Object> model) {
        Coffe coffe = new Coffe();
        model.put("coffe", coffe);
        return "new_coffe";
    }

    /**
     * Контроллер REQUEST запроса по ссылке "/coffe-create"
     * для добавления нового напитка в базу данных
     * @return возвращает страницу с таблицей напитков из базы данных,
     * в которую добавлен новый напиток
     */
    @RequestMapping(value = "/coffe-create", method = RequestMethod.POST)
    public String saveCoffe(@ModelAttribute("coffe") Coffe coffe) {
        coffeTeaService.saveCoffeTea(coffe);
        return "redirect:/cafe/coffe";
    }

    /**
     * Контроллер REQUEST запроса по ссылке "/lunch-create"
     * для возвращения на страницу с таблицей после добавления данных
     * @return возвращает страницу с таблицей ланчей из базы данных,
     * в которую добавлен новый ланч
     */
    @RequestMapping("/lunch-create")
    public String newLunchForm(Map<String, Object> model) {
        Lunch lunch = new Lunch();
        model.put("lunch", lunch);
        return "new_lunch";
    }

    /**
     * Контроллер REQUEST запроса по ссылке "/lunch-create"
     * для добавления новой строки в базу данных
     * @return возвращает страницу с таблицей ланчей из базы данных,
     * в которую добавлен новый ланч
     */
    @RequestMapping(value = "/lunch-create", method = RequestMethod.POST)
    public String saveLunch(@ModelAttribute("lunch") Lunch lunch) {
        lunchService.saveLunch(lunch);
        return "redirect:/cafe/lunch";
    }

    /**
     * Контроллер REQUEST запроса по ссылке "/breakfast-create"
     * для возвращения на страницу с таблицей после добавления данных
     * @return возвращает страницу с таблицей завтраков из базы данных,
     * в которую добавлен новый завтрак
     */
    @RequestMapping("/breakfast-create")
    public String newBreakfastForm(Map<String, Object> model) {
        Breakfast breakfast = new Breakfast();
        model.put("breakfast", breakfast);
        return "new_breakfast";
    }

    /**
     * Контроллер REQUEST запроса по ссылке "/breakfast-create"
     * для добавления новой строки в базу данных
     * @return возвращает страницу с таблицей завтраков из базы данных,
     * в которую добавлен новый завтрак
     */
    @RequestMapping(value = "/breakfast-create", method = RequestMethod.POST)
    public String saveBreakfast(@ModelAttribute("breakfast") Breakfast breakfast) {
        breakfastService.saveBreakfast(breakfast);
        return "redirect:/cafe/breakfast";
    }

    /**
     * Контроллер REQUEST запроса по ссылке "/delivery-create"
     * для возвращения на страницу с таблицей после добавления данных
     * @return возвращает страницу с таблицей заказов доставки из базы данных,
     * в которую добавлен новый заказ
     */
    @RequestMapping("/delivery-create")
    public String newDeliveryForm(Map<String, Object> model) {
        Delivery delivery = new Delivery();
        model.put("delivery", delivery);
        return "new_delivery";
    }

    /**
     * Контроллер REQUEST запроса по ссылке "/delivery-create"
     * для добавления нового заказа в базу данных
     * @return возвращает страницу с таблицей заказов доставки из базы данных,
     * в которую добавлен новый заказ
     */
    @RequestMapping(value = "/delivery-create", method = RequestMethod.POST)
    public String saveDelivery(@ModelAttribute("delivery") Delivery delivery) {
        deliveryService.saveDelivery(delivery);
        return "redirect:/cafe/delivery";
    }

    /**
     * Контроллер GET запроса по ссылке "/delete/{table}/{id}"
     * для удаления объекта из базы данных по id
     * @param table параметр таблицы для отображения
     * @param id параметр id объекта
     * @return возвращает страницу с таблицей, из которой удален
     * элемент по его id
     */
    @GetMapping("/delete/{table}/{id}")
    public String deleteCafe(@PathVariable("id") Integer id, @PathVariable("table") String table){
        switch (table) {
            case "dessert":
                cafeService.deleteById(id);
                return "redirect:/cafe";
            case "coffe":
                coffeTeaService.deleteById(id);
                return "redirect:/cafe/coffe";
            case "lunch":
                lunchService.deleteById(id);
                return "redirect:/cafe/lunch";
            case "breakfast":
                breakfastService.deleteById(id);
                return "redirect:/cafe/breakfast";
            case "delivery":
                deliveryService.deleteById(id);
                return "redirect:/cafe/delivery";
        }
        return "redirect:/cafe";
    }

    /**
     * Контроллер GET запроса по ссылке "/update/{table}/{id}"
     * для отображения страницы после редактирования данных
     * @param table параметр таблицы для отображения
     * @param id параметр id объекта
     * @return возвращает страницу с таблицей, в которой
     * обновлен объект
     */
    @GetMapping("/update/{table}/{id}")
    public String updateCafeForm(@PathVariable("id") Integer id, Model model, @PathVariable("table") String table){
        switch (table) {
            case "dessert":
                Cafe cafe = cafeService.findById(id);
                model.addAttribute("cafe", cafe);
                break;
            case "coffe":
                Coffe coffe = coffeTeaService.findById(id);
                model.addAttribute("coffe", coffe);
                break;
            case "lunch":
                Lunch lunch = lunchService.findById(id);
                model.addAttribute("lunch", lunch);
                break;
            case "breakfast":
                Breakfast breakfast = breakfastService.findById(id);
                model.addAttribute("breakfast", breakfast);
                break;
            case "delivery":
                Delivery delivery = deliveryService.findById(id);
                model.addAttribute("delivery", delivery);
                break;
        }
        return "cafe-update";
    }

    /**
     * Контроллер POST запроса по ссылке "/update/dessert"
     * для редактирования объекта из базы данных по id
     * @return возвращает страницу с таблицей, в которой
     * обновлен элемент
     */
    @PostMapping("/update/dessert")
    public String updateCafe(Cafe cafe){
        cafeService.saveCafe(cafe);
        return "redirect:/cafe";
    }

    /**
     * Контроллер POST запроса по ссылке "/update/delivery"
     * для редактирования объекта из базы данных по id
     * @return возвращает страницу с таблицей, в которой
     * обновлен элемент
     */
    @PostMapping("/update/delivery")
    public String updateDelivery(Delivery delivery){
        deliveryService.saveDelivery(delivery);
        return "redirect:/cafe/delivery";
    }

    /**
     * Контроллер POST запроса по ссылке "/update/coffe"
     * для редактирования объекта из базы данных по id
     * @return возвращает страницу с таблицей, в которой
     * обновлен элемент
     */
    @PostMapping("/update/coffe")
    public String updateCoffeTea(Coffe cafe){
        coffeTeaService.saveCoffeTea(cafe);
        return "redirect:/cafe/coffe";
    }

    /**
     * Контроллер POST запроса по ссылке "/update/lunch"
     * для редактирования объекта из базы данных по id
     * @return возвращает страницу с таблицей, в которой
     * обновлен элемент
     */
    @PostMapping("/update/lunch")
    public String updateLunch(Lunch lunch){
        lunchService.saveLunch(lunch);
        return "redirect:/cafe/lunch";
    }

    /**
     * Контроллер POST запроса по ссылке "/update/breakfast"
     * для редактирования объекта из базы данных по id
     * @return возвращает страницу с таблицей, в которой
     * обновлен элемент
     */
    @PostMapping("/update/breakfast")
    public String updateBreakfast(Breakfast breakfast){
        breakfastService.saveBreakfast(breakfast);
        return "redirect:/cafe/breakfast";
    }
}