import { Component } from "@angular/core";
import { Http } from "@angular/http";

// Примеры из директории sample01_inMemServer используют angular2-in-memory-web-api
// для того чтобы тестировать приложения обрабатывая http запросы без реального сервера
// Натройка angular2-in-memory-web-api происходит в app.module
// Логика серверной стороны находится в файле inMemoryServer.ts

@Component({
    moduleId: module.id,
    templateUrl: 'get-request.component.html',
    selector: 'get-request'
})
export class GetRequestComponent {
    itemArray: any[];

    constructor(private http: Http) { }

    clickHandler() {
        // this.http.get - отправка get запроса по указанному адресу
        // метод возвращает объект Observable из библиотеки RxJS
        // с помощью метода subscribe подписываемся на событие
        // событие произойдет после получения ответа от сервера.
        this.http.get('app/items').subscribe(
            result => this.itemArray = result.json().data,
            error => console.log(error.statusText)
        );
    }
}