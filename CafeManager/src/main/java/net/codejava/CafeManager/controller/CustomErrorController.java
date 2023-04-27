package net.codejava.CafeManager.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Контроллер обработки ошибок
 */
@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Получение статуса ошибки из запроса
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // Если статус не найден, вернуть страницу с ошибкой по умолчанию
        if (status == null) {
            return "errorPage";
        }

        // Преобразование статуса в целочисленное значение
        int statusCode = Integer.parseInt(status.toString());

        // Если статус является 404, вернуть страницу с ошибкой 404
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            return "smtError";
        }

        // Если статус является 403, вернуть страницу с ошибкой 403
        else if (statusCode == HttpStatus.FORBIDDEN.value()) {
            return "smtError";
        }

        // В остальных случаях вернуть страницу с ошибкой по умолчанию
        else {
            return "smtError";
        }
    }
}